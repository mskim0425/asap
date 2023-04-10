package asap.be.service;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.AllReleaseDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;

import java.util.List;

public interface ReleaseService {
	List<AllReleaseDto> findAll(Integer lastId);

	List<Release> findReleaseById(Long sId);

	List<Stock> findStockByPId(Long pId);

	EverythingDto findStockByPNameAndWId(String pName, Long wId, String pCode);

	Integer getCnt(PostProductDto dto);
}
