package asap.be.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostProductDto {
	private Long pId;
	private Long sId;
	private String pName;
	private int price;
	private String pCode;
	private Long wId;
	private int pInsert;
	private int quantity;
	private String date;

	public void addDate(String date) {
		this.date = date;
	}
}
