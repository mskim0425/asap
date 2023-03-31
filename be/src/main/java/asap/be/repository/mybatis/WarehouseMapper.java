package asap.be.repository.mybatis;

import asap.be.domain.Warehouse;
import asap.be.dto.EverythingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarehouseMapper {
    void wSave(EverythingDto everythingDto);
    void wDelete();
    void wChangeName(@Param("new_wName") String newName, @Param("old_wName") String wName);
    void wChangeLoc(@Param("wLoc") String wLoc, @Param("wName") String wName);
    List<Warehouse> findWarehouseByName(String wName);
    List<Warehouse> findWarehouseByLoc(String wLoc);
}
