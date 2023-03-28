package asap.be.repository;

import asap.be.domain.Product;
import asap.be.domain.Stock;
import asap.be.domain.Warehouse;

import java.util.List;

public class ProductRepository implements ProductMapper{
    @Override
    public void save(Product product, Stock stock, Warehouse warehouse) {

    }

    @Override
    public void delete(long sId) {

    }

    @Override
    public Product name() {
        return null;
    }

    @Override
    public Product price() {
        return null;
    }

    @Override
    public Product barcode() {
        return null;
    }

    @Override
    public Product findbyId() {
        return null;
    }

    @Override
    public Product findbyName() {
        return null;
    }

    @Override
    public List<Product> findbyAll() {
        return null;
    }
}
