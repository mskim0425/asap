package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YearStatusDto { //특정년도 1월~12월까지의 전체수량,전체출고량,전체입고량
    private int month;
    private int allQuantity; //해당월 전체 수량
    private int allReleaseAt; //해당월 전체 출고량
    private int allInsert; //해당월 전체 입고량
}
