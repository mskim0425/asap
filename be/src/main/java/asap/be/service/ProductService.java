package asap.be.service;

import asap.be.dto.AllProductCntDto;
import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;

import java.util.List;

public interface ProductService {
    void insertOrUpdateStock(PostProductDto dto);

    void updateProduct(EditProductDto dto);

    EverythingDto findById(Long pId, Long sId);

    List<EverythingDto> findByName(String pName);

    List<EverythingDto> findByAll(int startPage, int pageSize);

    AllProductCntDto findAllCntByPId(Long pId);

}
