package org.example.util;

/**
 * Clase de configuración para los datos del correo
 * IMPORTANTE: Reemplaza estos valores con tus propios datos
 */
public class EmailConfig {
    
    // ============ CONFIGURAR AQUÍ TUS DATOS ============
    
    /**
     * Tu correo de gmail.com
     * Ej: "tuusuario@gmail.com"
     */
    public static final String SENDER_EMAIL = "jhordanpineda88@gmail.com";
    
    /**
     * Contraseña de aplicación de Gmail
     * 1. Ve a https://myaccount.google.com/apppasswords
     * 2. Selecciona "Correo" y "Windows (u otro dispositivo)"
     * 3. Copia la contraseña generada aquí
     * Ej: "qnwv rvls qunx fayq"
     */
    public static final String SENDER_PASSWORD = "qnwv rvls qunx fayq";
    
    /**
     * Servidor SMTP según tu proveedor:
     * - Gmail: smtp.gmail.com
     * - Outlook: smtp-mail.outlook.com
     * - Yahoo: smtp.mail.yahoo.com
     */
    public static final String SMTP_SERVER = "smtp.gmail.com";
    
    /**
     * Puerto SMTP:
     * - 587 para TLS
     * - 465 para SSL
     */
    public static final int SMTP_PORT = 587;
    
    /**
     * Email de prueba para enviar reportes
     */
    public static final String DEFAULT_RECIPIENT = "jhordanpineda88@gmail.com";
    
    // ================================================
    
    public static boolean isConfigured() {
        return !SENDER_EMAIL.contains("tu_email") && 
               !SENDER_PASSWORD.contains("tu_contraseña");
    }
}

