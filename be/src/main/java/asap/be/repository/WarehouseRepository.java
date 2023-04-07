package asap.be.repository;

import asap.be.domain.Warehouse;
import asap.be.dto.CountryDto;

import java.util.List;

public interface WarehouseRepository {
	void wSave(String wName, String wLoc);

	void wDelete(Long wId);

	void wChangeName(String newName, String wName);

	void wChangeLoc(String wLoc, String wName);

	List<Warehouse> findWarehouseByName(String wName);

	List<Warehouse> findWarehouseByLoc(String wLoc);

	List<CountryDto> countryStatus();
}
