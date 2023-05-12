package asap.be.controller;

import asap.be.domain.Member;
import asap.be.dto.EverythingPageDto;
import asap.be.dto.MailDto;
import asap.be.search.SearchService;
import asap.be.service.MailService;
import asap.be.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainV2Controller {
	private final SearchService searchService;
	private final MemberService memberService;
	private final MailService mailService;
	private final RedisTemplate<String, String> redisTemplate;

	@GetMapping("/search")
	public ResponseEntity<List<EverythingPageDto>> search(@RequestParam(value = "lastId", required = false) Integer lastId,
														  @RequestParam("pName") String pName,
														  @RequestParam(value = "order", defaultValue = "DESC") String order) {

		List<EverythingPageDto> searchList = searchService.search(lastId, pName, order);
		return new ResponseEntity<>(searchList, HttpStatus.OK);
	}

	@PostMapping(value = "/login", produces = "application/json; charset=utf8")
	public ResponseEntity<String> login(HttpSession session, @RequestBody Member member) {

		if (memberService.authenticateMember(member.getEmail(), member.getPassword())) {
			session.setAttribute("memberId", member.getEmail());
			return ResponseEntity.ok("로그인 성공");
		}
		else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
	}

	@GetMapping(value = "/logout", produces = "application/json; charset=utf8")
	public ResponseEntity<String> logout(HttpSession session) {

		session.invalidate();
		return ResponseEntity.ok("로그아웃되었습니다.");
	}

	/**
	 * 회원가입 및 이메일 인증 동시 처리
	 * @param member
	 * @return
	 */
	@PostMapping(value = "/signin", produces = "application/json; charset=utf8")
	public ResponseEntity<String> signIn(@RequestBody Member member) {

		if (!member.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("잘못된 이메일 형식입니다.");
		
		memberService.save(member);
		mailService.verifyEmail(member.getEmail());
		mailService.sendCertificationMail(member.getEmail());
		
		
		return ResponseEntity.ok("회원가입 되었습니다.");
	}

	@GetMapping(value="/check/{checkmail}", produces = "application/json; charset=utf8")
	public ResponseEntity<String> checkEmail(@PathVariable("checkmail") String checkMail) {

		String verify = checkMail.split("!")[0];
		String email = checkMail.split("!")[1];

		if (!Boolean.TRUE.equals(redisTemplate.hasKey(email))) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("유효한 이메일이 아닙니다.");
		}
		if (!Objects.equals(redisTemplate.opsForValue().get(email), verify)) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("유효한 인증 코드가 아닙니다.");
		}

		mailService.setVerifiedEmail(email);

		return ResponseEntity.ok("메일이 인증 되었습니다.");
	}
}
