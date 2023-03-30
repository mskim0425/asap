package asap.be.service;

import asap.be.dto.ProductDto;
import asap.be.dto.RequestDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductMybatisRepository productMybatisRepository;
    @Override
    @Transactional
    public void save(ProductDto dto) {
        productMybatisRepository.save(dto);
    }

    @Override
    @Transactional
    public void delete(RequestDto.UpdatePStatus dto) {
        productMybatisRepository.status(dto);
    }

    @Override
    @Transactional
    public void name(RequestDto.UpdatePName requestDto) {
        productMybatisRepository.name(requestDto);
    }

    @Override
    @Transactional
    public void price(RequestDto.UpdatePrice requestDto) {
        productMybatisRepository.price(requestDto);
    }

    @Override
    @Transactional
    public void barcode(RequestDto.UpdatePCode requestDto) {
         productMybatisRepository.barcode(requestDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long pId) {
        return productMybatisRepository.findById(pId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findByName(String pName) {
        return productMybatisRepository.findByName(pName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findByAll() {
        return productMybatisRepository.findByAll();
    }
}
