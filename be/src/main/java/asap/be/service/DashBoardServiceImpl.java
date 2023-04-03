package asap.be.service;

import asap.be.dto.DashboardDto.*;
import asap.be.dto.CountryDto;
import asap.be.dto.MoneyDto;
import asap.be.dto.YearStatusDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashBoardService {
	private final ProductMybatisRepository productMybatisRepository;
	private final ReleaseMybatisRepository releaseMybatisRepository;
	private final WarehouseMybatisRepository warehouseMybatisRepository;

	@Override
	public List<ProductCntDto> CntProduct(Long pId) {

		List<ProductCntDto> result = new ArrayList<>();

		LocalDate today = LocalDate.now().minusDays(1);
		LocalDate start = today.minusDays(20); // 최근 21일위한

		List<Map<String, Object>> insertCnt = releaseMybatisRepository.insertCnt(pId, start.toString(), today.toString());
		List<Map<String, Object>> releaseCnt = releaseMybatisRepository.releaseCnt(pId, start.toString(), today.toString());

		for (int i = 0; i < insertCnt.size(); i++) {
			ProductCntDto cntDto = ProductCntDto.builder()
					.date(insertCnt.get(i).get("receive_in").toString())
					.insertCnt(insertCnt.get(i).get("pInsert") == null ? 0 : Integer.parseInt(insertCnt.get(i).get("pInsert").toString()))
					.releaseCnt(releaseCnt.get(i).get("pRelease") == null ? 0 : Integer.parseInt(releaseCnt.get(i).get("pRelease").toString()))
					.build();

			result.add(cntDto);
		}

		return result;
	}

	@Override
	public RankDto ProductCntRank() {
		String date = LocalDate.now().toString();
		List<Map<String, Object>> insertRankList = releaseMybatisRepository.insertRank("2022-01-01");
		List<Map<String, Object>> releaseRankList = releaseMybatisRepository.releaseCntRank("2022-01-01");

		return RankDto.builder()
				.insertRankDto(insertRankList)
				.releaseRankDto(releaseRankList)
				.build();
	}

    @Override
    public List<MoneyDto> TotalProductAmount(String startDate, String endDate) {
        //0000-00-00 ~ 0000-00-00 2022-12-31~ 2023-01-15
        //release_at 에서 받은 날짜를
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        List<MoneyDto> result = new ArrayList<>();
        List<MoneyDto> dto = releaseMybatisRepository.totalByDate(startDate, endDate); //결과값이 2022-12-31 : 400개 <<이런식으로 list에 쌓임

        int idx = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            if (idx < dto.size() && dto.get(idx).getReleaseat().equals(date.toString())) { //dto에 해당 날짜값이 있다면
                result.add(new MoneyDto(date.toString(), dto.get(idx).getMoney()));
                idx++;
            } else {
                result.add(new MoneyDto(date.toString(), 0)); //없다면 해당날짜에 0 반환
            }
        }
        // 요청한 기간 내의 출고액 합계를 계산하여 Map 객체에 저장

        return result;
    }

    @Override
    public List<YearStatusDto> getMonthlyStockSummary(String year) {
        return releaseMybatisRepository.getMonthlyStockSummary(year);
    }

    @Override
    public List<CountryDto> getCountryProductStatus() { // 오타...
        return warehouseMybatisRepository.countryStaus(); // 여기둥...
    }
}
