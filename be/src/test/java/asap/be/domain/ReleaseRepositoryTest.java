package asap.be.domain;

import asap.be.dto.AllReleaseDto;
import asap.be.dto.PostProductDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class ReleaseRepositoryTest {

	@Autowired
	ReleaseMybatisRepository releaseMybatisRepository;
	@Autowired
	ProductMybatisRepository productMybatisRepository;
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
	@DisplayName("전체 조회")
	void findAll() {
		// given
		Integer lastId = null;

		// when
		List<AllReleaseDto> releases = releaseMybatisRepository.findAll(lastId);
		Integer next = releases.get(0).getLastid();
		List<AllReleaseDto> releaseDtos = releaseMybatisRepository.findAll(next);

		// then
		assertThat(releases.size()).isEqualTo(10);
		assertThat(releaseDtos.size()).isEqualTo(10);
	}

	@Test
	@DisplayName("해당 재고의 출고 기록 조회")
	void findBySId() {
		// given
		Long sId = 10L;

		// when
		List<Release> releases = releaseMybatisRepository.findReleaseById(sId);

		// then
	}

	@Test
	@DisplayName("상품 아이디를 통해 재고 조회")
	void findStockByPId() {
		// given
		Product product = Product.builder()
				.pName("상품명")
				.price(10000)
				.pCode("상품바코드")
				.build();

		PostProductDto productDto = PostProductDto.builder()
				.pName(product.getPName())
				.price(product.getPrice())
				.pCode(product.getPCode())
				.wId(1L)
				.pInsert(10) //입고시
				.build();
		productMybatisRepository.insertOrUpdateStock(productDto);

		Long pId = productMybatisRepository.findPIdByPNameAndWId(productDto.getPName(), productDto.getWId());
		Long sId = productMybatisRepository.findSIdByPNameAndWId(productDto.getPName(), productDto.getWId());
		// when
		List<Stock> stock = releaseMybatisRepository.findStockByPId(pId,sId);

		// then
		assertThat(stock.get(0).getPId()).isEqualTo(pId);
	}
}
