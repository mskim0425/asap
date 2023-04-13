package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailReleaseDto {
	private String releaseAt;
	private int quantity;
	private String wName;
	private String wLoc;
}
