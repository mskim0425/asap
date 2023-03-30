package asap.be.repository;

import asap.be.domain.Warehouse;
import asap.be.dto.ProductDto;

import java.util.List;

public interface WarehouseRepository {
	void wSave(ProductDto productDto);

	void wDelete();

	void wChangeName(String newName, String wName);

	void wChangeLoc(String wLoc, String wName);

	List<Warehouse> findWarehouseByName(String wName);

	List<Warehouse> findWarehouseByLoc(String wLoc);
}
