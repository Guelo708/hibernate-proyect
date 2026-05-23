# CONFIGURACIÓN PARA ENVIAR PDF POR CORREO

## 📝 Pasos para configurar (IMPORTANTE)

### 1. Abre el archivo:
   `src/main/java/org/example/util/EmailConfig.java`

### 2. Configura tus datos:

```java
// ============ CONFIGURAR AQUÍ TUS DATOS ============

// Tu correo de Gmail
public static final String SENDER_EMAIL = "tuusuario@gmail.com";

// Contraseña de aplicación (VER PASO 3)
public static final String SENDER_PASSWORD = "tu_contraseña_app";

// Servidor SMTP (ya está configurado para Gmail)
public static final String SMTP_SERVER = "smtp.gmail.com";

// Puerto (ya está configurado)
public static final int SMTP_PORT = 587;

// Email donde enviar los reportes de prueba
public static final String DEFAULT_RECIPIENT = "email_destino@gmail.com";
```

### 3. Obtener la contraseña de aplicación de Gmail:

1. Ve a: **https://myaccount.google.com/apppasswords**
2. Selecciona:
   - **Correo** (en el dropdown)
   - **Windows/Linux/Mac** (en el segundo dropdown)
3. Haz clic en **Generar**
4. Copia la contraseña que aparece (formato: abcd efgh ijkl mnop)
5. Pégala en `SENDER_PASSWORD` del archivo EmailConfig.java
6. Guarda el archivo

### 4. Para otros proveedores:

**Outlook/Hotmail:**
```java
public static final String SENDER_EMAIL = "tuusuario@outlook.com";
public static final String SENDER_PASSWORD = "tu_contraseña";
public static final String SMTP_SERVER = "smtp-mail.outlook.com";
public static final int SMTP_PORT = 587;
```

**Yahoo:**
```java
public static final String SENDER_EMAIL = "tuusuario@yahoo.com";
public static final String SENDER_PASSWORD = "tu_contraseña_app";
public static final String SMTP_SERVER = "smtp.mail.yahoo.com";
public static final int SMTP_PORT = 587;
```

## 🧪 Cómo verificar que funciona:

1. Ejecuta la aplicación
2. Selecciona opción **5** (Gestionar Facturas)
3. Selecciona opción **4** (Generar PDF y enviar por correo)
4. Selecciona opción **1** (Enviar por correo)
5. Ingresa un email válido
6. Si ves el mensaje **"✅ Correo enviado exitosamente"**, ¡funcionó!
7. Revisa la bandeja de entrada del correo destino

## ⚠️ Errores comunes:

| Problema | Solución |
|----------|----------|
| "Error: Debes configurar los datos del correo" | No configuraste EmailConfig.java |
| "Timeout" | Revisa tu conexión a internet |
| "Authentication failed" | La contraseña es incorrecta |
| "Invalid sender email" | El email de SENDER_EMAIL es inválido |
| "Mail not received" | Revisa carpeta spam/correo no deseado |

## 📧 Logs para debugging:

Los errores aparecerán en la consola con información específica. Si algo falla verás:
- ❌ Error al enviar el correo
- Verificar configuración
- Verificar conexión
- Verificar email del destinatario
