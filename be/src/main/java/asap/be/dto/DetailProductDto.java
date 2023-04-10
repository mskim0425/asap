package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailProductDto {
	private long pId;
	private String pName;
	private String pCode;
	private int price;
	private int pStatus;
}
