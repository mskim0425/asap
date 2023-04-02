package asap.be.repository;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.EverythingDto;
import asap.be.dto.ReleaseStockDto;

import java.util.List;
import java.util.Map;

public interface ReleaseRepository {
	void sSave(EverythingDto everythingDto);

	Integer cnt(Long pId);

	void release(ReleaseStockDto stockDto);

	void update(ReleaseStockDto stockDto);

	List<Release> findAll();

	List<Release> findReleaseById(Long sId);

	Stock findStockByPId(Long pId);

	Integer totalByDate(String receiveIn);

	List<Map<String, Object>> insertCnt(Long pId, String startAt, String endAt);

	List<Map<String, Object>> releaseCnt(Long pId, String startAt, String endAt);

	List<Map<String, Object>> insertRank(String receive_in);

	List<Map<String, Object>> releaseCntRank(String release_at);
}
