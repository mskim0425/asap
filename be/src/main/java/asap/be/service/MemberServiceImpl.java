package asap.be.service;

import asap.be.domain.Member;
import asap.be.repository.mybatis.MemberMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMybatisRepository memberMybatisRepository;

	@Override
	public boolean authenticateMember(String email, String password) {
		return memberMybatisRepository.authenticateMember(email, password);
	}

	@Override
	public void save(Member member) {
		memberMybatisRepository.save(member);
	}
}
