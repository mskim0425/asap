package asap.be.domain;

import asap.be.dto.DayMaxValueDto;
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

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class DashBoardServiceImplTest2 {

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

    PostProductDto productDto;

    PostProductDto dummyDto;

    @BeforeEach
    void beforeEach() {
        //트랜잭션 시작
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        //given
        Product product = Product.builder()
                .pName("TEST PRODUCT NAME")
                .price(10000)
                .pCode("4534554533")
                .build();

        productDto = PostProductDto.builder()
                .pName(product.getPName())
                .price(product.getPrice())
                .pCode(product.getPCode())
                .wId(10L)
                .pInsert(100000)
                .date("1992-04-25")
                .build();

        Product dummy = Product.builder()
                .pName("DUMY PRODUCT NAME")
                .price(1000)
                .pCode("3333333")
                .build();

        dummyDto = PostProductDto.builder()
                .pName(dummy.getPName())
                .price(dummy.getPrice())
                .pCode(dummy.getPCode())
                .wId(14L)
                .pInsert(500)
                .date("1992-04-25")
                .build();

        //when
        productMybatisRepository.insertOrUpdateStock(productDto); // 상품 최대 수용개수 삽입
        productMybatisRepository.insertOrUpdateStock(dummyDto);
    }


    @AfterEach
    void afterEach() {
        //트랜잭션 롤백
        transactionManager.rollback(status);
    }


    @Test
    @DisplayName("최대 입고 아이템과 이에 따른 창고 위치")
    void max_receive_item(){

        //then
        Map<String, String> data = warehouseMybatisRepository.max_receive_item("1992-04-25");
        assertThat(data.get("item")).isEqualTo(productDto.getPName());
        assertThat(data.get("item")).isNotEqualTo(dummyDto.getPName());

    }
    @Test
    @DisplayName("최대 입고 아이템과 이에 따른 창고 위치")
    void max_release_item(){
        //given
        productDto = PostProductDto.builder()
                .pName(productDto.getPName())
                .price(productDto.getPrice())
                .pCode(productDto.getPCode())
                .wId(10L)
                .quantity(10000)
                .date("1992-04-25")
                .build();

        dummyDto = PostProductDto.builder()
                .pName(dummyDto.getPName())
                .price(dummyDto.getPrice())
                .pCode(dummyDto.getPCode())
                .wId(14L)
                .quantity(500)
                .date("1992-04-25")
                .build();
        //when
        productMybatisRepository.insertOrUpdateRelease(productDto); // 상품 최대 수용개수 삽입
        productMybatisRepository.insertOrUpdateRelease(dummyDto);

        //then
        Map<String, String> data = warehouseMybatisRepository.max_release_item("1992-04-25");
        assertThat(data.get("item")).isEqualTo(productDto.getPName());
        assertThat(data.get("item")).isNotEqualTo(dummyDto.getPName());
    }

    @Test
    @DisplayName("최대 입고 창고 위치")
    void max_receive_warehouse(){
        String maxWLoc = warehouseMybatisRepository.max_receive_warehouse("1992-04-25");
        String warehouseLocByWId = warehouseMybatisRepository.findWarehouseLocByWId(productDto.getWId());
        assertThat(maxWLoc).isEqualTo(warehouseLocByWId);
    }
    @Test
    @DisplayName("최대 출고 창고 위치")
    void max_release_warehouse(){
        //given
        productDto = PostProductDto.builder()
                .pName(productDto.getPName())
                .price(productDto.getPrice())
                .pCode(productDto.getPCode())
                .wId(10L)
                .quantity(10000)
                .date("1992-04-25")
                .build();

        dummyDto = PostProductDto.builder()
                .pName(dummyDto.getPName())
                .price(dummyDto.getPrice())
                .pCode(dummyDto.getPCode())
                .wId(14L)
                .quantity(500)
                .date("1992-04-25")
                .build();
        //when
        productMybatisRepository.insertOrUpdateRelease(productDto); // 상품 최대 수용개수 삽입
        productMybatisRepository.insertOrUpdateRelease(dummyDto);
        String maxWLoc = warehouseMybatisRepository.max_release_warehouse("1992-04-25");
        String warehouseLocByWId = warehouseMybatisRepository.findWarehouseLocByWId(productDto.getWId());
        assertThat(maxWLoc).isEqualTo(warehouseLocByWId);
    }

    @Test
    @DisplayName("총 입고량")
    void total_pInsert(){
        Integer total_pinsert = warehouseMybatisRepository.total_pinsert("1992-04-25");
        assertThat(total_pinsert).isEqualTo(productDto.getPInsert() + dummyDto.getPInsert());
    }
    @Test
    @DisplayName("총 출고량")
    void total_pRelease(){
        Integer total_pRelease = warehouseMybatisRepository.total_pRelease("1992-04-25");
        assertThat(total_pRelease).isEqualTo(0);
    }

    @Test
    @DisplayName("6개 전부다 측정")
    void six_values_test(){
        //given
        productDto = PostProductDto.builder()
                .pName(productDto.getPName())
                .price(productDto.getPrice())
                .pCode(productDto.getPCode())
                .wId(10L)
                .quantity(10000)
                .date("1992-04-25")
                .build();

        dummyDto = PostProductDto.builder()
                .pName(dummyDto.getPName())
                .price(dummyDto.getPrice())
                .pCode(dummyDto.getPCode())
                .wId(14L)
                .quantity(500)
                .date("1992-04-25")
                .build();
        //when
        productMybatisRepository.insertOrUpdateRelease(productDto); // 상품 최대 수용개수 삽입
        productMybatisRepository.insertOrUpdateRelease(dummyDto);

        DayMaxValueDto dto = warehouseMybatisRepository.sixDate("1992-04-25");
        assertThat(dto.getMax_receive_item()).isNotNull();
        assertThat(dto.getMax_release_item()).isNotNull();

        assertThat(dto.getMax_release_warehouse()).isNotNull();
        assertThat(dto.getMax_receive_warehouse()).isNotNull();

        assertThat(dto.getTotal_pinsert()).isNotNull();
        assertThat(dto.getTotal_pRelease()).isNotNull();
    }
}
