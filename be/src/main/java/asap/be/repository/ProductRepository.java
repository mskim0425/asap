package asap.be.repository;

import asap.be.dto.EverythingDto;
import asap.be.dto.RequestDto;

import java.util.List;

public interface ProductRepository {
	void save(EverythingDto everythingDto);
	void status(RequestDto.UpdatePStatus requestDto);
	void name(RequestDto.UpdatePName requestDto);
	void price(RequestDto.UpdatePrice requestDto);
	void barcode(RequestDto.UpdatePCode requestDto);
	EverythingDto findById(Long pId);
	List<EverythingDto> findByName(String pName);
	List<EverythingDto> findByAll();
}
