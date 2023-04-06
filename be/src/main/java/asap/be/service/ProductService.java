package asap.be.service;

import asap.be.dto.*;

import java.util.List;

public interface ProductService {
	void insertOrUpdateStock(PostProductDto dto);

	void updateProduct(EditProductDto dto);

	EverythingDto findById(Long pId, Long sId);

	List<EverythingDto> findByName(String pName);

	List<EverythingPageDto> findByAll(Integer lastId);

	AllProductCntDto findAllCntByPId(Long pId);

	List<DetailInfoDto> detailPageUsingPId(Long pId);

}
