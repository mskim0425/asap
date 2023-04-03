package asap.be.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Product {
    private Long pId;
    private String pName;
    private int price;
    private String pCode;
    private int pStatus;
}
