package asap.be.service;

import asap.be.domain.Warehouse;
import asap.be.dto.CountryDto;
import asap.be.dto.DayMaxValueDto;
import asap.be.dto.WarehouseDto;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
	private final WarehouseMybatisRepository warehouseMybatisRepository;

	@Override
	@Transactional
	public void wSave(WarehouseDto.Post dto) {
		warehouseMybatisRepository.wSave(dto);
	}

	@Override
	@Transactional
	public void wDelete(Long wId) {
		warehouseMybatisRepository.wDelete(wId);
	}

	@Override
	@Transactional
	public void wChange(WarehouseDto.Patch dto) {
		warehouseMybatisRepository.wChange(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Warehouse> findWarehouseByName(String wName) {
		return warehouseMybatisRepository.findWarehouseByName(wName);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Warehouse> findWarehouseByLoc(String wLoc) {
		return warehouseMybatisRepository.findWarehouseByLoc(wLoc);
	}

	@Override
	public List<CountryDto> countryStatus() {
		return warehouseMybatisRepository.countryStatus();
	}

	@Override
	public DayMaxValueDto sixData(String date) {
		return warehouseMybatisRepository.sixDate(date);
	}

}
