package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllProductCntDto {
	private Long pInsertCnt;
	private Long stockCnt;
	private Long releaseCnt;
	private String lastReceiveIn;
}
