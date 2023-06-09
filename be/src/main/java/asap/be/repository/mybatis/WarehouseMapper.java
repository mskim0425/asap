package asap.be.repository.mybatis;

import asap.be.domain.Warehouse;
import asap.be.dto.CountryDto;
import asap.be.dto.WarehouseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WarehouseMapper {
	void wSave(@Param("w") WarehouseDto.Post dto);

	void wDelete(Long wId);

	void wChange(@Param("w") WarehouseDto.Patch dto);

	List<Warehouse> findWarehouseByName(String wName);

	List<Warehouse> findWarehouseByLoc(String wLoc);

	List<CountryDto> countryStatus();

	Map<String, String> max_receive_item(String date);

	Map<String, String> max_release_item(String date);

	String findWarehouseLocByWId(Long wId);

	String max_receive_warehouse(String date);

	String max_release_warehouse(String date);

	Integer total_pinsert(String date);

	Integer total_pRelease(String date);


}
