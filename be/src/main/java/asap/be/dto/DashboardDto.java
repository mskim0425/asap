package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

public class DashboardDto {

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RankDto {
		private List<Map<String, Object>> insertRankDto;
		private List<Map<String, Object>> releaseRankDto;
	}
}

