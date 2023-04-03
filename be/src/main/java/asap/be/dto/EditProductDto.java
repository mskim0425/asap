package asap.be.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditProductDto {
	private Long pId;
	private Long sId;
	private String pName;
	private Integer price;
	private String pCode;
	private Integer pStatus;
}
