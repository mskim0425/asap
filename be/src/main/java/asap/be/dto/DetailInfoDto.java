package asap.be.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DetailInfoDto {
    private DetailProductDto product;
    private List<DetailInsertLogsDto> insertLogs;
    private List<DetailReleaseDto> releaseLogs;

}
