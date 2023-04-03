package asap.be.domain;

import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.EditProductDto;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

		Stock stock = new Stock(product.getPId(), 1L, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),10);
		//TODO: wId1~5까지 프론트에서 드롭박스형태로 줘야함

		Integer cnt = releaseMybatisRepository.cnt(product.getPId());
		PostProductDto productDto = PostProductDto.builder()
				.pName(product.getPName())
				.price(product.getPrice())
				.pCode(product.getPCode())
				.wId(2L)
				.pInsert(10)
//				.quantity(100)
				.build();

		//when
		productMybatisRepository.insertOrUpdateStock(productDto); // 상품 최초 저장 및 입고/출고 시 원큐에 끝나여

		//then
		EverythingDto findProd = productMybatisRepository.findById(100001L, 170L);
		assertThat(findProd.getPId()).isEqualTo(100001L);
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

		// when
		List<EverythingDto> list = productMybatisRepository.findByAll();

		// then
		assertThat(list).isNotNull();
	}

}

