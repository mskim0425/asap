package asap.be.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Release {
    private Long rId;
    private int quantity;
    private int total;
    private LocalDate releaseAt;
}
