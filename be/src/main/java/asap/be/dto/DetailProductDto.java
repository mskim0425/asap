package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
	private int cnt;
	private String pQr;
	private List<DetailWarehouseDto> warehouses;
}
