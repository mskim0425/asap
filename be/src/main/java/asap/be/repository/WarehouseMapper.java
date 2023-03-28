package asap.be.repository;

import asap.be.domain.Warehouse;

public interface WarehouseMapper {
    void wDelete();
    Warehouse wChangeName();
    Warehouse wChangeLoc();
}
