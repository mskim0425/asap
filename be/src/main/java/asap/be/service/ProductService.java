package asap.be.service;

import asap.be.domain.Product;
import asap.be.domain.Stock;
import asap.be.domain.Warehouse;
import asap.be.dto.ProductDto;
import asap.be.dto.RequestDto;

import java.util.List;

public interface ProductService {
    void save(ProductDto productDto); //상품등록
    void delete(long pId);
    void name(RequestDto.UpdatePName requestDto);
    void price(RequestDto.UpdatePrice requestDto);
    void barcode(RequestDto.UpdatePCode requestDto);
    ProductDto findById(Long pId);
    List<ProductDto> findByName(String pName);
    List<ProductDto> findByAll();
}
