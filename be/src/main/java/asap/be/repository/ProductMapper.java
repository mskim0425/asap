package asap.be.repository;

import asap.be.domain.Product;
import asap.be.domain.Stock;
import asap.be.domain.Warehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface ProductMapper {
    void save(Product product, Stock stock, Warehouse warehouse); //상품등록
    void delete(long sId);
    Product name();
    Product price();
    Product barcode();
    Product findbyId();
    Product findbyName();
    List<Product> findbyAll();
}

