package asap.be.repository;

import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;

import java.util.List;

public interface ProductRepository {
	void insertOrUpdateStock(PostProductDto dto);

	void updateProduct(EditProductDto dto);

	EverythingDto findById(Long pId, Long sId);

	List<EverythingDto> findByName(String pName);

	List<EverythingDto> findByAll();

}
