package asap.be.service;

import asap.be.domain.Member;

public interface MemberService {
	boolean authenticateMember(String email, String password);

	void save(Member member);
}
