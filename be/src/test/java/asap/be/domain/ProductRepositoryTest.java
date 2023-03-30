package asap.be.domain;

import asap.be.dto.ProductDto;
import asap.be.dto.RequestDto;
import asap.be.repository.ProductRepository;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
	void save() {
		//given
		Product product = Product.builder()
				.pName("TEST PRODUCT NAME")
				.price(10000)
				.pCode("4534554533")
				.build();
		Stock stock = new Stock(product.getPId(), 10, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		Warehouse warehouse = new Warehouse(product.getPId(), "TEMP WAREHOUSE NAME", "SEOUL");

		ProductDto productDto = ProductDto.builder()
				.pname(product.getPName())
				.price(product.getPrice())
				.pcode(product.getPCode())
				.cnt(stock.getCnt())
				.receiveIn(stock.getReceive_in())
				.wname(warehouse.getWName())
				.wloc(warehouse.getWLoc())
				.build();

		//when
		productMybatisRepository.save(productDto);
		releaseMybatisRepository.sSave(productDto);
		warehouseMybatisRepository.wSave(productDto);

		//then
		ProductDto findProd = productMybatisRepository.findById(product.getPId());
		assertThat(findProd.getPId()).isEqualTo(product.getPId());
	}

	@Test
	void delete() {
//		// given
//		long pId = 1L;
//
//		// when
//		productRepository.delete(pId);
//
//		// then
//		assertThat(productRepository.findById(pId)).isNull();
	}

	@Test
	void updateName() {
		// given
		Long pId = 3L;
		String pName = "NEW NAME TEST";

		RequestDto.UpdatePName updatePName = RequestDto.UpdatePName.builder()
				.pId(pId)
				.pName(pName)
				.build();

		// when
		productMybatisRepository.name(updatePName);

		// then
		assertThat(productMybatisRepository.findById(pId).getPname()).isEqualTo(pName);
	}

	@Test
	void updatePrice() {
		// given
		Long pId = 3L;
		int price = 50000;

		RequestDto.UpdatePrice updatePrice = RequestDto.UpdatePrice.builder()
				.pId(pId)
				.price(price)
				.build();

		// when
		productMybatisRepository.price(updatePrice);

		// then
		assertThat(productMybatisRepository.findById(pId).getPrice()).isEqualTo(price);
	}

	@Test
	void updateBarcode() {
		// given
		Long pId = 3L;
		String pCode = "TEST BARCODE";

		RequestDto.UpdatePCode updatePCode = RequestDto.UpdatePCode.builder()
				.pId(pId)
				.pCode(pCode)
				.build();

		// when
		productMybatisRepository.barcode(updatePCode);

		// then
		assertThat(productMybatisRepository.findById(pId).getPcode()).isEqualTo(pCode);
	}

	@Test
	void findById() {
		// given
		Long pId = 5L;

		// when
		ProductDto productDto = productMybatisRepository.findById(pId);

		// then
		assertThat(productDto.getPId()).isEqualTo(pId);
	}

	@Test
	void findByName() {
		// given
		String pName1 = "asd";
		String pName2 = "Fish";

		// when
		List<ProductDto> wrong = productMybatisRepository.findByName(pName1);
		List<ProductDto> right = productMybatisRepository.findByName(pName2);

		// then
		assertThat(wrong).isNullOrEmpty();
		assertThat(right).isNotNull();
	}

	@Test
	void findAll() {
		// given

		// when
		List<ProductDto> list = productMybatisRepository.findByAll();

		// then
		assertThat(list).isNotNull();
	}

}

