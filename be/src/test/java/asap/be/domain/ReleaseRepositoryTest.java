package asap.be.domain;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
public class ReleaseRepositoryTest {

	@Autowired
	ReleaseMybatisRepository releaseMybatisRepository;
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
	@DisplayName("전체조회")
	void findAll() {
		// given

		// when

		// then
	}
}
