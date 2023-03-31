package asap.be.repository.mybatis;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.EverythingDto;
import asap.be.dto.ReleaseStockDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReleaseMapper {
    Long sSave(EverythingDto everythingDto);
    void release(@Param("s") ReleaseStockDto stockDto);
    void update(@Param("s") ReleaseStockDto stockDto);
    List<Release> findAll();
    List<Release> findReleaseById(Long sId);
    Stock findStockByPId(Long pId);
}
