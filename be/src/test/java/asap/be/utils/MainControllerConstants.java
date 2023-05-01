package asap.be.utils;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.domain.Warehouse;
import asap.be.dto.*;
import asap.be.dto.ProductCntDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static asap.be.utils.Dummy.*;

/**
 * 더미데이터
 */
public class MainControllerConstants {
	public static final PostProductDto SAVE_AND_RECEIVE_PRODUCT =
			PostProductDto.builder()
					.pName("상품명")
					.price(10000)
					.pCode("상품바코드")
					.wId(1L) // 창고코드
					.pInsert(100) // 입고 수량
					.build();

	public static final PostProductDto RELEASE_PRODUCT =
			PostProductDto.builder()
					.pName("상품명")
					.price(10000)
					.pCode("상품바코드")
					.wId(1L) // 창고코드
					.quantity(1) // 출고
					.build();

	public static final PostProductDto EXCEPTION_RELEASE_PRODUCT =
			PostProductDto.builder()
					.pName("상품명")
					.price(10000)
					.pCode("상품바코드")
					.wId(1L) // 창고코드
					.quantity(9999999) // 출고
					.build();

	public static final PostProductDto NAME_EXCEPTION_RELEASE_PRODUCT =
			PostProductDto.builder()
					.pName("이것은 없는 상품명")
					.price(10000)
					.pCode("상품바코드")
					.wId(1L) // 창고코드
					.quantity(9999999) // 출고
					.build();

	public static final EditProductDto EDIT_PRODUCT_NAME =
			EditProductDto.builder()
					.pId(57777L)
					.sId(57777L)
					.pName("새로 변경할 상품명")
					.build();

	public static final EditProductDto EDIT_PRODUCT_PRICE =
			EditProductDto.builder()
					.pId(57777L)
					.sId(57777L)
					.price(5000)
					.build();

	public static final EditProductDto EDIT_PRODUCT_BARCODE =
			EditProductDto.builder()
					.pId(57777L)
					.sId(57777L)
					.pCode("새로운 상품 바코드")
					.build();

	public static final EditProductDto EDIT_ALL =
			EditProductDto.builder()
					.pId(57777L)
					.sId(57777L)
					.pName("새로 변경할 상품명")
					.price(2000)
					.pCode("새로운 상품 바코드")
					.build();

	public static final EditProductDto DELETE_PRODUCT =
			EditProductDto.builder()
					.pId(57777L)
					.sId(57777L)
					.pStatus(0)
					.build();

	public static final EditProductDto EXIST_PRODUCT =
			EditProductDto.builder()
					.pId(123456L)
					.sId(120L)
					.pName("새로 변경할 상품명")
					.build();


	public static final Warehouse WAREHOUSE =
			Warehouse.builder()
					.wId(1L)
					.wName("창고명")
					.wLoc("창고 위치")
					.build();

	public static final DetailReleaseInsertDto DETAIL_INFO_DTO01 =
			DetailReleaseInsertDto.builder()
					.releaseAt("2023-04-03").quantity(10)
					.cnt(348).pid(1).wId(38)
					.sId(1L).pInsertLog("2023-04-03 : 348").wName("BMW M6")
					.wLoc("Palau").rId(1L)
					.build();

	public static final DetailReleaseInsertDto DETAIL_INFO_DTO02 =
			DetailReleaseInsertDto.builder()
					.releaseAt("2023-04-03").quantity(10)
					.cnt(348).pid(1).wId(38)
					.sId(1L).pInsertLog("2023-04-03 : 348").wName("BMW M6")
					.wLoc("Palau").rId(1L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO01 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(1L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO02 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(2L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO03 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(3L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO04 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(4L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO05 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(5L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO06 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(6L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO07 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(7L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO08 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(8L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO09 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(9L)
					.build();

	public static final EverythingPageDto EVERYTHING_PAGE_DTO10 =
			EverythingPageDto.builder()
					.pname("닭고기")
					.price(656)
					.pcode("7e5fbc0d-bf00-4c2d-bb58-a78fdbf326f3")
					.lastid(10)
					.pId(10L)
					.build();

	public static final List<ProductCntDto> PRODUCT_CNT_DTO_LIST =
			List.of(PRODUCT_CNT_DTO1, PRODUCT_CNT_DTO2, PRODUCT_CNT_DTO3,
					PRODUCT_CNT_DTO4, PRODUCT_CNT_DTO5, PRODUCT_CNT_DTO6,
					PRODUCT_CNT_DTO7, PRODUCT_CNT_DTO8, PRODUCT_CNT_DTO9,
					PRODUCT_CNT_DTO10, PRODUCT_CNT_DTO11, PRODUCT_CNT_DTO12);

	public static final List<YearStatusDto> YEAR_STATUS_DTO_LIST =
			List.of(YEAR_STATUS_DTO1, YEAR_STATUS_DTO2, YEAR_STATUS_DTO3,
					YEAR_STATUS_DTO4, YEAR_STATUS_DTO5, YEAR_STATUS_DTO6,
					YEAR_STATUS_DTO7, YEAR_STATUS_DTO8, YEAR_STATUS_DTO9,
					YEAR_STATUS_DTO10, YEAR_STATUS_DTO11, YEAR_STATUS_DTO12);

	public static final List<CountryDto> COUNTRY_DTO_LIST =
			List.of(COUNTRY_DTO1, COUNTRY_DTO2, COUNTRY_DTO3,
					COUNTRY_DTO4, COUNTRY_DTO5, COUNTRY_DTO6,
					COUNTRY_DTO7, COUNTRY_DTO8, COUNTRY_DTO9,
					COUNTRY_DTO10);

	public static final List<MoneyDto> MONEY_DTO_LIST =
			List.of(MONEY_DTO01, MONEY_DTO02, MONEY_DTO03, MONEY_DTO04,
					MONEY_DTO05, MONEY_DTO06, MONEY_DTO07, MONEY_DTO08, MONEY_DTO09, MONEY_DTO10);

	public static final List<DetailReleaseInsertDto> DETAIL_INFO_DTO_LIST =
			List.of(DETAIL_INFO_DTO01, DETAIL_INFO_DTO02);

	public static final DetailProductDto PRODUCT_DTO =
			DetailProductDto.builder()
					.pId(1)
					.pName("피자")
					.pCode("df26e23d-4136-442f-92d3-7a0170d79a22")
					.pStatus(1)
					.price(765)
					.build();

	public static final DetailInsertLogsDto DETAIL_INSERT_LOGS_DTO =
			DetailInsertLogsDto.builder()
					.wName("BMW M5")
					.wLoc("Nicaragua")
					.pInsert(100)
					.receiveIn("2023-03-01")
					.build();

	public static final DetailReleaseDto DETAIL_RELEASE_DTO =
			DetailReleaseDto.builder()
					.wName("BMW M5")
					.wLoc("Nicaragua")
					.releaseAt("2023-03-01")
					.quantity(10)
					.build();

	public static final DetailInfoDto DETAIL_INFO_DTO =
			DetailInfoDto.builder()
					.product(PRODUCT_DTO)
					.insertLogs(List.of(DETAIL_INSERT_LOGS_DTO))
					.releaseLogs(List.of(DETAIL_RELEASE_DTO))
					.build();

	public static final List<EverythingPageDto> ALL_INFO_DTO_LIST =
			List.of(EVERYTHING_PAGE_DTO01, EVERYTHING_PAGE_DTO02, EVERYTHING_PAGE_DTO03,
					EVERYTHING_PAGE_DTO04, EVERYTHING_PAGE_DTO05, EVERYTHING_PAGE_DTO06,
					EVERYTHING_PAGE_DTO07, EVERYTHING_PAGE_DTO08, EVERYTHING_PAGE_DTO09, EVERYTHING_PAGE_DTO10
			);

	public static final EverythingDto SAVE_EVERYTHING_DTO =
			EverythingDto.builder()
					.pId(1L)
					.pname(SAVE_AND_RECEIVE_PRODUCT.getpName())
					.price(SAVE_AND_RECEIVE_PRODUCT.getPrice())
					.pcode(SAVE_AND_RECEIVE_PRODUCT.getpCode())
					.sId(1L)
					.cnt(SAVE_AND_RECEIVE_PRODUCT.getpInsert())
					.receiveIn(LocalDate.now().toString())
					.wId(SAVE_AND_RECEIVE_PRODUCT.getwId())
					.wname(WAREHOUSE.getWName())
					.wloc(WAREHOUSE.getWLoc())
					.pStatus(1)
					.build();

	public static final AllProductCntDto ALL_PRODUCT_CNT_DTO =
			AllProductCntDto.builder()
					.pInsertCnt(10000L)
					.stockCnt(10000L)
					.releaseCnt(10000L)
					.lastReceiveIn(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
					.build();

	public static final WarehouseDto.Post WAREHOUSE_POST = WarehouseDto.Post.builder()
			.wname("이건 임시 창고인데여")
			.wloc("이게 간식 창고라면 얼마나 좋을까")
			.build();

	public static final WarehouseDto.Patch WAREHOUSE_PATCH = WarehouseDto.Patch.builder()
			.oldName("BMW M5")
			.newName("간식창고")
			.build();

	public static final WarehouseDto.Patch WAREHOUSE_PATCH_2 = WarehouseDto.Patch.builder()
			.oldName("BMW M5")
			.oldLoc("Nicaragua")
			.newLoc("이제부터 여긴 제 간식창고입니다")
			.build();

	public static final Warehouse WAREHOUSE_RESPONSE =
			Warehouse.builder()
					.wId(1L)
					.wName("BMW M5")
					.wLoc("Nicaragua")
					.build();

	public static final List<Warehouse> WAREHOUSE_LIST =
			List.of(WAREHOUSE_RESPONSE);

	public static final List<AllReleaseDto> ALLRELEASE =
			List.of(AllReleaseDto.builder()
					.rId(1L)
					.total(42230)
					.quantity(82)
					.releaseAt(LocalDate.of(2022, 9,21))
					.lastid(10)
					.build());

	public static final List<Release> RELEASES =
			List.of(
					Release.builder()
							.rId(1L)
							.total(42230)
							.quantity(82)
							.releaseAt(LocalDate.of(2022, 9, 21))
							.build()
			);

	public static final List<Stock> STOCKS =
			List.of(
					Stock.builder()
							.sId(1L)
							.pId(1L)
							.wId(91L)
							.cnt(41)
							.pInsert(123)
							.receiveIn("2022-09-21")
							.build()
			);
}
