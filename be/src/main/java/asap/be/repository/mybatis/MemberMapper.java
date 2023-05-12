package asap.be.repository.mybatis;

import asap.be.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

	void save(@Param("m") Member member);

	boolean authenticateMember(String email, String password);
}
