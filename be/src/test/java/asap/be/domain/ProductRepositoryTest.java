package asap.be.domain;

import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

import static asap.be.utils.MainControllerConstants.*;
import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class ProductRepositoryTest {

	@Autowired
	ProductMybatisRepository productMybatisRepository;
	@Autowired
	ReleaseMybatisRepository releaseMybatisRepository;
	@Autowired
	WarehouseMybatisRepository warehouseMybatisRepository;
    @Autowired
	PlatformTransactionManager transactionManager;
    TransactionStatus status;

    @BeforeEach
    void beforeEach() {
        //트랜잭션 시작
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }


	@AfterEach
	void afterEach() {
		//트랜잭션 롤백
		transactionManager.rollback(status);
	}

	@Test
	@DisplayName("상품 저장 및 입고/출고")
	void save() {
		//given
		Product product = Product.builder()
				.pName("TEST PRODUCT NAME")
				.price(10000)
				.pCode("4534554533")
				.build();

		PostProductDto productDto = PostProductDto.builder()
				.pName(product.getPName())
				.price(product.getPrice())
				.pCode(product.getPCode())
				.wId(2L)
				.pInsert(10) //입고시
//				.quantity(100) //출고시
				.build();

		PostProductDto notExist = PostProductDto.builder()
				.pName("새로운 상품")
				.price(100000)
				.pCode("testing code").wId(5L).pInsert(10000).build();

		PostProductDto same = PostProductDto.builder()
				.pName("새로운 상품")
				.price(100000)
				.pCode("testing code").wId(5L).pInsert(10000).build();


		//when
		productMybatisRepository.insertOrUpdateStock(notExist);
		EverythingDto lastProd = releaseMybatisRepository.findStockByPNameAndWId(notExist.getPName(),notExist.getWId(), notExist.getPCode()); //만개가 저장되고

		productMybatisRepository.insertOrUpdateStock(same); // 상품 최초 저장 및 입고/출고 시 원큐에 끝나여
		EverythingDto sameProd = releaseMybatisRepository.findStockByPNameAndWId(same.getPName(),same.getWId(), same.getPCode()); //만개가 한번더 저장되고

		//then
		assertThat(lastProd.getPname()).isEqualTo(notExist.getPName()); //입고저장
		assertThat(sameProd.getCnt()).isEqualTo(lastProd.getCnt() + same.getPInsert()); //재고끼리 더하기가 됐는지
//		assertThat(findProd.getCnt()).isEqualTo(productDto.getCnt()+10);
	}

	@Test
	@DisplayName("삭제 및 상품 정보 변경")
	void editProduct() {

		// 이름 변경
		productMybatisRepository.updateProduct(EDIT_PRODUCT_NAME);

		EverythingDto findProd1 = productMybatisRepository.findById(EDIT_PRODUCT_NAME.getPId(), EDIT_PRODUCT_NAME.getSId());
		assertThat(findProd1.getPname()).isEqualTo(EDIT_PRODUCT_NAME.getPName());

		// 가격 변경
		productMybatisRepository.updateProduct(EDIT_PRODUCT_PRICE);

		EverythingDto findProd2 = productMybatisRepository.findById(EDIT_PRODUCT_PRICE.getPId(), EDIT_PRODUCT_PRICE.getSId());
		assertThat(findProd2.getPrice()).isEqualTo(findProd2.getPrice());

		// 바코드 변경
		productMybatisRepository.updateProduct(EDIT_PRODUCT_BARCODE);

		EverythingDto findProd3 = productMybatisRepository.findById(EDIT_PRODUCT_BARCODE.getPId(), EDIT_PRODUCT_BARCODE.getSId());
		assertThat(findProd3.getPcode()).isEqualTo(findProd3.getPcode());

		// 전체 변경
		productMybatisRepository.updateProduct(EDIT_ALL);

		EverythingDto findProd4 = productMybatisRepository.findById(EDIT_ALL.getPId(), EDIT_ALL.getSId());
		assertThat(findProd4.getPname()).isEqualTo(EDIT_ALL.getPName());
		assertThat(findProd4.getPrice()).isEqualTo(EDIT_ALL.getPrice());
		assertThat(findProd4.getPcode()).isEqualTo(EDIT_ALL.getPCode());

		// 상태 삭제로 변경
		productMybatisRepository.updateProduct(DELETE_PRODUCT);

		EverythingDto findProd5 = productMybatisRepository.findById(DELETE_PRODUCT.getPId(), DELETE_PRODUCT.getSId());
		assertThat(findProd5.getPStatus()).isEqualTo(0);
	}

	@Test
	@DisplayName("아이디로 찾기")
	void findById() {
		// given
		Long pId = 5L;
		Long sId = 5L;

		// when
		EverythingDto everythingDto = productMybatisRepository.findById(pId, sId);

		// then
		assertThat(everythingDto.getPId()).isEqualTo(pId);
	}

	@Test
	@DisplayName("이름으로 찾기")
	void findByName() {
		// given
		String pName1 = "asd";
		String pName2 = "Fish";

		// when
		List<EverythingDto> wrong = productMybatisRepository.findByName(pName1);
		List<EverythingDto> right = productMybatisRepository.findByName(pName2);

		// then
		assertThat(wrong).isNullOrEmpty();
		assertThat(right).isNotNull();
	}

	@Test
	@DisplayName("전부 찾기")
	void findAll() {
		// given
		int startPage = 10, pageSize= 10; //10-20

		List<EverythingDto> list = productMybatisRepository.findByAll(startPage, startPage+pageSize);

		// then
		assertThat(list.size()).isEqualTo(10);
	}

}

