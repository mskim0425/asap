package asap.be.service;

import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
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
    public void insertOrUpdateStock(PostProductDto dto) {
        productMybatisRepository.insertOrUpdateStock(dto);
    }

    @Override
    public void updateProduct(EditProductDto dto) {
        productMybatisRepository.updateProduct(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public EverythingDto findById(Long pId, Long sId) {
        return productMybatisRepository.findById(pId, sId);
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
