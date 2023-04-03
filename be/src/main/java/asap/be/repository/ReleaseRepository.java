package asap.be.repository;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.EverythingDto;
import asap.be.dto.MoneyDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.ReleaseStockDto;
import asap.be.dto.YearStatusDto;

import java.util.List;
import java.util.Map;

public interface ReleaseRepository {
	Integer cnt(Long pId);

	void release(ReleaseStockDto stockDto);

	void update(ReleaseStockDto stockDto);

	List<Release> findAll();

	List<Release> findReleaseById(Long sId);

	Stock findStockByPId(Long pId);

	EverythingDto findStockByPNameAndWId(String pName, Long wId);

	List<MoneyDto> totalByDate(String start, String end);

	List<YearStatusDto> getMonthlyStockSummary(String year);

	List<Map<String, Object>> insertCnt(Long pId, String startAt, String endAt);

	List<Map<String, Object>> releaseCnt(Long pId, String startAt, String endAt);

	List<Map<String, Object>> insertRank(String receive_in);

	List<Map<String, Object>> releaseCntRank(String release_at);

	void updateStock(PostProductDto productDto);
}
