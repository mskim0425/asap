package asap.be.service;

import asap.be.dto.EverythingDto;
import asap.be.dto.ProductUpdateDto;
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
    public void save(EverythingDto dto) {
        productMybatisRepository.save(dto);
    }

    @Override
    @Transactional
    public void delete(ProductUpdateDto.UpdatePStatus dto) {
        productMybatisRepository.status(dto);
    }

    @Override
    @Transactional
    public void name(ProductUpdateDto.UpdatePName requestDto) {
        productMybatisRepository.name(requestDto);
    }

    @Override
    @Transactional
    public void price(ProductUpdateDto.UpdatePrice requestDto) {
        productMybatisRepository.price(requestDto);
    }

    @Override
    @Transactional
    public void barcode(ProductUpdateDto.UpdatePCode requestDto) {
         productMybatisRepository.barcode(requestDto);
    }

    @Override
    @Transactional(readOnly = true)
    public EverythingDto findById(Long pId) {
        return productMybatisRepository.findById(pId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EverythingDto> findByName(String pName) {
        return productMybatisRepository.findByName(pName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EverythingDto> findByAll() {
        return productMybatisRepository.findByAll();
    }
}
