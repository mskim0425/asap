package asap.be.repository.mybatis;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.EverythingDto;
import asap.be.dto.MoneyDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.ProductCntDto;
import asap.be.dto.YearStatusDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReleaseMapper {
	List<Release> findAll();

	List<Release> findReleaseById(Long sId);

	Stock findStockByPId(Long pId);

	EverythingDto findStockByPNameAndWId(@Param("pName") String pName, @Param("wId") Long wId, @Param("pCode") String pCode);

	List<MoneyDto> totalByDates(String start_date, String end_date);

	List<YearStatusDto> getMonthlyStockSummary(String year);

	List<ProductCntDto> cntProductByDate(@Param("pName") String pName, @Param("start_at") String startAt, @Param("end_at") String endAt);

	List<Map<String, Object>> insertCntRank(String receive_in);

	List<Map<String, Object>> releaseCntRank(String release_at);

	Integer getCnt(@Param("p") PostProductDto dto);
}
