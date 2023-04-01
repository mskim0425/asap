package asap.be.service;

import asap.be.dto.MoneyDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashBoardService{
    private final ProductMybatisRepository productMybatisRepository;
    private final ReleaseMybatisRepository releaseMybatisRepository;
    private final WarehouseMybatisRepository warehouseMybatisRepository;

    @Override
    public List<MoneyDto> TotalProductAmount(String startDate, String endDate) {
        //0000-00-00 ~ 0000-00-00
        //release_at 에서 받은 날짜를
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        List<MoneyDto> result = new ArrayList<>();

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            Integer total = releaseMybatisRepository.totalByDate(date.toString());
            if(total==null) total = 0; //그날 출고액이없을경우
            result.add(new MoneyDto(date.toString(), total));
        }

        return result;
    }
}
