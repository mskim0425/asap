package asap.be.service;

import asap.be.dto.CountryDto;
import asap.be.dto.DashboardDto.RankDto;
import asap.be.dto.MoneyDto;
import asap.be.dto.ProductCntDto;
import asap.be.dto.YearStatusDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static asap.be.config.CacheConstant.MONTHLY_SUMMARY;
import static asap.be.config.CacheConstant.RANK_PRODUCT;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashBoardService {
	private final ProductMybatisRepository productMybatisRepository;
	private final ReleaseMybatisRepository releaseMybatisRepository;
	private final WarehouseMybatisRepository warehouseMybatisRepository;

	@Override
	public List<ProductCntDto> CntProduct(String pName) {

		LocalDate today = LocalDate.now();
		LocalDate start = today.minusDays(20); // 오늘 제외한 최근 21일

		List<ProductCntDto> result = new ArrayList<>();
		List<ProductCntDto> dto = releaseMybatisRepository.cntProductByDate(pName, start.toString(), today.toString());

		int i = 0;
		for (LocalDate date = start; !date.isAfter(today); date = date.plusDays(1)) {
			if (i < dto.size() && dto.get(i).getDate().equals(date.toString())) {
				result.add(new ProductCntDto(date.toString(), dto.get(i).getInsertCnt(), dto.get(i).getReleaseCnt()));
				i++;
			} else result.add(new ProductCntDto(date.toString(), 0, 0));
		}

		return result;
	}

	@Override
	@Cacheable(RANK_PRODUCT)
	public RankDto ProductCntRank() {
		String date = LocalDate.now().toString();
		List<Map<String, Object>> insertRankList = releaseMybatisRepository.insertRank(date);
		List<Map<String, Object>> releaseRankList = releaseMybatisRepository.releaseCntRank(date);

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
	@Cacheable(MONTHLY_SUMMARY)
	public List<YearStatusDto> getMonthlyStockSummary(String year) {
		return releaseMybatisRepository.getMonthlyStockSummary(year);
	}

	@Override
	public List<CountryDto> getCountryProductStatus() {
		return warehouseMybatisRepository.countryStatus();
	}

	@Override
	public List<String> showAllPName() {
		return productMybatisRepository.showAllPName();
	}
}
