package br.unitins.tp2.service;

public interface EmailService {

    void enviarEmail(String emailDestinatario, String assunto, String mensagem);

} 