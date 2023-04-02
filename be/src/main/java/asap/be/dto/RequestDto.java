package asap.be.dto;

import lombok.Builder;
import lombok.Getter;

public class RequestDto {

	@Getter
	@Builder
	public static class UpdatePName {
		private Long pId;
		private String pName;
	}

	@Getter
	@Builder
	public static class UpdatePrice {
		private Long pId;
		private int price;
	}

	@Getter
	@Builder
	public static class UpdatePCode {
		private Long pId;
		private String pCode;
	}

	@Getter
	@Builder
	public static class	UpdatePStatus{
		private Long pId;
		private int pStatus;
	}
}
