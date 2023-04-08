package asap.be.service;

import asap.be.dto.CountryDto;
import asap.be.dto.ProductCntDto;
import asap.be.dto.DashboardDto.RankDto;
import asap.be.dto.MoneyDto;
import asap.be.dto.YearStatusDto;

import java.util.List;

public interface DashBoardService {
	List<MoneyDto> TotalProductAmount(String startDate, String endDate);

	List<ProductCntDto> CntProduct(String pName);

	RankDto ProductCntRank();

	List<YearStatusDto> getMonthlyStockSummary(String year);

	List<CountryDto> getCountryProductStatus();
	List<String> showAllPName();

}
