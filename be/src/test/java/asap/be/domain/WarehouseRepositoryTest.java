package asap.be.domain;

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

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
public class WarehouseRepositoryTest {

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
	void changeWName() {
		// given
		String newName = "NEW WNAME";
		String wName = "coral";

		// when
		warehouseMybatisRepository.wChangeName(newName, wName);

		// then
		assertThat(warehouseMybatisRepository.findWarehouseByName(newName)).isNotNull();
	}

	@Test
	void changeWLoc() {
		// given
		String wLoc = "NEW LOCATION";
		String wName = "coral";

		// when
		warehouseMybatisRepository.wChangeLoc(wLoc, wName);

		// then
		assertThat(warehouseMybatisRepository.findWarehouseByLoc(wLoc)).isNotNull();
	}

	@Test
	void findByName() {
		// given
		String wName = "coral";

		// when
		List<Warehouse> list = warehouseMybatisRepository.findWarehouseByName(wName);

		// then
		assertThat(list).isNotNull();
	}
}
