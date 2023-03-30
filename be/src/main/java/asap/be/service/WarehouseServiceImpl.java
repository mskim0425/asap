package asap.be.service;

import asap.be.domain.Warehouse;
import asap.be.dto.ProductDto;
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
    public void wSave(ProductDto productDto) {
        warehouseMybatisRepository.wSave(productDto);
    }

    @Override
    @Transactional
    public void wDelete() {

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
}
