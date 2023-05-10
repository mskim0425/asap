package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostProductDto {

	private String pName;
	private int price;
	private String pCode;
	private Long wId;
	private int pInsert;
	private String date;

	private Long pId;
	private Long sId;

	private int quantity;

	public String getpName() {
		return pName;
	}

	public int getPrice() {
		return price;
	}

	public String getpCode() {
		return pCode;
	}

	public Long getwId() {
		return wId;
	}

	public int getpInsert() {
		return pInsert;
	}

	public String getDate() {
		return date;
	}

	public Long getpId() {
		return pId;
	}

	public Long getsId() {
		return sId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void addDate(String date) {
		this.date = date;
	}

}
