package asap.be.service;

import asap.be.domain.Product;
import asap.be.domain.Stock;
import asap.be.domain.Warehouse;

import java.util.List;

public interface ProductService {
    void save(Product product, Stock stock, Warehouse warehouse); //상품등록
    void delete(long sId);
    Product name();
    Product price();
    Product barcode();
    Product findbyId();
    Product findbyName();
    List<Product> findbyAll();
}
