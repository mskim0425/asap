package asap.be.repository;

import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.RequestDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRepository {
	void save(PostProductDto productDto);
	void status(RequestDto.UpdatePStatus requestDto);
	void name(RequestDto.UpdatePName requestDto);
	void price(RequestDto.UpdatePrice requestDto);
	void barcode(RequestDto.UpdatePCode requestDto);
	EverythingDto findById(Long pId, Long sId);
	List<EverythingDto> findByName(String pName);
	List<EverythingDto> findByAll();
	Boolean existProductByNameAndWId(String pName, Long wId);
}
