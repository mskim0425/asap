package asap.be.service;

import asap.be.domain.Release;
import asap.be.domain.Stock;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.ReleaseStockDto;

import java.util.List;

public interface ReleaseService {
    Integer cnt(Long pId);
    void release(ReleaseStockDto stockDto);
    void update(ReleaseStockDto stockDto);
    List<Release> findAll();
    List<Release> findReleaseById(Long sId);
    Stock findStockByPId(Long pId);
    void updateStock(PostProductDto dto);
    EverythingDto findStockByPNameAndWId(String pName, Long wId);
}
