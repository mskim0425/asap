package asap.be.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayMaxValueDto {
    private Map<String, String> max_receive_item;
    private Map<String, String> max_release_item;
    private String max_receive_warehouse;
    private String max_release_warehouse;
    private Integer total_pinsert;
    private Integer total_pRelease;
}
