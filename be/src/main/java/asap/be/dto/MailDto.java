package asap.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MailDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class postEmail {
        private String email;
    }

    @Getter
    @Builder
    public static class checkMail {
        private String code;
        private String email;
    }
}
