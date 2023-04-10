package asap.be.controller;

import asap.be.service.ReleaseServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import static asap.be.utils.MainControllerConstants.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ReleaseRestDocs {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Gson gson;

	@MockBean
	private ReleaseServiceImpl releaseService;

	@Test
	@DisplayName("전체 출고 조회")
	void totalReleasePage() throws Exception {
		Integer lastId = 10;
		given(releaseService.findAll(anyInt())).willReturn(ALLRELEASE);

		ResultActions actions = mockMvc.perform(
				RestDocumentationRequestBuilders.get("/api/release", lastId)
						.accept(MediaType.APPLICATION_JSON)
		);

		actions.andExpect(status().isOk())
				.andDo(document(
						"find-all-release",
						responseFields(
								List.of(
										fieldWithPath("[].rid").type(JsonFieldType.NUMBER).description("출고 코드"),
										fieldWithPath("[].quantity").type(JsonFieldType.NUMBER).description("출고량"),
										fieldWithPath("[].total").type(JsonFieldType.NUMBER).description("총 가격"),
										fieldWithPath("[].releaseAt").type(JsonFieldType.STRING).description("출고일자"),
										fieldWithPath("[].lastid").type(JsonFieldType.NUMBER).description("무한스크롤을 사용하기 위한 데이터")
								)
						)

				));
	}

	@Test
	@DisplayName("재고 아이디로 출고 목록 조회")
	void getReleaseByStock() throws Exception {
		Long sId = 1L;

		given(releaseService.findReleaseById(anyLong())).willReturn(RELEASES);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.get("/api/release/{sId}", sId)
								.accept(MediaType.APPLICATION_JSON)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"get-release-by-sid",
						responseFields(
								List.of(
										fieldWithPath("[].rid").type(JsonFieldType.NUMBER).description("출고 코드"),
										fieldWithPath("[].quantity").type(JsonFieldType.NUMBER).description("출고량"),
										fieldWithPath("[].total").type(JsonFieldType.NUMBER).description("총 가격"),
										fieldWithPath("[].releaseAt").type(JsonFieldType.STRING).description("출고일자")
								)
						)
				));
	}
}
