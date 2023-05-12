package asap.be.service;

public interface MailService {
    public void verifyEmail(String email);

    public void setVerifiedEmail(String email);

    public void sendCertificationMail(String email);

    public void sendMail(String code, String email) throws Exception;
}
