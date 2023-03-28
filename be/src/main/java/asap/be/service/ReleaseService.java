package asap.be.service;

import asap.be.domain.Stock;
import asap.be.dto.ReleaseStockDto;

public interface ReleaseService {
    ReleaseStockDto release();
    Stock update();
}
