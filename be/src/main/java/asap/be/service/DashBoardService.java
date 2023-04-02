package asap.be.service;

import asap.be.dto.CountryDto;
import asap.be.dto.MoneyDto;
import asap.be.dto.YearStatusDto;

import java.util.List;

public interface DashBoardService {
    List<MoneyDto> TotalProductAmount(String startDate, String endDate);
    List<YearStatusDto> getMonthlyStockSummary(String year);
    List<CountryDto> getCountryProductStauts();
}
