package asap.be.repository;

import asap.be.dto.AllProductCntDto;
import asap.be.dto.DetailInsertDto;
import asap.be.dto.DetailProductDto;
import asap.be.dto.DetailReleaseDto;
import asap.be.dto.DetailWarehouseDto;
import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.EverythingPageDto;
import asap.be.dto.PostProductDto;

import java.util.List;

public interface ProductRepository {

	void insertOrUpdateStock(PostProductDto dto);

	void insertOrUpdateRelease(PostProductDto dto);

	void updateProduct(EditProductDto dto);

	EverythingDto findById(Long pId, Long sId);

	List<EverythingDto> findByName(String pName);

	List<EverythingPageDto> findByAll(Integer lastId, String order);

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

	Long findByUUID(String uuid);

	void saveS3ImageUrl(String imageURL, Long pId);

	List<EverythingPageDto> search(Integer lastId, String query, String order);

	String findLastReceiveIn(String uuid);
}
