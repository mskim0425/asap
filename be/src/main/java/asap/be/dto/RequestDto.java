package asap.be.dto;

import lombok.Builder;
import lombok.Getter;

public class RequestDto {

	@Getter
	@Builder
	public static class UpdatePName {
		private Long pId;
		private Long sId;
		private String pName;
	}

	@Getter
	@Builder
	public static class UpdatePrice {
		private Long pId;
		private Long sId;
		private int price;
	}

	@Getter
	@Builder
	public static class UpdatePCode {
		private Long pId;
		private Long sId;
		private String pCode;
	}

	@Getter
	@Builder
	public static class	UpdatePStatus{
		private Long pId;
		private Long sId;
		private int pStatus;
	}
}
