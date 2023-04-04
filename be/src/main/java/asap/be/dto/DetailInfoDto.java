package asap.be.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailInfoDto {

    private Long rId;
    private String release_at;
    private Integer quantity;

    private Long sId;
    private long pId;
    private long wId;
    private String receive_in;
    private String wName;
    private String wLoc;
    private String pName;
    private String pCode;

    private int pInsert;
    private int cnt;
    private int price;
    private int pStatus;

}
