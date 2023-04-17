package asap.be.controller;

import asap.be.dto.EditProductDto;
import asap.be.service.ProductServiceImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
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
										fieldWithPath("pInsert").description("저장 및 입고 시 사용").attributes(key("ignored").value(true)),
										fieldWithPath("quantity").type(JsonFieldType.NUMBER).description("입고량")
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
	@DisplayName("상품 출고 예외 테스트1")
	void releaseProductExceptionTest() throws Exception {
		String content = gson.toJson(EXCEPTION_RELEASE_PRODUCT);
		doNothing().when(productService).insertOrUpdateStock(EXCEPTION_RELEASE_PRODUCT);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.post("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().is4xxClientError())
				.andDo(document(
						"release-prod-exception",
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
										fieldWithPath("status").type(JsonFieldType.NUMBER).description("에러 코드"),
										fieldWithPath("message").type(JsonFieldType.STRING).description("에러 메세지"),
										fieldWithPath("fieldErrors").description("").attributes(key("ignored").value(true)),
										fieldWithPath("violationErrors").description("").attributes(key("ignored").value(true))
								)
						)
				));
	}

	@Test
	@DisplayName("상품 출고 예외 테스트2")
	void releaseProductExceptionNameTest() throws Exception {
		String content = gson.toJson(NAME_EXCEPTION_RELEASE_PRODUCT);
		doNothing().when(productService).insertOrUpdateStock(NAME_EXCEPTION_RELEASE_PRODUCT);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.post("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().is4xxClientError())
				.andDo(document(
						"release-prod-exception",
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
										fieldWithPath("status").type(JsonFieldType.NUMBER).description("에러 코드"),
										fieldWithPath("message").type(JsonFieldType.STRING).description("에러 메세지"),
										fieldWithPath("fieldErrors").description("").attributes(key("ignored").value(true)),
										fieldWithPath("violationErrors").description("").attributes(key("ignored").value(true))
								)
						)
				));
	}

	@Test
	@DisplayName("상품명을 통한 총 입고량, 총 출고량, 총 재고량, 최신 입고일 조회 테스트")
	void getAllProductCnt() throws Exception {

		// given
		String productName = "과자";
		// when
		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.get("/api/all-cnt?pName={pName}", productName)
								.accept(MediaType.APPLICATION_JSON)
				);
		//then
		actions.andExpect(status().isOk())
				.andDo(document(
						"get-all-cnt",
						responseFields(
								List.of(
										fieldWithPath("pinsertCnt").type(JsonFieldType.NUMBER).description("총 입고량"),
										fieldWithPath("stockCnt").type(JsonFieldType.NUMBER).description("총 재고량"),
										fieldWithPath("releaseCnt").type(JsonFieldType.NUMBER).description("총 출고량").optional(),
										fieldWithPath("lastReceiveIn").type(JsonFieldType.STRING).description("최근 입고일")
								)
						)
				));
	}

	@Test
	@DisplayName("상세페이지 조회")
	void detailPage() throws Exception {
		Long pId = 3L;
		given(productService.detailPageUsingPId(anyLong())).willReturn(DETAIL_INFO_DTO);

		ResultActions actions = mockMvc.perform(
				RestDocumentationRequestBuilders.get("/api/find-one?pId={pId}", pId).accept(MediaType.APPLICATION_JSON)
		);

		actions.andExpect(status().isOk())
				.andDo(document(
						"find-one",
						responseFields(
								List.of(
										fieldWithPath("product").type(JsonFieldType.OBJECT).description("상품 정보"),
										fieldWithPath("product.price").type(JsonFieldType.NUMBER).description("단가"),
										fieldWithPath("product.pstatus").type(JsonFieldType.NUMBER).description("상품상태"),
										fieldWithPath("product.pid").type(JsonFieldType.NUMBER).description("상품코드"),
										fieldWithPath("product.pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("product.pcode").type(JsonFieldType.STRING).description("바코드"),
										fieldWithPath("product.cnt").type(JsonFieldType.NUMBER).description("상품 재고"),
										fieldWithPath("product.warehouses[]").type(JsonFieldType.ARRAY).description("창고 리스트"),
										fieldWithPath("product.warehouses[].wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("product.warehouses[].wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("insertLogs[].receiveIn").type(JsonFieldType.STRING).description("입고 일자"),
										fieldWithPath("insertLogs[].pinsert").type(JsonFieldType.NUMBER).description("입고량"),
										fieldWithPath("insertLogs[].wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("insertLogs[].wloc").type(JsonFieldType.STRING).description("창고 위치"),
										fieldWithPath("releaseLogs[].releaseAt").type(JsonFieldType.STRING).description("출고일"),
										fieldWithPath("releaseLogs[].quantity").type(JsonFieldType.NUMBER).description("출고량"),
										fieldWithPath("releaseLogs[].wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("releaseLogs[].wloc").type(JsonFieldType.STRING).description("창고 위치")
								)
						)
				));
	}

	@Test
	@DisplayName("전체페이지 조회")
	void totalPage() throws Exception {
		Integer lastId = 10;
		given(productService.findByAll(anyInt())).willReturn(ALL_INFO_DTO_LIST);

		ResultActions actions = mockMvc.perform(
				RestDocumentationRequestBuilders.get("/api/find-all?lastId={lastId}", lastId).accept(MediaType.APPLICATION_JSON)
		);

		actions.andExpect(status().isOk())
				.andDo(document(
						"find-all",
						responseFields(
								List.of(
										fieldWithPath("[].price").type(JsonFieldType.NUMBER).description("단가"),
										fieldWithPath("[].pid").type(JsonFieldType.NUMBER).description("상품코드"),
										fieldWithPath("[].pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("[].pcode").type(JsonFieldType.STRING).description("바코드"),
										fieldWithPath("[].lastid").type(JsonFieldType.NUMBER).description("무한스크롤을 사용하기 위한 데이터")
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
										fieldWithPath("pstatus").type(JsonFieldType.NUMBER).description("삭제 유무 (삭제됨 상품 : 0)")
								)
						)
				));
	}

	@Test
	@DisplayName("존재하지 않는 상품 예외 테스트")
	void exceptionProductName() throws Exception {

		String content = gson.toJson(EXIST_PRODUCT);

		doNothing().when(productService).updateProduct(EXIST_PRODUCT);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.patch("/api/prod")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().is4xxClientError())
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
										fieldWithPath("status").type(JsonFieldType.NUMBER).description("에러 코드"),
										fieldWithPath("message").type(JsonFieldType.STRING).description("에러 메세지"),
										fieldWithPath("fieldErrors").description("").attributes(key("ignored").value(true)),
										fieldWithPath("violationErrors").description("").attributes(key("ignored").value(true))
								)
						)
				));
	}

	@Test
	@DisplayName("상세페이지 수정")
	void editDetailPage() throws Exception {
		Long pId = 3L;
		String content = gson.toJson(EDIT_PRODUCT_NAME);

		given(productService.editDetailPage(anyLong(), any(EditProductDto.class))).willReturn(DETAIL_INFO_DTO);

		ResultActions actions = mockMvc.perform(
				RestDocumentationRequestBuilders.get("/api/find-one?pId={pId}", pId)
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(content)
		);

		actions.andExpect(status().isOk())
				.andDo(document(
						"patch-find-one",
						responseFields(
								List.of(
										fieldWithPath("product").type(JsonFieldType.OBJECT).description("상품 정보"),
										fieldWithPath("product.price").type(JsonFieldType.NUMBER).description("단가"),
										fieldWithPath("product.pstatus").type(JsonFieldType.NUMBER).description("상품상태"),
										fieldWithPath("product.pid").type(JsonFieldType.NUMBER).description("상품코드"),
										fieldWithPath("product.pname").type(JsonFieldType.STRING).description("상품명"),
										fieldWithPath("product.pcode").type(JsonFieldType.STRING).description("바코드"),
										fieldWithPath("product.cnt").type(JsonFieldType.NUMBER).description("상품 재고"),
										fieldWithPath("product.warehouses[]").type(JsonFieldType.ARRAY).description("창고 리스트"),
										fieldWithPath("product.warehouses[].wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("product.warehouses[].wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("insertLogs[].receiveIn").type(JsonFieldType.STRING).description("입고 일자"),
										fieldWithPath("insertLogs[].pinsert").type(JsonFieldType.NUMBER).description("입고량"),
										fieldWithPath("insertLogs[].wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("insertLogs[].wloc").type(JsonFieldType.STRING).description("창고 위치"),
										fieldWithPath("releaseLogs[].releaseAt").type(JsonFieldType.STRING).description("출고일"),
										fieldWithPath("releaseLogs[].quantity").type(JsonFieldType.NUMBER).description("출고량"),
										fieldWithPath("releaseLogs[].wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("releaseLogs[].wloc").type(JsonFieldType.STRING).description("창고 위치")
								)
						)

				));

	}

}
