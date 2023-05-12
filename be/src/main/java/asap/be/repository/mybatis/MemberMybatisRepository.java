package asap.be.repository.mybatis;

import asap.be.domain.Member;
import asap.be.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberMybatisRepository implements MemberRepository {

	private final MemberMapper memberMapper;

	@Override
	public void save(Member member) {
		memberMapper.save(member);
	}

	@Override
	public boolean authenticateMember(String email, String password) {
		return memberMapper.authenticateMember(email, password);
	}
}
