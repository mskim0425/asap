package asap.be.service;

import asap.be.dto.MoneyDto;

import java.util.List;

public interface DashBoardService {
    List<MoneyDto> TotalProductAmount(String startDate, String endDate);
}
