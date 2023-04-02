package asap.be.service;

import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.RequestDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
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
    public void save(PostProductDto dto) {
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

    @Override
    public Boolean existProductByNameAndWId(String pName, Long wId) {
        return productMybatisRepository.existProductByNameAndWId(pName, wId);
    }
}
