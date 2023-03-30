package asap.be.repository.mybatis;

import asap.be.dto.ProductDto;
import asap.be.dto.RequestDto;
import asap.be.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductMybatisRepository implements ProductRepository {
    private final ProductMapper productMapper;

    @Override
    public void save(ProductDto productDto) {
        productMapper.save(productDto);
    }

    @Override
    public void delete(long pId) {
        productMapper.delete(pId);
    }

    @Override
    public void name(RequestDto.UpdatePName requestDto) {
        productMapper.name(requestDto);
    }

    @Override
    public void price(RequestDto.UpdatePrice requestDto) {
        productMapper.price(requestDto);
    }

    @Override
    public void barcode(RequestDto.UpdatePCode requestDto) {
        productMapper.barcode(requestDto);
    }

    @Override
    public ProductDto findById(Long pId) {
        return productMapper.findById(pId);
    }

    @Override
    public List<ProductDto> findByName(String pName) {
        return productMapper.findByName(pName);
    }

    @Override
    public List<ProductDto> findByAll() {
        return productMapper.findByAll();
    }

}
