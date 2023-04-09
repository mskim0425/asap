package asap.be.domain;

import asap.be.dto.WarehouseDto;
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
		WarehouseDto.Patch patch =
				WarehouseDto.Patch.builder()
						.oldName("coral")
						.newName("NEW NAME")
						.build();

		// when
		warehouseMybatisRepository.wChange(patch);

		// then
		assertThat(warehouseMybatisRepository.findWarehouseByName(patch.getNewName())).isNotNull();
	}

	@Test
	void changeWLoc() {
		// given
		WarehouseDto.Patch patch =
				WarehouseDto.Patch.builder()
						.oldName("BMW M5")
						.newLoc("NEW LOCATION")
						.build();

		// when
		warehouseMybatisRepository.wChange(patch);

		// then
		assertThat(warehouseMybatisRepository.findWarehouseByLoc(patch.getNewLoc())).isNotNull();
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

	@Test
	void wSave() {
		// given
		String wName = "새로운 창고";
		String wLoc = "새로운 위치";

		WarehouseDto.Post dto = WarehouseDto.Post.builder()
				.wname(wName)
				.wloc(wLoc)
				.build();

		// when
		warehouseMybatisRepository.wSave(dto);

		// then
		assertThat(warehouseMybatisRepository.findWarehouseByName(wName)).isNotNull();
	}

	@Test
	void wDelete() {
		// given
		Long wId = 101L;
		String wName = "새로운 창고";

		// when
		warehouseMybatisRepository.wDelete(wId);

		// then
		assertThat(warehouseMybatisRepository.findWarehouseByName(wName).size()).isEqualTo(0);
	}
}
