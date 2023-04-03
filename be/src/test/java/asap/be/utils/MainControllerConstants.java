package asap.be.utils;

import asap.be.domain.Warehouse;
import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;

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
					.pCode("상품 바코드")
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
}
