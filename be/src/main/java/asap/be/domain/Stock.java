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
    private String receive_in;
    private int pInsert;

    public Stock(Long sId, Long wId, String receive_in, int pInsert) {
        this.sId = sId;
        this.wId = wId;
        this.receive_in = receive_in;
        this.pInsert = pInsert;
    }
}
