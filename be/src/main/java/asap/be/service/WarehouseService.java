package asap.be.service;

import asap.be.domain.Warehouse;
import asap.be.dto.CountryDto;
import asap.be.dto.DayMaxValueDto;
import asap.be.dto.EverythingDto;

import java.util.List;

public interface WarehouseService {
	void wSave(EverythingDto everythingDto);

	void wDelete(Long wId);

	void wChangeName(String newName, String wName);

	void wChangeLoc(String wLoc, String wName);

	List<Warehouse> findWarehouseByName(String wName);

	List<Warehouse> findWarehouseByLoc(String wLoc);

	List<CountryDto> countryStatus();

    DayMaxValueDto sixData(String date);
}
