package org.example.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {

    private String senderEmail;
    private String senderPassword;
    private String smtpServer;
    private int smtpPort;

    /**
     * Constructor para configurar los datos del correo
     * @param senderEmail Email del remitente
     * @param senderPassword Contraseña del remitente
     * @param smtpServer Servidor SMTP (ej: smtp.gmail.com)
     * @param smtpPort Puerto SMTP (ej: 587 para TLS, 465 para SSL)
     */
    public EmailService(String senderEmail, String senderPassword, String smtpServer, int smtpPort) {
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
        this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
    }

    /**
     * Envía un correo con un archivo adjunto (PDF)
     * @param recipientEmail Email del destinatario
     * @param subject Asunto del correo
     * @param body Cuerpo del mensaje
     * @param filePath Ruta del archivo a adjuntar
     * @return true si se envió correctamente, false en caso contrario
     */
    public boolean sendEmailWithAttachment(String recipientEmail, String subject, String body, String filePath) {
        try {
            System.out.println("   Conectando con servidor: " + smtpServer + ":" + smtpPort);
            
            // Configurar propiedades del servidor SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpServer);
            props.put("mail.smtp.port", smtpPort);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.connectiontimeout", "5000");
            props.put("mail.smtp.timeout", "5000");

            System.out.println("   Autenticando con: " + senderEmail);
            
            // Crear sesión con autenticación
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            System.out.println("   Adjuntando archivo: " + filePath);
            
            // Crear el contenido con partes (texto + archivo adjunto)
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body, "utf-8");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(filePath);

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Enviar el correo
            System.out.println("   Enviando mensaje a: " + recipientEmail);
            Transport.send(message);

            return true;

        } catch (Exception e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
            System.err.println("Tipo de error: " + e.getClass().getSimpleName());
            return false;
        }
    }

    /**
     * Envía un correo simple sin adjuntos
     * @param recipientEmail Email del destinatario
     * @param subject Asunto del correo
     * @param body Cuerpo del mensaje
     * @return true si se envió correctamente, false en caso contrario
     */
    public boolean sendSimpleEmail(String recipientEmail, String subject, String body) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpServer);
            props.put("mail.smtp.port", smtpPort);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Correo simple enviado a: " + recipientEmail);
            return true;

        } catch (Exception e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
