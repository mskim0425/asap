package asap.be.service;

import asap.be.domain.Product;
import asap.be.domain.Stock;
import asap.be.domain.Warehouse;
import asap.be.dto.ReleaseStockDto;

import java.util.List;

public class MainServiceV1 implements ProductService, ReleaseService, WarehouseService{
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

    @Override
    public ReleaseStockDto release() {
        return null;
    }

    @Override
    public Stock update() {
        return null;
    }

    @Override
    public void wDelete() {

    }

    @Override
    public Warehouse wChangeName() {
        return null;
    }

    @Override
    public Warehouse wChangeLoc() {
        return null;
    }
}
