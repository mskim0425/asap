//package asap.be;
//
//import static asap.be.utils.ApiDocumentUtils.*;
//import static asap.be.utils.MainControllerConstants.*;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import asap.be.domain.Warehouse;
//import asap.be.dto.EverythingDto;
//import asap.be.dto.PostProductDto;
//import asap.be.service.ProductService;
//import asap.be.service.ReleaseService;
//import asap.be.service.WarehouseService;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//
//@Transactional
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//public class MainControllerRestDocs {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private Gson gson;
//
//	@MockBean
//	private ProductService productService;
//
//	@MockBean
//	private ReleaseService releaseService;
//
//	@MockBean
//	private WarehouseService warehouseService;
//
//	@Test
//	@DisplayName("상품 저장 테스트")
//	void saveProductTest() throws Exception {
//		String content = gson.toJson(SAVE_AND_RECEIVE_PRODUCT);
//
//		EverythingDto everythingDto =
//				EverythingDto.builder()
//						.pId(1L)
//						.pname(SAVE_AND_RECEIVE_PRODUCT.getPName())
//						.price(SAVE_AND_RECEIVE_PRODUCT.getPrice())
//						.pcode(SAVE_AND_RECEIVE_PRODUCT.getPCode())
//						.sId(1L)
//						.cnt(SAVE_AND_RECEIVE_PRODUCT.getPInsert())
//						.receiveIn(LocalDate.now().toString())
//						.wId(SAVE_AND_RECEIVE_PRODUCT.getWId())
//						.wname(WAREHOUSE.getWName())
//						.wloc(WAREHOUSE.getWLoc())
//						.pStatus(1)
//						.build();
//
//	}
//
//	@Test
//	@DisplayName("아이템 별 최신 21일 입/출고량 테스트")
//	void getProductCntByDate() throws Exception {
//
//		Long pId = 1L;
//
//		ResultActions actions =
//				mockMvc.perform(
//						RestDocumentationRequestBuilders.get("/api/cnt-product-by-date/{p-id}", pId)
//								.accept(MediaType.APPLICATION_JSON)
//				);
//
//		actions.andExpect(status().isOk())
//				.andDo(document(
//						"post-product",
//						pathParameters(
//								parameterWithName("p-id").description("상품 번호")
//						)
//				));
//	}
//}
