package asap.be.repository;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.EverythingDto;
import asap.be.dto.ReleaseStockDto;

import java.util.List;

public interface ReleaseRepository {
	void sSave(EverythingDto everythingDto);

	Integer cnt(Long pId);
	void release(ReleaseStockDto stockDto);

	void update(ReleaseStockDto stockDto);

	List<Release> findAll();

	List<Release> findReleaseById(Long sId);

	Stock findStockByPId(Long pId);
}
