package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class WarehouseDto {

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Post {
		private String wname;
		private String wloc;
	}

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Patch {
		private String newLoc;
		private String oldLoc;
		private String newName;
		private String oldName;
	}
}