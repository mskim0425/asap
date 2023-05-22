package asap.be.exception;

import lombok.Getter;

public enum ExceptionCode {
    /*
     * 필요시 추가로 구현하시면 됩니다 에러이름(code, message)
     */
    PRODUCT_NOT_EXISTS(404, "Product not exists"),
    OVER_QUANTITY_THAN_STOCK(400, "Over quantity than stock"),
    NEED_IMAGE(404,"Need Image" ),
    MEMBER_EXIST(404, "이미 가입된 이메일입니다. 메일함을 확인하시거나 로그인을 진행해주세요."),
    EMAIL_EXIST(404, "Email exist" );

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

