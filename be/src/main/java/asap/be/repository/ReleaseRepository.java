package asap.be.repository;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.AllReleaseDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.MoneyDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.ProductCntDto;
import asap.be.dto.YearStatusDto;

import java.util.List;
import java.util.Map;

public interface ReleaseRepository {

	List<AllReleaseDto> findAll(Integer lastId);

	List<Release> findReleaseById(Long sId);

	List<Stock> findStockByPId(Long pId, Long sId);

	EverythingDto findStockByPNameAndWId(String pName, Long wId, String pCode);

	List<MoneyDto> totalByDate(String start, String end);

	List<YearStatusDto> getMonthlyStockSummary(String year);

	List<ProductCntDto> cntProductByDate(String pName, String startAt, String endAt);

	List<Map<String, Object>> insertRank(String receive_in);

	List<Map<String, Object>> releaseCntRank(String release_at);

	Integer getCnt(PostProductDto dto);
}
