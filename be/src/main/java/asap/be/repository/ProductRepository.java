package asap.be.repository;

import asap.be.dto.ProductDto;
import asap.be.dto.RequestDto;

import java.util.List;

public interface ProductRepository {
	void save(ProductDto productDto);
	void delete(long pId);
	void name(RequestDto.UpdatePName requestDto);
	void price(RequestDto.UpdatePrice requestDto);
	void barcode(RequestDto.UpdatePCode requestDto);
	ProductDto findById(Long pId);
	List<ProductDto> findByName(String pName);
	List<ProductDto> findByAll();
}
