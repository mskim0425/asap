package asap.be.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Long sId;
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
//    public Stock(int cnt, LocalDateTime receive_in) {
//        this.cnt = cnt;
//        this.receive_in = receive_in;
//    }
}
