package asap.be.domain;

import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.RequestDto;
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
	@DisplayName("프로덕트 저장")
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
				.wId(1L)
//				.cnt(cnt==null ? 0 : cnt)
				.pInsert(10)
//				.receiveIn(stock.getReceive_in())
				.build();


		//when
		productMybatisRepository.save(productDto);
		releaseMybatisRepository.sSave(productDto);

		//then
		EverythingDto findProd = productMybatisRepository.findById(productDto.getPId(), productDto.getSId());
		assertThat(findProd.getPId()).isEqualTo(productDto.getPId());
//		assertThat(findProd.getCnt()).isEqualTo(productDto.getCnt()+10);
	}

	@Test
	@DisplayName("삭제")
	void delete() {
		// given
		int status = 0;
		Long pId = 1L;
		Long sId = 1L;

		RequestDto.UpdatePStatus build =
		RequestDto.UpdatePStatus.builder()
				.pId(1L)
				.sId(1L)
				.pStatus(status).build();

		// when
		productMybatisRepository.status(build);

		// then
		assertThat(productMybatisRepository.findById(pId, sId).getPStatus()).isEqualTo(0);
	}

	@Test
	@DisplayName("이름 수정")
	void updateName() {
		// given
		Long pId = 3L;
		Long sId = 3L;
		String pName = "NEW NAME TEST";

		RequestDto.UpdatePName updatePName = RequestDto.UpdatePName.builder()
				.pId(pId)
				.sId(sId)
				.pName(pName)
				.build();

		// when
		productMybatisRepository.name(updatePName);

		// then
		assertThat(productMybatisRepository.findById(pId, sId).getPname()).isEqualTo(pName);
	}

	@Test
	@DisplayName("가격 수정")
	void updatePrice() {
		// given
		Long pId = 3L;
		Long sId = 3L;
		int price = 50000;

		RequestDto.UpdatePrice updatePrice = RequestDto.UpdatePrice.builder()
				.pId(pId)
				.sId(sId)
				.price(price)
				.build();

		// when
		productMybatisRepository.price(updatePrice);

		// then
		assertThat(productMybatisRepository.findById(pId, sId).getPrice()).isEqualTo(price);
	}

	@Test
	@DisplayName("바코드 수정")
	void updateBarcode() {
		// given
		Long pId = 3L;
		Long sId = 3L;
		String pCode = "TEST BARCODE";

		RequestDto.UpdatePCode updatePCode = RequestDto.UpdatePCode.builder()
				.pId(pId)
				.sId(sId)
				.pCode(pCode)
				.build();

		// when
		productMybatisRepository.barcode(updatePCode);

		// then
		assertThat(productMybatisRepository.findById(pId, sId).getPcode()).isEqualTo(pCode);
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

