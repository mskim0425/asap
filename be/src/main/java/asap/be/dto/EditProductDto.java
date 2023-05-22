package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

	public Long getpId() {
		return pId;
	}

	public Long getsId() {
		return sId;
	}

	public String getPName() {
		return pName;
	}

	public Integer getPrice() {
		return price;
	}

	public String getPCode() {
		return pCode;
	}

	public Integer getPStatus() {
		return pStatus;
	}
}
