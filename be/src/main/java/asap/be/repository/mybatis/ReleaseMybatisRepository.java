package asap.be.repository.mybatis;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.EverythingDto;
import asap.be.dto.ReleaseStockDto;
import asap.be.repository.ReleaseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReleaseMybatisRepository implements ReleaseRepository {
    private final ReleaseMapper releaseMapper;

    @Override
    public void sSave(EverythingDto everythingDto) {
        releaseMapper.sSave(everythingDto);
    }

    @Override
    public Integer cnt(Long pId) {
        return releaseMapper.cnt(pId);
    }

    @Override
    public void release(ReleaseStockDto stockDto) {
        releaseMapper.release(stockDto) ;
    }

    @Override
    public void update(ReleaseStockDto stockDto) {
        releaseMapper.update(stockDto);
    }

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
    public Integer totalByDate(String receiveIn) {
        return releaseMapper.totalByDate(receiveIn);
    }

    @Override
    public List<Map<String, Object>> insertCnt(Long pId, String startAt, String endAt) {
        return releaseMapper.insertCnt(pId, startAt, endAt);
    }

    @Override
    public List<Map<String, Object>> releaseCnt(Long pId, String startAt, String endAt) {
        return releaseMapper.releaseCnt(pId, startAt, endAt);
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
