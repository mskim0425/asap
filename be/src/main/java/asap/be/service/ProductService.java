package asap.be.service;

import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.RequestDto;

import java.util.List;

public interface ProductService {
    void save(PostProductDto productDto); //상품등록
    void delete(RequestDto.UpdatePStatus requestDto);
    void name(RequestDto.UpdatePName requestDto);
    void price(RequestDto.UpdatePrice requestDto);
    void barcode(RequestDto.UpdatePCode requestDto);
    EverythingDto findById(Long pId, Long sId);
    List<EverythingDto> findByName(String pName);
    List<EverythingDto> findByAll();
    Boolean existProductByNameAndWId(String pName, Long wId);
}
