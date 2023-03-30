package asap.be.repository;

import asap.be.domain.Product;
import asap.be.domain.Stock;
import asap.be.domain.Warehouse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static java.time.LocalTime.now;

@Slf4j
@Transactional
@SpringBootTest
public class productTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReleaseRepository releaseRepository;
    @Autowired
    WarehouseRepository warehouseRepository;


    @Test
    @DisplayName("저장")
    void save(){
        Product product = new Product("test product", 10000, "barcode1");
        Stock stock = new Stock(400, LocalDate.now());
        Warehouse warehouse = new Warehouse("test 창고", "seoul somewhere");

        productRepository.save(product,stock,warehouse);
    }


    @Test
    @DisplayName("상품 삭제")
    void delete(){
        //given
//        productRepository.delete();
    }

}
