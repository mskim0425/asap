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

import static asap.be.utils.MainControllerConstants.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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
						"post-and-receive-prod",
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
						"release-prod",
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

	@Test
	@DisplayName("상품 ID 를 통한 총 입고량, 총 출고량, 총 재고량, 최신 입고일 조회 테스트")
	void getAllProductCnt() throws Exception {

		Long pId = 1L;

		given(productService.findAllCntByPId(anyLong())).willReturn(ALL_PRODUCT_CNT_DTO);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.get("/api/all-cnt/{p-id}", pId)
								.accept(MediaType.APPLICATION_JSON)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"get-all-cnt",
						pathParameters(
								parameterWithName("p-id").description("상품 번호")
						),
						responseFields(
								List.of(
										fieldWithPath("pinsertCnt").type(JsonFieldType.NUMBER).description("총 입고량"),
										fieldWithPath("stockCnt").type(JsonFieldType.NUMBER).description("총 재고량"),
										fieldWithPath("releaseCnt").type(JsonFieldType.NUMBER).description("총 출고량"),
										fieldWithPath("lastReceiveIn").type(JsonFieldType.STRING).description("최근 입고일")
								)
						)
				));
	}

	@Test
	@DisplayName("상품 이름 수정 테스트")
	void patchProductName() throws Exception {

		String content = gson.toJson(EDIT_PRODUCT_NAME);

		doNothing().when(productService).updateProduct(EDIT_PRODUCT_NAME);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.patch("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"patch-product-name",
						requestFields(
								List.of(
										fieldWithPath("pId").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("sId").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("pName").type(JsonFieldType.STRING).description("변경 상품명")
								)
						),
						responseFields(
								List.of(
										fieldWithPath("pid").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 단가"),
										fieldWithPath("pcode").type(JsonFieldType.STRING).description("상품 바코드"),
										fieldWithPath("sid").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("cnt").type(JsonFieldType.NUMBER).description("재고량"),
										fieldWithPath("receiveIn").type(JsonFieldType.STRING).description("최근 입고일"),
										fieldWithPath("wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("wloc").type(JsonFieldType.STRING).description("창고 위치"),
										fieldWithPath("pstatus").type(JsonFieldType.NUMBER).description("삭제 유무 (삭제됨 상품 : 0")
								)
						)
				));
	}

	@Test
	@DisplayName("상품 가격 수정 테스트")
	void patchProductPrice() throws Exception {

		String content = gson.toJson(EDIT_PRODUCT_PRICE);

		doNothing().when(productService).updateProduct(EDIT_PRODUCT_PRICE);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.patch("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"patch-product-price",
						requestFields(
								List.of(
										fieldWithPath("pId").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("sId").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("변경 가격")
								)
						),
						responseFields(
								List.of(
										fieldWithPath("pid").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 단가"),
										fieldWithPath("pcode").type(JsonFieldType.STRING).description("상품 바코드"),
										fieldWithPath("sid").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("cnt").type(JsonFieldType.NUMBER).description("재고량"),
										fieldWithPath("receiveIn").type(JsonFieldType.STRING).description("최근 입고일"),
										fieldWithPath("wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("wloc").type(JsonFieldType.STRING).description("창고 위치"),
										fieldWithPath("pstatus").type(JsonFieldType.NUMBER).description("삭제 유무 (삭제됨 상품 : 0")
								)
						)
				));
	}

	@Test
	@DisplayName("상품 바코드 수정 테스트")
	void patchProductBarcode() throws Exception {

		String content = gson.toJson(EDIT_PRODUCT_BARCODE);

		doNothing().when(productService).updateProduct(EDIT_PRODUCT_BARCODE);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.patch("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"patch-product-barcode",
						requestFields(
								List.of(
										fieldWithPath("pId").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("sId").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("pCode").type(JsonFieldType.STRING).description("변경 바코드")
								)
						),
						responseFields(
								List.of(
										fieldWithPath("pid").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 단가"),
										fieldWithPath("pcode").type(JsonFieldType.STRING).description("상품 바코드"),
										fieldWithPath("sid").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("cnt").type(JsonFieldType.NUMBER).description("재고량"),
										fieldWithPath("receiveIn").type(JsonFieldType.STRING).description("최근 입고일"),
										fieldWithPath("wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("wloc").type(JsonFieldType.STRING).description("창고 위치"),
										fieldWithPath("pstatus").type(JsonFieldType.NUMBER).description("삭제 유무 (삭제됨 상품 : 0")
								)
						)
				));
	}

	@Test
	@DisplayName("상품 전체 수정 테스트")
	void patchProduct() throws Exception {

		String content = gson.toJson(EDIT_ALL);

		doNothing().when(productService).updateProduct(EDIT_ALL);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.patch("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"patch-product",
						requestFields(
								List.of(
										fieldWithPath("pId").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("sId").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("pName").type(JsonFieldType.STRING).description("바뀐 상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("바뀐 가격"),
										fieldWithPath("pCode").type(JsonFieldType.STRING).description("바뀐 바코드")
								)
						),
						responseFields(
								List.of(
										fieldWithPath("pid").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 단가"),
										fieldWithPath("pcode").type(JsonFieldType.STRING).description("상품 바코드"),
										fieldWithPath("sid").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("cnt").type(JsonFieldType.NUMBER).description("재고량"),
										fieldWithPath("receiveIn").type(JsonFieldType.STRING).description("최근 입고일"),
										fieldWithPath("wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("wloc").type(JsonFieldType.STRING).description("창고 위치"),
										fieldWithPath("pstatus").type(JsonFieldType.NUMBER).description("삭제 유무 (삭제됨 상품 : 0")
								)
						)
				));
	}

	@Test
	@DisplayName("상품 상태 변경 테스트 (삭제)")
	void deleteProduct() throws Exception {

		String content = gson.toJson(DELETE_PRODUCT);

		doNothing().when(productService).updateProduct(DELETE_PRODUCT);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.patch("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"delete-product",
						requestFields(
								List.of(
										fieldWithPath("pId").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("sId").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("pStatus").type(JsonFieldType.NUMBER).description("삭제 상태로 변경:0, 활성화 상태:1")
								)
						),
						responseFields(
								List.of(
										fieldWithPath("pid").type(JsonFieldType.NUMBER).description("상품 식별자"),
										fieldWithPath("pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 단가"),
										fieldWithPath("pcode").type(JsonFieldType.STRING).description("상품 바코드"),
										fieldWithPath("sid").type(JsonFieldType.NUMBER).description("재고 식별자"),
										fieldWithPath("cnt").type(JsonFieldType.NUMBER).description("재고량"),
										fieldWithPath("receiveIn").type(JsonFieldType.STRING).description("최근 입고일"),
										fieldWithPath("wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("wloc").type(JsonFieldType.STRING).description("창고 위치"),
										fieldWithPath("pstatus").type(JsonFieldType.NUMBER).description("삭제 유무 (삭제됨 상품 : 0")
								)
						)
				));
	}
}
