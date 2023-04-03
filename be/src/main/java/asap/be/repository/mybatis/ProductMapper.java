package asap.be.repository.mybatis;

import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    void insertOrUpdateStock(@Param("p") PostProductDto dto); // 상품 등록 및 입고/출고

    void updateProduct(@Param("p") EditProductDto dto);

    EverythingDto findById(@Param("pId") Long pId, @Param("sId") Long sId);

    List<EverythingDto> findByName(String pName);

    List<EverythingDto> findByAll();

}

