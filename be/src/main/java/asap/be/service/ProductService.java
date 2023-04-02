package asap.be.service;

import asap.be.dto.EverythingDto;
import asap.be.dto.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    void save(EverythingDto everythingDto); //상품등록
    void delete(ProductUpdateDto.UpdatePStatus requestDto);
    void name(ProductUpdateDto.UpdatePName requestDto);
    void price(ProductUpdateDto.UpdatePrice requestDto);
    void barcode(ProductUpdateDto.UpdatePCode requestDto);
    EverythingDto findById(Long pId);
    List<EverythingDto> findByName(String pName);
    List<EverythingDto> findByAll();

}
