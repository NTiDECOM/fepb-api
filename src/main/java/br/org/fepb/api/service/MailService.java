package br.org.fepb.api.service;

import br.org.fepb.api.domain.Inscricao;
import br.org.fepb.api.domain.Pessoa;
import br.org.fepb.api.domain.User;

import io.github.jhipster.config.JHipsterProperties;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * Service for sending emails.
 * <p>
 * We use the @Async annotation to send emails asynchronously.
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private static final String USER = "user";

    private static final String PESSOA = "pessoa";

    private static final String NOME_COORDENADOR = "nomeCoordenador";

    private static final String CIDADE = "cidade";

    private static final String INSTITUICAO = "instituicao";

    private static final String BASE_URL = "baseUrl";

    private final JHipsterProperties jHipsterProperties;

    private final JavaMailSender javaMailSender;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    public MailService(JHipsterProperties jHipsterProperties, JavaMailSender javaMailSender,
            MessageSource messageSource, SpringTemplateEngine templateEngine) {

        this.jHipsterProperties = jHipsterProperties;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml, File attachment) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(jHipsterProperties.getMail().getFrom(), "AJE 2019");
            message.setSubject(subject);
            message.setText(content, isHtml);
            if (attachment != null) {
                message.addAttachment(attachment.getName(), attachment);
            }
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }

    @Async
    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(user.getEmail(), subject, content, false, true, null);

    }

    @Async
    public void sendSuccessEmailFromTemplate(Inscricao i, String templateName, String titleKey) {
        Context context = new Context();
        context.setVariable(PESSOA, i.getPessoa());
        context.setVariable(BASE_URL, "http://app-aje.fepb.org.br/pagamento/" + i.getId());
        String content = templateEngine.process(templateName, context);
        String subject = "INSCRIÇÃO - AJE 2019";
        sendEmail(i.getPessoa().getEmail(), subject, content, false, true, null);
    }

    @Async
    public void sendCoordinatorEmailFromTemplate(Inscricao i, String templateName, String titleKey) {
        Context context = new Context();
        context.setVariable(NOME_COORDENADOR, i.getNomeCoordenador());
        context.setVariable(PESSOA, i.getPessoa());
        context.setVariable(CIDADE, i.getCidade().getNome() + " (" + i.getCidade().getEstado().getUf() + ")");
        context.setVariable(INSTITUICAO, i.getInstituicao());
        context.setVariable(BASE_URL, "http://app-aje.fepb.org.br/validacao/" + i.getId());
        String content = templateEngine.process(templateName, context);
        String subject = "APROVAR INSCRIÇÃO - AJE 2019";
        sendEmail(i.getEmailCoordenador(), subject, content, false, true, null);
    }

    @Async
    public void sendAutorizacaoEmailFromTemplate(Pessoa p, String templateName, String titleKey) {
        Context context = new Context();
        context.setVariable(PESSOA, p);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = "AUTORIZAÇÃO DE MENOR - AJE 2019";

        File attachment = new File("src/main/java/br/org/fepb/api/reports/docs/autorizacao_menor_aje.docx");
        sendEmail(p.getEmail(), subject, content, true, true, attachment);
    }

    @Async
    public void sendActivationEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "mail/activationEmail", "email.activation.title");
    }

    @Async
    public void sendCreationEmail(User user) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "mail/creationEmail", "email.activation.title");
    }

    @Async
    public void sendPasswordResetMail(User user) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "mail/passwordResetEmail", "email.reset.title");
    }

    @Async
    public void sendSuccessMail(Inscricao i) {
        log.debug("Sending success email to '{}'", i.getPessoa().getEmail());
        sendSuccessEmailFromTemplate(i, "mail/successEmail", "email.reset.title");
    }

    @Async
    public void sendCoordenadorMail(Inscricao i) {
        log.debug("Sending coordinator email to '{}'", i.getEmailCoordenador());
        sendCoordinatorEmailFromTemplate(i, "mail/coordinatorEmail", "email.reset.title");
    }

    @Async
    public void sendAutorizacaoMail(Pessoa p) {
        log.debug("Sending autorizacao email to '{}'", p.getEmail());
        sendAutorizacaoEmailFromTemplate(p, "mail/autorizacao", "email.reset.title");
    }
}
