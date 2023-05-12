package asap.be.service;

import asap.be.dto.AllProductCntDto;
import asap.be.dto.DetailInfoDto;
import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.EverythingPageDto;
import asap.be.dto.PostProductDto;
import com.google.zxing.WriterException;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface ProductService {
	void insertOrUpdateStock(PostProductDto dto, HttpSession session) throws IOException, WriterException;

	void updateProduct(EditProductDto dto);

	EverythingDto findById(Long pId, Long sId);

	List<EverythingDto> findByName(String pName);

	List<EverythingPageDto> findByAll(Integer lastId, String order);

	AllProductCntDto findAllCntByPName(String pName);

	DetailInfoDto detailPageUsingPId(Long pId);

	Long findPIdByPNameAndWId(String pName, Long wId);

	DetailInfoDto editDetailPage(Long pId, EditProductDto dto);

	Long findByUUID(String uuid);

	void saveS3ImageUrl(String imageURL, Long pId);
}
