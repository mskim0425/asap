package asap.be.service;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.ReleaseStockDto;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReleaseServiceImpl implements ReleaseService {
    private final ReleaseMybatisRepository releaseMybatisRepository;

    @Override
    public Integer cnt(Long pId) {
        return releaseMybatisRepository.cnt(pId);
    }

    @Override
    @Transactional
    public void release(ReleaseStockDto stockDto) {
        releaseMybatisRepository.release(stockDto);
    }

    @Override
    @Transactional
    public void update(ReleaseStockDto stockDto) {
        releaseMybatisRepository.update(stockDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Release> findAll() {
        return releaseMybatisRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Release> findReleaseById(Long sId) {
        return releaseMybatisRepository.findReleaseById(sId);
    }

    @Override
    @Transactional(readOnly = true)
    public Stock findStockByPId(Long pId) {
        return releaseMybatisRepository.findStockByPId(pId);
    }

    @Override
    public void updateStock(PostProductDto dto) {
        releaseMybatisRepository.updateStock(dto);
    }

    @Override
    public EverythingDto findStockByPNameAndWId(String pName, Long wId) {
        return releaseMybatisRepository.findStockByPNameAndWId(pName, wId);
    }
}
