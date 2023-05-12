package asap.be.service;

import asap.be.exception.BusinessLogicException;
import asap.be.exception.ExceptionCode;
import asap.be.repository.mybatis.MemberMybatisRepository;
import asap.be.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
    private final RedisUtil redisUtil;
    private final JavaMailSender javaMailSender;
    private final MemberMybatisRepository memberMybatisRepository;
    private final RedisTemplate<String, String> redisTemplate;


    @Override
    @Async("threadPoolTaskExecutor - Mail")
    public void sendCertificationMail(String email) {
        try {
            String code = UUID.randomUUID().toString().substring(0, 6); // 랜덤한 6자리 코드 생성
            verifyHasCode(email);
            sendMail(code, email);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BusinessLogicException(ExceptionCode.EMAIL_EXIST);
        }
    }

    /* 인증 번호는 존재하지만 인증 안된 이메일인 경우 */
    private void verifyHasCode(String email) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(email))) {
            redisTemplate.delete(email);
        }
    }

    @Override
    public void sendMail(String code, String email) throws Exception {
        try {
            MimeMessage mimeMessage = createMessage(code, email);
            javaMailSender.send(mimeMessage);
        } catch (MailException mailException) {
            mailException.printStackTrace();
            throw new IllegalAccessException();
        }

        redisUtil.setDataExpire(email, code, 60 * 5L); // 인증번호 5분간 유효
    }

    private MimeMessage createMessage(String code, String email) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

        String url = "https://soonerthebetter.site/api/check/"+code +"!"+ email;
        helper.setTo(email);
        helper.setSubject("ASAP 이메일 인증");
        helper.setText("<br>안녕하세요, ASAP 가족이 되신걸 환영합니다</br>" +
                "<br>ASAP를 정상적으로 이용하기 위해서는 이메일 인증이 필요합니다</br>" +
                "아래의 링크를 누르시면 인증이 완료 됩니다." + url, true);
        helper.setFrom( "mins402kim@gmail.com" , "ASAP 관리자" );

        return message;
    }


    /**
     * 인증번호 전송전에 이미 가입한 이메일여부 확인
     * @param email
     */
    @Override
    public void verifyEmail(String email) {
        if(!memberMybatisRepository.findByEmail(email))
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXIST);
    }

    //인증된 객체를 레디스에 저장
    @Override
    public void setVerifiedEmail(String email) {
        redisUtil.deleteData(email);
        memberMybatisRepository.updateVerified(email);
    }


}
