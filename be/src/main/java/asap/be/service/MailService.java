package asap.be.service;

public interface MailService {
	void verifyEmail(String email);

	void setVerifiedEmail(String email);

	void sendCertificationMail(String email);

	void sendMail(String code, String email) throws Exception;
}
