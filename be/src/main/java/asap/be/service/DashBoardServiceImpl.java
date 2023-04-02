package asap.be.service;

import asap.be.dto.DashboardDto.*;
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
	public List<MoneyDto> TotalProductAmount(String startDate, String endDate) {
		//0000-00-00 ~ 0000-00-00
		//release_at 에서 받은 날짜를
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(startDate, formatter);
		LocalDate end = LocalDate.parse(endDate, formatter);

		List<MoneyDto> result = new ArrayList<>();

		for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
			Integer total = releaseMybatisRepository.totalByDate(date.toString());
			if (total == null) total = 0; //그날 출고액이없을경우
			result.add(new MoneyDto(date.toString(), total));
		}

		return result;
	}

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
		List<Map<String, Object>> insertRankList = releaseMybatisRepository.insertRank(date);
		List<Map<String, Object>> releaseRankList = releaseMybatisRepository.releaseCntRank(date);

		return RankDto.builder()
				.insertRankDto(insertRankList)
				.releaseRankDto(releaseRankList)
				.build();
	}
}
