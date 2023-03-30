package asap.be.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EverythingDto {
    private Long pId;
    private String pName;
    private int price;
    private String pCode;
    private Long sId;
    private int cnt;
    private LocalDate receive_in;
    private Long wId;
    private String wName;
    private String wLoc;

    public EverythingDto(Long pId, String pName, int price, String pCode, Long sId, int cnt, LocalDate receive_in, Long wId, String wName, String wLoc) {
        this.pId = pId;
        this.pName = pName;
        this.price = price;
        this.pCode = pCode;
        this.sId = sId;
        this.cnt = cnt;
        this.receive_in = receive_in;
        this.wId = wId;
        this.wName = wName;
        this.wLoc = wLoc;
    }

    public EverythingDto() {
    }
}
