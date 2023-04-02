package asap.be.domain;

import asap.be.dto.DashboardDto.*;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import asap.be.service.DashBoardService;
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

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Slf4j
@Transactional
@SpringBootTest
class DashBoardServiceImplTest {

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

    @Test
    @DisplayName("날짜와 함께 조회되는지")
    void first_display() {
        List<MoneyDto> moneyDtos = dashBoardService.TotalProductAmount("2023-03-20", "2023-04-01");
        for (MoneyDto dto : moneyDtos) {
            log.info(dto.getDate() + " "+ dto.getMoney());
        }

        Assertions.assertThat(moneyDtos.size()).isEqualTo(13);
    }

    @Test
    @DisplayName("날짜, 입고량, 출고량 조회")
    void cntProduct() {
        // given
        String pName = "Meat";
        Long pId = 2L;

        // when
        List<ProductCntDto> list = dashBoardService.CntProduct(pId);

        for (ProductCntDto dto : list)
            log.info(dto.getDate() + " " + dto.getInsertCnt() + " " + dto.getReleaseCnt());

        // then
        assertThat(list.size()).isEqualTo(21);
    }

    @Test
    @DisplayName("일별 입고량/출고량 TOP 10")
    void top10() {
        RankDto rankDto = dashBoardService.ProductCntRank();
    }

}