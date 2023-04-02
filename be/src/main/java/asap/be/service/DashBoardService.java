package asap.be.service;

import asap.be.dto.DashboardDto.*;

import java.util.List;

public interface DashBoardService {
    List<MoneyDto> TotalProductAmount(String startDate, String endDate);

    // pName 기반 시 중복 상품명 존재로 조회 불가.. pId를 통해 조회해야함
    List<ProductCntDto> CntProduct(Long pId);

    RankDto ProductCntRank();
}
