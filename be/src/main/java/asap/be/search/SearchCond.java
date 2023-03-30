package asap.be.search;

import lombok.Data;

@Data
public class SearchCond {
    private String pName;
    private int price;
    private String pCode;

    public SearchCond() {
    }

    public SearchCond(String pName, int price, String pCode) {
        this.pName = pName;
        this.price = price;
        this.pCode = pCode;
    }
}
