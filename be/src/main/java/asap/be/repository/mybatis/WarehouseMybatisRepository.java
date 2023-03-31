package asap.be.repository.mybatis;

import asap.be.domain.Warehouse;
import asap.be.dto.EverythingDto;
import asap.be.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WarehouseMybatisRepository implements WarehouseRepository {
    private final WarehouseMapper warehouseMapper;

    public void wSave(EverythingDto everythingDto) {
        warehouseMapper.wSave(everythingDto);
    }

    public void wDelete() {

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
}
