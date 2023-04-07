package asap.be.service;

import asap.be.domain.Warehouse;
import asap.be.dto.CountryDto;
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
	public void wSave(String wName, String wLoc) {
		warehouseMybatisRepository.wSave(wName, wLoc);
	}

	@Override
	@Transactional
	public void wDelete(Long wId) {
		warehouseMybatisRepository.wDelete(wId);
	}

	@Override
	@Transactional
	public void wChangeName(String newName, String wName) {
		warehouseMybatisRepository.wChangeName(newName, wName);
	}

	@Override
	@Transactional
	public void wChangeLoc(String wLoc, String wName) {
		warehouseMybatisRepository.wChangeLoc(wLoc, wName);
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

}
