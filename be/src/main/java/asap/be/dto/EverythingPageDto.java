package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EverythingPageDto {
	private Long pId;
	private String pname;
	private int price;
	private String pcode;
	private Integer lastid;
}