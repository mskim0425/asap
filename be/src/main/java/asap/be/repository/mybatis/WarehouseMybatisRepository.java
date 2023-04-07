package asap.be.repository.mybatis;

import asap.be.domain.Warehouse;
import asap.be.dto.CountryDto;
import asap.be.dto.DayMaxValueDto;
import asap.be.dto.EverythingDto;

import asap.be.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class WarehouseMybatisRepository implements WarehouseRepository {
	private final WarehouseMapper warehouseMapper;

	public void wSave(String wName, String wLoc) {
		warehouseMapper.wSave(wName, wLoc);
	}

	@Override
	public void wDelete(Long wId) {
		warehouseMapper.wDelete(wId);
	}

	public void wChangeName(String newName, String wName) {
		warehouseMapper.wChangeName(newName, wName);
	}

	public void wChangeLoc(String wLoc, String wName) {
		warehouseMapper.wChangeLoc(wLoc, wName);
	}

	public List<Warehouse> findWarehouseByName(String wName) {
		return warehouseMapper.findWarehouseByName(wName);
	}

	public List<Warehouse> findWarehouseByLoc(String wLoc) {
		return warehouseMapper.findWarehouseByLoc(wLoc);
	}

	@Override
	public String findWarehouseLocByWId(Long wId) {
		return warehouseMapper.findWarehouseLocByWId(wId);
	}

	@Override
	public List<CountryDto> countryStatus() {
		return warehouseMapper.countryStatus();
	}

	@Override
	public DayMaxValueDto sixDate(String date) {
		DayMaxValueDto dto = new DayMaxValueDto();
		Map<String, String> max_receive_item = max_receive_item(date);
		Map<String, String> max_release_item = max_release_item(date);
		String max_receive_warehouse = max_receive_warehouse(date);
		String max_release_warehouse = max_release_warehouse(date);
		Integer total_pinsert = total_pinsert(date);
		Integer total_pRelease = total_pRelease(date);

		DayMaxValueDto result = dto.builder()
				.max_receive_item(max_receive_item)
				.max_release_item(max_release_item)
				.max_receive_warehouse(max_receive_warehouse)
				.max_release_warehouse(max_release_warehouse)
				.total_pinsert(total_pinsert)
				.total_pRelease(total_pRelease)
				.build();

		return result;
	}
	@Override
	public Map<String, String> max_receive_item(String date) {
		return warehouseMapper.max_receive_item(date);
	}

	@Override
	public Map<String, String> max_release_item(String date) {
		return warehouseMapper.max_release_item(date);
	}

	@Override
	public String max_receive_warehouse(String date) {
		return warehouseMapper.max_receive_warehouse(date);
	}

	@Override
	public String max_release_warehouse(String date) {
		return warehouseMapper.max_release_warehouse(date);
	}

	@Override
	public Integer total_pinsert(String date) {
		return warehouseMapper.total_pinsert(date);
	}

	@Override
	public Integer total_pRelease(String date) {
		return warehouseMapper.total_pRelease(date);
	}

}
