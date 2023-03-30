package asap.be.repository.mybatis;


import asap.be.dto.ProductDto;
import asap.be.dto.RequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    void save(ProductDto productDto); //상품등록
    void status(@Param("p") RequestDto.UpdatePStatus requestDto);
    void name(@Param("p") RequestDto.UpdatePName requestDto);
    void price(@Param("p") RequestDto.UpdatePrice requestDto);
    void barcode(@Param("p") RequestDto.UpdatePCode requestDto);
    ProductDto findById(Long pId);
    List<ProductDto> findByName(String pName);
    List<ProductDto> findByAll();
}

