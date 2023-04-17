package asap.be.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailWarehouseDto {
	private Long wId;
	private String wName;
}
