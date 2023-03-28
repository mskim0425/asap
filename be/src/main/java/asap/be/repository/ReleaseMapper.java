package asap.be.repository;

import asap.be.domain.Stock;
import asap.be.dto.ReleaseStockDto;

public interface ReleaseMapper {
    ReleaseStockDto release();
    Stock update();
}
