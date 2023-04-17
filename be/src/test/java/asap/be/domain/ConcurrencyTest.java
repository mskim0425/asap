package asap.be.domain;

import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.facade.RedissonLockServiceFacade;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import asap.be.service.DashBoardService;
import asap.be.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Slf4j
@Transactional
@SpringBootTest
@RequiredArgsConstructor
public class ConcurrencyTest {

    @Autowired
    ProductMybatisRepository productMybatisRepository;
    @Autowired
    ReleaseMybatisRepository releaseMybatisRepository;
    @Autowired
    WarehouseMybatisRepository warehouseMybatisRepository;

    @Autowired
    RedissonLockServiceFacade serviceFacade;
    @Autowired
    PlatformTransactionManager transactionManager;
    TransactionStatus status;

    @BeforeEach
    public void beforeEach() throws InterruptedException {
        //트랜잭션 시작 TODO: 본인 디비에 저장해서 테스트 하슈
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        PostProductDto test_data = PostProductDto.builder()
                .pName("TD")
                .price(100)
                .pCode("TD")
                .wId(10L)
                .pInsert(1000)
                .build();
        serviceFacade.save(test_data);
    }


    @AfterEach
    void afterEach() {
        //트랜잭션 롤백
        transactionManager.rollback(status);
    }

//    @Test
    @DisplayName("1개씩 100번 출고 요청 - Redisson 적용")
    public void AtOnce100_Redisson() throws InterruptedException {
        int threadcnt = 100;
        //given 수량이 1000개인 데이터를 저장한다.
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        PostProductDto releaseData = PostProductDto.builder()
                .pName("TestData")
                .price(100)
                .pCode("TestData")
                .wId(10L)
                .quantity(1)
                .build();
        //when
        for (int i = 0; i < threadcnt; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                serviceFacade.release("releaseLock", releaseData);
            });
            futures.add(future);
       }
        log.info("{}", CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join());

        List<EverythingDto> list = productMybatisRepository.findByName("TestData");
        Assertions.assertThat(list.get(0).getCnt()).isEqualTo(900);
    }
}
