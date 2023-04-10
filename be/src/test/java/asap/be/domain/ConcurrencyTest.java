package asap.be.domain;

import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import asap.be.service.DashBoardService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
public class ConcurrencyTest {

    @Autowired
    ProductMybatisRepository productMybatisRepository;
    @Autowired
    ReleaseMybatisRepository releaseMybatisRepository;
    @Autowired
    WarehouseMybatisRepository warehouseMybatisRepository;
    @Autowired
    DashBoardService dashBoardService;
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


//    @Test
    @DisplayName("동시에 100개 요청 - 적용")
    public synchronized void AtOnce100() throws InterruptedException {
        int threadcnt = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32); //비동기를 실행시켜주는 자바의 API
        CountDownLatch latch = new CountDownLatch(threadcnt);//100개의 요청을 기다려야함 다른 스레드에서 완성될때까지 대기하는 클래스

        for (int i = 0; i < threadcnt; i++) {
            executorService.submit(() -> {
                PostProductDto test = PostProductDto.builder()
                        .pName("테스트값")
                        .price(500)
                        .pCode("테스트 코드")
                        .wId(10L)
                        .pInsert(10)
                        .date("1992-04-25")
                        .build();
                try { productMybatisRepository.insertOrUpdateStock(test);
                } finally {
                    latch.countDown();
                }
            });
        } latch.await();//100개 끝날떄까지 기다려

        List<EverythingDto> list = productMybatisRepository.findByName("테스트값");
        log.info(" 사이즈 "+ String.valueOf(list.size()));
    }

}
