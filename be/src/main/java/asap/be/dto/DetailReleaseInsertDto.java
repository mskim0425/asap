package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailReleaseInsertDto {
	private Long rId;
	private String releaseAt;
	private Integer quantity;

	private Long sId;
	private long pid;
	private long wId;
	private String wName;
	private String wLoc;

	private String pInsertLog;
	private int cnt;

}
