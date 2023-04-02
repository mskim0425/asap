package asap.be.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReleaseStockDto {
	private Long pId;
	private Long sId;
	private int quantity;
}
