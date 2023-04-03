package asap.be.controller;

import static asap.be.utils.MainControllerConstants.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.will;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import asap.be.dto.DashboardDto;
import asap.be.service.DashBoardService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class DashboardRestDocs {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Gson gson;

	@MockBean
	private DashBoardService dashBoardService;


	@Test
	@DisplayName("아이템 별 최신 21일 입/출고량 테스트")
	void getProductCntByDate() throws Exception {

		Long pId = 1L;

		given(dashBoardService.CntProduct(anyLong())).willReturn(PRODUCT_CNT_DTO_LIST);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.get("/api/cnt-product-by-date/{p-id}", pId)
								.accept(MediaType.APPLICATION_JSON)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"cnt-product-by-date",
						pathParameters(
								parameterWithName("p-id").description("상품 번호")
						),
						responseFields(
								List.of(
										fieldWithPath("[]").type(JsonFieldType.ARRAY).description("결과 데이터"),
										fieldWithPath("[].date").type(JsonFieldType.STRING).description("날짜"),
										fieldWithPath("[].insertCnt").type(JsonFieldType.NUMBER).description("입고량"),
										fieldWithPath("[].releaseCnt").type(JsonFieldType.NUMBER).description("출고량")
								)
						)
				));
	}

	@Test
	@DisplayName("일별 상품 입/출고수 TOP 10 테스트")
	void getProductRankTop10() throws Exception {

		List<Map<String, Object>> insertRankDto = new ArrayList<>();
		Map<String, Object> insertRank = new HashMap<>();
		insertRank.put("pName", "입고 상품명");
		insertRank.put("insertCnt", 100);
		insertRank.put("ranking", 1);
		insertRankDto.add(insertRank);

		List<Map<String, Object>> releaseRankDto = new ArrayList<>();
		Map<String, Object> releaseRank = new HashMap<>();
		releaseRank.put("pName", "출고 상품명");
		releaseRank.put("releaseCnt", 100);
		releaseRank.put("ranking", 1);
		releaseRankDto.add(releaseRank);

		DashboardDto.RankDto rankDto = DashboardDto.RankDto.builder()
				.insertRankDto(insertRankDto)
				.releaseRankDto(releaseRankDto)
				.build();

		given(dashBoardService.ProductCntRank()).willReturn(rankDto);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.get("/api/product-rank")
								.accept(MediaType.APPLICATION_JSON)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"product-rank",
						responseFields(
								List.of(
										fieldWithPath("insertRankDto[]").type(JsonFieldType.ARRAY).description("금일 상품별 입고량 TOP 10"),
										fieldWithPath("insertRankDto[].pName").type(JsonFieldType.STRING).description("입고 상품명"),
										fieldWithPath("insertRankDto[].insertCnt").type(JsonFieldType.NUMBER).description("금일 입고량"),
										fieldWithPath("insertRankDto[].ranking").type(JsonFieldType.NUMBER).description("순위"),
										fieldWithPath("releaseRankDto[]").type(JsonFieldType.ARRAY).description("금일 상품별 출고량 TOP 10"),
										fieldWithPath("releaseRankDto[].pName").type(JsonFieldType.STRING).description("출고 상품명"),
										fieldWithPath("releaseRankDto[].releaseCnt").type(JsonFieldType.NUMBER).description("금일 출고량"),
										fieldWithPath("releaseRankDto[].ranking").type(JsonFieldType.NUMBER).description("순위")
								)
						)
				));
	}

//	@Test
	@DisplayName("일별 출고 금액 테스트")
	void getTotalProductAmount() throws Exception {
		/* 민섭님 담당 레스트독스 */
	}

	@Test
	@DisplayName("월별 재고 현황 테스트")
	void getMonthlyStockSum() throws Exception {

		String year = "2022";

		given(dashBoardService.getMonthlyStockSummary(anyString())).willReturn(YEAR_STATUS_DTO_LIST);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.get("/api/monthly-stock-summary?year={year}", year)
								.accept(MediaType.APPLICATION_JSON)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"monthly-stock-summary",
						responseFields(
								List.of(
										fieldWithPath("[]").type(JsonFieldType.ARRAY).description("결과 데이터"),
										fieldWithPath("[].month").type(JsonFieldType.NUMBER).description("월"),
										fieldWithPath("[].allQuantity").type(JsonFieldType.NUMBER).description("월 전체 수량"),
										fieldWithPath("[].allReleaseAt").type(JsonFieldType.NUMBER).description("월 전체 출고량"),
										fieldWithPath("[].allInsert").type(JsonFieldType.NUMBER).description("월 전체 입고량")
								)
						)
				));
	}

	@Test
	@DisplayName("나라 별 재고 분포도 테스트")
	void getCountryProductStatus() throws Exception {

		given(dashBoardService.getCountryProductStauts()).willReturn(COUNTRY_DTO_LIST);

		ResultActions actions =
				mockMvc.perform(RestDocumentationRequestBuilders.get("/api/country-product-status")
						.accept(MediaType.APPLICATION_JSON));

		actions.andExpect(status().isOk())
				.andDo(document(
						"country-product-status",
						responseFields(
								List.of(
										fieldWithPath("[]").type(JsonFieldType.ARRAY).description("결과 데이터"),
										fieldWithPath("[].countryName").type(JsonFieldType.STRING).description("나라 이름"),
										fieldWithPath("[].productCnt").type(JsonFieldType.NUMBER).description("재고 분포 개수")
								)
						)
				));

	}
}
