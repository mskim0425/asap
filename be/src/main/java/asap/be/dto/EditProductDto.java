package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProductDto {
	private Long pId;
	private Long sId;
	private String pName;
	private Integer price;
	private String pCode;
	private Integer pStatus;
}
