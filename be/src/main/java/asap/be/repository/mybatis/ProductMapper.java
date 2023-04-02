package asap.be.repository.mybatis;


import asap.be.dto.EverythingDto;
import asap.be.dto.ProductUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    void save(EverythingDto everythingDto); //상품등록
    void status(@Param("p") ProductUpdateDto.UpdatePStatus requestDto);
    void name(@Param("p") ProductUpdateDto.UpdatePName requestDto);
    void price(@Param("p") ProductUpdateDto.UpdatePrice requestDto);
    void barcode(@Param("p") ProductUpdateDto.UpdatePCode requestDto);
    EverythingDto findById(Long pId);
    List<EverythingDto> findByName(String pName);
    List<EverythingDto> findByAll();
}

