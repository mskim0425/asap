package asap.be.exception;

import lombok.Getter;

public enum ExceptionCode {
    /*
     * 필요시 추가로 구현하시면 됩니다 에러이름(code, message)
     */
    PRODUCT_NOT_EXISTS(404, "Product not exists"),
    OVER_QUANTITY_THAN_STOCK(400, "Over quantity than stock");


    @Getter
    private int code;
    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

