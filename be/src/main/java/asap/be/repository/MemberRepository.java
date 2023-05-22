package asap.be.repository;

import asap.be.domain.Member;

public interface MemberRepository {
	void save(Member member);

	boolean authenticateMember(String nickname, String password);

    boolean findByEmail(String email);

	void updateVerified(String email);
}
