package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EverythingDto {
	private Long pId;
	private String pname;
	private int price;
	private String pcode;
	private Long sId;
	private int cnt;
	private String receiveIn;
	private Long wId;
	private String wname;
	private String wloc;
	private int pStatus;
}