package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllReleaseDto {
	private Long rId;
	private int quantity;
	private int total;
	private LocalDate releaseAt;
	private Integer lastid;
}
