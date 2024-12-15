package co.edu.iudigital.helmelud.services;

public interface IEmailService {

    boolean sendEmail(String mensaje, String email, String asunto);
}
