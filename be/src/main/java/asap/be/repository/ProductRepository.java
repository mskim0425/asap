package asap.be.repository;

import asap.be.dto.*;

import java.util.List;

public interface ProductRepository {

	void insertOrUpdateStock(PostProductDto dto);

	void insertOrUpdateRelease(PostProductDto dto);

	void updateProduct(EditProductDto dto);

	EverythingDto findById(Long pId, Long sId);

	List<EverythingDto> findByName(String pName);

	List<EverythingPageDto> findByAll(Integer lastId);

	AllProductCntDto findAllCntByPName(String pName);

	List<DetailReleaseDto> detailReleaseUsingPId(Long pId);

	List<DetailInsertDto> detailInsertUsingPId(Long pId);

	Boolean verifiedProduct(Long pId, Long sId);

	Boolean checkExistence(String pName);

	Long findPIdByPNameAndWId(String pName, Long wId);

	List<String> showAllPName();

	DetailProductDto findProductById(Long pId);

	Long findSIdByPNameAndWId(String pName, Long wId);

	List<DetailWarehouseDto> findProductWarehouseById(Long pId);
}
