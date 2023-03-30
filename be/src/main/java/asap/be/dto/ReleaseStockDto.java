package asap.be.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class ReleaseStockDto {
	private Long pId;
	private int quantity;
}
