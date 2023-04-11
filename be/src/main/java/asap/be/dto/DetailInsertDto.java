package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailInsertDto {
	private String pInsertLog;
	private String wName;
	private String wLoc;
	private int cnt;
}
