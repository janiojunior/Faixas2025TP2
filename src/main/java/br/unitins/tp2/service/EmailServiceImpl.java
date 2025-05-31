package br.unitins.tp2.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

@ApplicationScoped
public class EmailServiceImpl implements EmailService {

    @Inject
    Mailer mailer;

    public void enviarEmail(String emailDestinatario, String assunto, String mensagem) {
        mailer.send(
            Mail.withText(emailDestinatario, assunto, mensagem)
        );
    }

}