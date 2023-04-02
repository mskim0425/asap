package asap.be.repository.mybatis;

import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
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
    public void save(PostProductDto productDto) {
        productMapper.save(productDto);
    }

    @Override
    public void status(RequestDto.UpdatePStatus requestDto) {
        productMapper.status(requestDto);
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
    public EverythingDto findById(Long pId, Long sId) {
        return productMapper.findById(pId, sId);
    }

    @Override
    public List<EverythingDto> findByName(String pName) {
        return productMapper.findByName(pName);
    }

    @Override
    public List<EverythingDto> findByAll() {
        return productMapper.findByAll();
    }

    @Override
    public Boolean existProductByNameAndWId(String pName, Long wId) {
        return productMapper.existProductByNameAndWId(pName, wId);
    }

}
