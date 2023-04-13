package asap.be.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Long sId;
    private Long pId;
    private Long wId;
    private int cnt;
    private String receiveIn;
    private int pInsert;

    public Stock(Long sId, Long wId, String receiveIn, int pInsert) {
        this.sId = sId;
        this.wId = wId;
        this.receiveIn = receiveIn;
        this.pInsert = pInsert;
    }
}
