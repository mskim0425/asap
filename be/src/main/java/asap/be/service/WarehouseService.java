package asap.be.service;

import asap.be.domain.Warehouse;
import asap.be.dto.CountryDto;
import asap.be.dto.DayMaxValueDto;
import asap.be.dto.WarehouseDto;

import java.util.List;

public interface WarehouseService {
	void wSave(WarehouseDto.Post dto);

	void wDelete(Long wId);

	void wChange(WarehouseDto.Patch dto);

	List<Warehouse> findWarehouseByName(String wName);

	List<Warehouse> findWarehouseByLoc(String wLoc);

	List<CountryDto> countryStatus();

    DayMaxValueDto sixData(String date);
}
