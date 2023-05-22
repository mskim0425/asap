package asap.be.utils;

import asap.be.domain.Member;

public class MainV2ControllerConstants {

	public static final Member SIGNUP_MEMBER =
			Member.builder()
					.email("hyoreal51@gmail.com")
					.nickname("졸려")
					.password("asdfghjkl")
					.build();

	public static final Member WRONG_EMAIL_FORMAT_SIGNUP =
			Member.builder()
					.email("hyoreal51")
					.nickname("졸려")
					.password("asdfghjkl")
					.build();

	public static final Member LOGIN_MEMBER =
			Member.builder()
					.email("hyoreal51@gmail.com")
					.password("asdfghjkl")
					.build();

	public static final Member WRONG_LOGIN_MEMBER =
			Member.builder()
					.email("hyoreal51@gmail.com")
					.password("wrong_password")
					.build();
}
