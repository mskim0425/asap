package asap.be.service;

import asap.be.domain.Warehouse;

public interface WarehouseService {
    void wDelete();
    Warehouse wChangeName();
    Warehouse wChangeLoc();
}
