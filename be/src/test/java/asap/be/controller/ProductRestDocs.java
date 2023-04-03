package asap.be.controller;

import asap.be.service.ProductServiceImpl;
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

import static asap.be.utils.MainControllerConstants.RELEASE_PRODUCT;
import static asap.be.utils.MainControllerConstants.SAVE_AND_RECEIVE_PRODUCT;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ProductRestDocs {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Gson gson;

	@MockBean
	private ProductServiceImpl productService;


	@Test
	@DisplayName("상품 저장/입고 테스트")
	void saveProductTest() throws Exception {
		String content = gson.toJson(SAVE_AND_RECEIVE_PRODUCT);

		doNothing().when(productService).insertOrUpdateStock(SAVE_AND_RECEIVE_PRODUCT);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.post("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"post-prod",
						requestFields(
								List.of(
										fieldWithPath("pName").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 단가"),
										fieldWithPath("pCode").type(JsonFieldType.STRING).description("상품 바코드 이미지"),
										fieldWithPath("wId").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("pInsert").type(JsonFieldType.NUMBER).description("입고량"),
										fieldWithPath("quantity").description("출고 시 사용").attributes(key("ignored").value(true))
								)
						),
						responseFields(
								List.of(
										fieldWithPath("pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 단가"),
										fieldWithPath("pcode").type(JsonFieldType.STRING).description("상품 바코드 이미지"),
										fieldWithPath("cnt").type(JsonFieldType.NUMBER).description("재고량"),
										fieldWithPath("receiveIn").type(JsonFieldType.STRING).description("최신 입고일"),
										fieldWithPath("wname").type(JsonFieldType.STRING).description("재고 보유 창고명"),
										fieldWithPath("wloc").type(JsonFieldType.STRING).description("재고 보유 창고 위치"),
										fieldWithPath("pstatus").type(JsonFieldType.NUMBER).description("삭제 유무 (삭제됨 상품 : 0)"),
										fieldWithPath("wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("pid").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("sid").type(JsonFieldType.NUMBER).description("재고 식별자")
						)
				)));
	}

	@Test
	@DisplayName("상품 출고 테스트")
	void releaseProductTest() throws Exception {
		String content = gson.toJson(RELEASE_PRODUCT);
		doNothing().when(productService).insertOrUpdateStock(RELEASE_PRODUCT);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.post("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"post-prod",
						requestFields(
								List.of(
										fieldWithPath("pName").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 단가"),
										fieldWithPath("pCode").type(JsonFieldType.STRING).description("상품 바코드 이미지"),
										fieldWithPath("wId").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("quantity").type(JsonFieldType.NUMBER).description("입고량"),
										fieldWithPath("pInsert").description("입고 시 사용").attributes(key("ignored").value(true))
								)
						),
						responseFields(
								List.of(
										fieldWithPath("pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 단가"),
										fieldWithPath("pcode").type(JsonFieldType.STRING).description("상품 바코드 이미지"),
										fieldWithPath("cnt").type(JsonFieldType.NUMBER).description("재고량"),
										fieldWithPath("receiveIn").type(JsonFieldType.STRING).description("최신 입고일"),
										fieldWithPath("wname").type(JsonFieldType.STRING).description("재고 보유 창고명"),
										fieldWithPath("wloc").type(JsonFieldType.STRING).description("재고 보유 창고 위치"),
										fieldWithPath("pstatus").type(JsonFieldType.NUMBER).description("삭제 유무 (삭제됨 상품 : 0)"),
										fieldWithPath("wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("pid").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("sid").type(JsonFieldType.NUMBER).description("재고 식별자")
								)
						)));
	}
}
