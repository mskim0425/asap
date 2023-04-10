package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailInsertLogsDto {
	private String receiveIn;
	private Integer pInsert;
	private String wName;
	private String wLoc;
}
