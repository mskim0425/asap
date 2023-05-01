package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
