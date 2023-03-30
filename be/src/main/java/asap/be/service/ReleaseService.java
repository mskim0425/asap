package asap.be.service;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.ProductDto;
import asap.be.dto.ReleaseStockDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReleaseService {
    void sSave(ProductDto productDto);
    void release(ReleaseStockDto stockDto);
    void update(ReleaseStockDto stockDto);
    List<Release> findAll();
    List<Release> findReleaseById(Long sId);
    Stock findStockByPId(Long pId);
}
