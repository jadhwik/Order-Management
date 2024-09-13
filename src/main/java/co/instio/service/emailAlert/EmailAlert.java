package co.instio.service.emailAlert;

import co.instio.entity.Users;
import co.instio.enums.CommonErrorCodeEnum;
import co.instio.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailAlert {

    private final JavaMailSender mailSender;
    public void sendEmailAlert(String oldEmail,Users users) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        try {
            mailMessage.setTo("adhwikjagadeesh@gmail.com");
            mailMessage.setSubject("Simple mail message regarding user information updation");
            mailMessage.setText("This is to inform you that your user details have been updated with userName a " + users.getUserName() +"  email from "+oldEmail+ "  to email as  " + users.getEmail());
            mailSender.send(mailMessage);
        }
        catch (Exception e){
            log.error("Failed to send email");
            throw  new ServiceException(CommonErrorCodeEnum.EXPECTATION_FAILED);
        }
    }
}
