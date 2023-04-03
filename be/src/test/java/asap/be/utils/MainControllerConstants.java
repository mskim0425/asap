package asap.be.utils;

import asap.be.domain.Warehouse;
import asap.be.dto.*;
import asap.be.dto.DashboardDto.ProductCntDto;

import java.time.LocalDate;
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
					.quantity(30) // 출고 수량
					.build();

	public static final EditProductDto EDIT_PRODUCT_NAME =
			EditProductDto.builder()
					.pId(1L)
					.sId(1L)
					.pName("새로 변경할 상품명")
					.build();

	public static final EditProductDto EDIT_PRODUCT_PRICE =
			EditProductDto.builder()
					.pId(1L)
					.sId(1L)
					.price(5000)
					.build();

	public static final EditProductDto EDIT_PRODUCT_BARCODE =
			EditProductDto.builder()
					.pId(1L)
					.sId(1L)
					.pCode("새로운 상품 바코드")
					.build();

	public static final EditProductDto EDIT_ALL =
			EditProductDto.builder()
					.pId(1L)
					.sId(1L)
					.pName("새로 변경할 상품명")
					.price(2000)
					.pCode("새로 변경할 바코드")
					.build();

	public static final EditProductDto DELETE_PRODUCT =
			EditProductDto.builder()
					.pId(1L)
					.sId(1L)
					.pStatus(0)
					.build();

	public static final Warehouse WAREHOUSE =
			Warehouse.builder()
					.wId(1L)
					.wName("창고명")
					.wLoc("창고 위치")
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

	public static final EverythingDto SAVE_EVERYTHING_DTO =
			EverythingDto.builder()
					.pId(1L)
					.pname(SAVE_AND_RECEIVE_PRODUCT.getPName())
					.price(SAVE_AND_RECEIVE_PRODUCT.getPrice())
					.pcode(SAVE_AND_RECEIVE_PRODUCT.getPCode())
					.sId(1L)
					.cnt(SAVE_AND_RECEIVE_PRODUCT.getPInsert())
					.receiveIn(LocalDate.now().toString())
					.wId(SAVE_AND_RECEIVE_PRODUCT.getWId())
					.wname(WAREHOUSE.getWName())
					.wloc(WAREHOUSE.getWLoc())
					.pStatus(1)
					.build();
}
