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

//    public Product(String pName, int price, String pCode) {
//        this.pName = pName;
//        this.price = price;
//        this.pCode = pCode;
//    }
}
