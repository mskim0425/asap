package asap.be.controller;

import asap.be.domain.Member;
import asap.be.service.MailServiceImpl;
import asap.be.service.MemberServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static asap.be.utils.ApiDocumentUtils.getDocumentRequest;
import static asap.be.utils.MainV2ControllerConstants.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class MemberRestDocs {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private Gson gson;
	@MockBean
	private MemberServiceImpl memberService;
	@MockBean
	private MailServiceImpl mailService;

	@Test
	@DisplayName("회원가입 테스트")
	void signinTest() throws Exception {
		// given
		String content = gson.toJson(SIGNUP_MEMBER);

		doNothing().when(mailService).verifyEmail(Mockito.anyString());
		doNothing().when(memberService).save(Mockito.any(Member.class));
		doNothing().when(mailService).sendCertificationMail(Mockito.anyString());

		// when
		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.post("/api/signin")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content));

		// then
		actions.andExpect(status().isOk())
				.andDo(document(
						"Sign_Up",
						getDocumentRequest(),
						requestFields(
								List.of(
										fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
										fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
										fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
								)
						),
						responseBody()
				));
	}

	@Test
	@DisplayName("회원가입 잘못된 이메일 형식 테스트")
	void incorrectEmailFormatTest() throws Exception {
		// given
		String content = gson.toJson(WRONG_EMAIL_FORMAT_SIGNUP);

		given(memberService.authenticateMember(Mockito.anyString(), Mockito.anyString())).willReturn(true);

		// when
		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.post("/api/signin")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content));

		// then
		actions.andExpect(status().isNotAcceptable())
				.andDo(document(
						"incorrect_email_format",
						getDocumentRequest(),
						requestFields(
								List.of(
										fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
										fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
										fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
								)
						),
						responseBody()
				));
	}

	@Test
	@DisplayName("로그인 테스트")
	void loginTest() throws Exception {
		// given
		String content = gson.toJson(LOGIN_MEMBER);

		given(memberService.authenticateMember(Mockito.anyString(), Mockito.anyString())).willReturn(true);

		// when
		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.post("/api/login")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content));

		// then
		actions.andExpect(status().isOk())
				.andDo(document(
						"Login",
						getDocumentRequest(),
						requestFields(
								List.of(
										fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
										fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
								)
						),
						responseBody()
				));
	}

	@Test
	@DisplayName("로그인 실패 테스트")
	void loginFailTest() throws Exception {
		// given
		String content = gson.toJson(WRONG_LOGIN_MEMBER);

		given(memberService.authenticateMember(Mockito.anyString(), Mockito.anyString())).willReturn(true);

		// when
		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.post("/api/login")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content));

		// then
		actions.andExpect(status().isUnauthorized())
				.andDo(document(
						"Login_fail",
						getDocumentRequest(),
						requestFields(
								List.of(
										fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
										fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
								)
						),
						responseBody()
				));
	}
}
