package asap.be.controller;

import asap.be.domain.Member;
import asap.be.dto.EverythingPageDto;
import asap.be.search.SearchService;
import asap.be.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainV2Controller {
	private final SearchService searchService;
	private final MemberService memberService;

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

	@PostMapping(value = "/signin", produces = "application/json; charset=utf8")
	public ResponseEntity<String> signIn(@RequestBody Member member) {

		if (!member.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("잘못된 이메일 형식입니다.");

		memberService.save(member);

		return ResponseEntity.ok("회원가입 되었습니다.");
	}

}
