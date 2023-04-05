package asap.be.repository.mybatis;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.EverythingDto;
import asap.be.dto.MoneyDto;
import asap.be.dto.ProductCntDto;
import asap.be.dto.YearStatusDto;
import asap.be.repository.ReleaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReleaseMybatisRepository implements ReleaseRepository {
    private final ReleaseMapper releaseMapper;

    @Override
    public List<Release> findAll() {
        return releaseMapper.findAll();
    }

    @Override
    public List<Release> findReleaseById(Long sId) {
        return releaseMapper.findReleaseById(sId);
    }

    @Override
    public Stock findStockByPId(Long pId) {
        return releaseMapper.findStockByPId(pId);
    }

    @Override
    public EverythingDto findStockByPNameAndWId(String pName, Long wId, String pCode) {
        return releaseMapper.findStockByPNameAndWId(pName, wId, pCode);
    }

    @Override
    public List<MoneyDto> totalByDate(String start, String end) {
        return releaseMapper.totalByDates(start, end);
    }

    @Override
    public List<YearStatusDto> getMonthlyStockSummary(String year) {
        return releaseMapper.getMonthlyStockSummary(year);
    }

    @Override
    public List<ProductCntDto> cntProductByDate(Long pId, String startAt, String endAt) {
        return releaseMapper.cntProductByDate(pId, startAt, endAt);
    }

    @Override
    public List<Map<String, Object>> insertRank(String receive_in) {
        return releaseMapper.insertCntRank(receive_in);
    }

    @Override
    public List<Map<String, Object>> releaseCntRank(String release_at) {
        return releaseMapper.releaseCntRank(release_at);
    }

}
