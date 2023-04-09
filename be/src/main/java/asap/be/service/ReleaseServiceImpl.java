package asap.be.service;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.AllReleaseDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
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
	@Transactional(readOnly = true)
	public List<AllReleaseDto> findAll(Integer lastId) {
		return releaseMybatisRepository.findAll(lastId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Release> findReleaseById(Long sId) {
		return releaseMybatisRepository.findReleaseById(sId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Stock> findStockByPId(Long pId) {
		return releaseMybatisRepository.findStockByPId(pId);
	}

	@Override
	@Transactional(readOnly = true)
	public EverythingDto findStockByPNameAndWId(String pName, Long wId, String pCode) {
		return releaseMybatisRepository.findStockByPNameAndWId(pName, wId, pCode);
	}

	@Override
	public Integer getCnt(PostProductDto dto) {
		return releaseMybatisRepository.getCnt(dto);
	}

}
