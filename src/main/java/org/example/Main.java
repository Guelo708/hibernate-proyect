package org.example;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import org.example.repository.BillRepository;
import org.example.repository.BillStatusRepository;
import org.example.repository.PaymentMethodRepository;
import org.example.repository.PaymentRepository;
import org.example.repository.PropertyRepository;
import org.example.repository.PropertyTypeRepository;
import org.example.repository.SectorRepository;
import org.example.repository.TariffRepository;
import org.example.repository.UserRepository;
import org.example.util.EmailConfig;
import org.example.util.EmailService;
import org.example.util.JpaUtil;

import jakarta.persistence.EntityManager;

import com.lowagie.text.Document; // Documento PDF
import com.lowagie.text.FontFactory; // Factory para crear fuentes
import com.lowagie.text.pdf.PdfWriter; // Escritor de PDF
import java.io.FileOutputStream; // Salida a archivo

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManager em;

    // Repositorios
    private static UserRepository userRepo;
    private static TariffRepository tariffRepo;
    private static PropertyTypeRepository propertyTypeRepo;
    private static PropertyRepository propertyRepo;
    private static BillRepository billRepo;
    private static BillStatusRepository billStatusRepo;
    private static PaymentRepository paymentRepo;
    private static PaymentMethodRepository paymentMethodRepo;
    private static SectorRepository sectorRepo;

    public static void main(String[] args) {
        em = JpaUtil.getEntityManager();

        // Inicializar repositorios
        userRepo = new UserRepository(em);
        tariffRepo = new TariffRepository(em);
        propertyTypeRepo = new PropertyTypeRepository(em);
        propertyRepo = new PropertyRepository(em);
        billRepo = new BillRepository(em);
        billStatusRepo = new BillStatusRepository(em);
        paymentRepo = new PaymentRepository(em);
        paymentMethodRepo = new PaymentMethodRepository(em);
        sectorRepo = new SectorRepository(em);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Tarifas");
            System.out.println("3. Gestionar Tipos de Predios");
            System.out.println("4. Gestionar Predios");
            System.out.println("5. Gestionar Facturas");
            System.out.println("6. Gestionar Estados de Facturas");
            System.out.println("7. Gestionar Pagos");
            System.out.println("8. Gestionar Métodos de Pago");
            System.out.println("9. Gestionar Sectores");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 ->
                    menuUsers();
                case 2 ->
                    menuTariffs();
                case 3 ->
                    menuPropertyTypes();
                case 4 ->
                    menuProperties();
                case 5 -> {
                    try {
                        menuBills();
                    } catch (Exception e) {
                        System.out.println("Error al gestionar facturas: " + e.getMessage());
                    }
                }
                case 6 ->
                    menuBillStatuses();
                case 7 ->
                    menuPayments();
                case 8 ->
                    menuPaymentMethods();
                case 9 ->
                    menuSectors();
                case 0 ->
                    exit = true;
                default ->
                    System.out.println("Opción no válida.");
            }
        }

        JpaUtil.close();
        System.out.println("Saliendo...");
    }

    // Ejemplo de CRUD para Usuarios
    private static void menuUsers() {
        System.out.println("\n--- GESTIÓN DE USUARIOS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                System.out.print("Cédula: ");
                String cedula = scanner.nextLine();
                System.out.print("Primer Nombre: ");
                String primerNombre = scanner.nextLine();
                System.out.print("Segundo Nombre: ");
                String segundoNombre = scanner.nextLine();
                System.out.print("Primer Apellido: ");
                String primerApellido = scanner.nextLine();
                System.out.print("Segundo Apellido: ");
                String segundoApellido = scanner.nextLine();
                System.out.print("Celular: ");
                String celular = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                userRepo.save(
                        new User(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, celular, email));
                System.out.println("Usuario guardado.");
            }
            case 2 ->
                userRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("Cédula a actualizar: ");
                String cedula = scanner.nextLine();
                Optional<User> u = userRepo.findById(cedula);
                u.ifPresentOrElse(user -> {
                    System.out.print("Nuevo celular: ");
                    user.setCelular(scanner.nextLine());
                    userRepo.update(user);
                    System.out.println("Usuario actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("Cédula a eliminar: ");
                String cedula = scanner.nextLine();
                userRepo.findById(cedula).ifPresentOrElse(user -> {
                    userRepo.delete(user);
                    System.out.println("Usuario eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuProperties() {
        System.out.println("\n--- GESTIÓN DE PREDIOS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                // NOTA: codigoPredio es autoincremental, no se pide al crear
                System.out.print("Id Tipo Predio: ");
                Long idTipoPredio = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Cédula Propietario: ");
                String cedulaPropietario = scanner.nextLine();

                System.out.print("Id Sector: ");
                Long idSector = scanner.nextLong();
                scanner.nextLine();

                propertyRepo.save(new Property(idTipoPredio, cedulaPropietario, idSector));
                System.out.println("Predio guardado.");
            }
            case 2 ->
                propertyRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("Código del Predio a actualizar: ");
                Long codigoPredio = scanner.nextLong();
                scanner.nextLine();

                propertyRepo.findById(codigoPredio).ifPresentOrElse(p -> {
                    System.out.print("Nuevo Id Tipo Predio: ");
                    p.setIdTipoPredio(scanner.nextLong());
                    scanner.nextLine();

                    System.out.print("Nueva Cédula Propietario: ");
                    p.setCedulaPropietario(scanner.nextLine());

                    System.out.print("Nuevo Id Sector: ");
                    p.setIdSector(scanner.nextLong());
                    scanner.nextLine();

                    propertyRepo.update(p);
                    System.out.println("Predio actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("Código del Predio a eliminar: ");
                Long codigoPredio = scanner.nextLong();
                scanner.nextLine();

                propertyRepo.findById(codigoPredio).ifPresentOrElse(p -> {
                    propertyRepo.delete(p);
                    System.out.println("Predio eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuPropertyTypes() {
        System.out.println("\n--- GESTIÓN DE TIPOS DE PREDIOS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                System.out.print("Descripción del Tipo de Predio: ");
                String descripcion = scanner.nextLine();

                propertyTypeRepo.save(new PropertyType(descripcion));
                System.out.println("Tipo de Predio guardado.");
            }
            case 2 ->
                propertyTypeRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID del Tipo de Predio a actualizar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                propertyTypeRepo.findById(id).ifPresentOrElse(tp -> {
                    System.out.print("Nueva descripción: ");
                    tp.setDescripcion(scanner.nextLine());
                    propertyTypeRepo.update(tp);
                    System.out.println("Tipo de Predio actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID del Tipo de Predio a eliminar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                propertyTypeRepo.findById(id).ifPresentOrElse(tp -> {
                    propertyTypeRepo.delete(tp);
                    System.out.println("Tipo de Predio eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuSectors() {
        System.out.println("\n--- GESTIÓN DE SECTORES ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                System.out.print("Nombre del Sector: ");
                String nombre = scanner.nextLine();

                System.out.print("Descripción del Sector: ");
                String descripcion = scanner.nextLine();

                sectorRepo.save(new Sector(nombre, descripcion));
                System.out.println("Sector guardado.");
            }
            case 2 ->
                sectorRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID del Sector a actualizar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                sectorRepo.findById(id).ifPresentOrElse(s -> {
                    System.out.print("Nuevo nombre: ");
                    s.setNombreSector(scanner.nextLine());

                    System.out.print("Nueva descripción: ");
                    s.setDescripcionSector(scanner.nextLine());

                    sectorRepo.update(s);
                    System.out.println("Sector actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID del Sector a eliminar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                sectorRepo.findById(id).ifPresentOrElse(s -> {
                    sectorRepo.delete(s);
                    System.out.println("Sector eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuTariffs() {
        System.out.println("\n--- GESTIÓN DE TARIFAS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                System.out.print("Id Tipo Predio (número): ");
                Long idTipoPredio = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Valor de la tarifa: ");
                Double valor = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Fecha inicio (yyyy-MM-dd): ");
                String fechaInicioStr = scanner.nextLine();
                Date fechaInicio = java.sql.Date.valueOf(fechaInicioStr);

                System.out.print("Fecha fin (yyyy-MM-dd, opcional, ENTER si no aplica): ");
                String fechaFinStr = scanner.nextLine();
                Date fechaFin = fechaFinStr.isEmpty() ? null : java.sql.Date.valueOf(fechaFinStr);

                tariffRepo.save(new Tariff(idTipoPredio, valor, fechaInicio, fechaFin));
                System.out.println("Tarifa guardada.");
            }
            case 2 ->
                tariffRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID de la Tarifa a actualizar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                tariffRepo.findById(id).ifPresentOrElse(t -> {
                    System.out.print("Nuevo valor: ");
                    t.setValor(scanner.nextDouble());
                    scanner.nextLine();

                    System.out.print("Nueva fecha inicio (yyyy-MM-dd): ");
                    t.setFechaInicio(java.sql.Date.valueOf(scanner.nextLine()));

                    System.out.print("Nueva fecha fin (yyyy-MM-dd, ENTER si no aplica): ");
                    String fechaFinStr = scanner.nextLine();
                    t.setFechaFin(fechaFinStr.isEmpty() ? null : java.sql.Date.valueOf(fechaFinStr));

                    tariffRepo.update(t);
                    System.out.println("Tarifa actualizada.");
                }, () -> System.out.println("No encontrada."));
            }
            case 4 -> {
                System.out.print("ID de la Tarifa a eliminar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                tariffRepo.findById(id).ifPresentOrElse(t -> {
                    tariffRepo.delete(t);
                    System.out.println("Tarifa eliminada.");
                }, () -> System.out.println("No encontrada."));
            }
        }
    }

    private static void menuPaymentMethods() {
        System.out.println("\n--- GESTIÓN DE MÉTODOS DE PAGO ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                System.out.print("Nombre del Método de Pago: ");
                String nombre = scanner.nextLine();

                paymentMethodRepo.save(new PaymentMethod(nombre));
                System.out.println("Método de Pago guardado.");
            }
            case 2 ->
                paymentMethodRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID del Método de Pago a actualizar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                paymentMethodRepo.findById(id).ifPresentOrElse(mp -> {
                    System.out.print("Nueva descripción: ");
                    mp.setDescripcion(scanner.nextLine());
                    paymentMethodRepo.update(mp);
                    System.out.println("Método de Pago actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID del Método de Pago a eliminar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                paymentMethodRepo.findById(id).ifPresentOrElse(mp -> {
                    paymentMethodRepo.delete(mp);
                    System.out.println("Método de Pago eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuPayments() {
        System.out.println("\n--- GESTIÓN DE PAGOS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                System.out.print("ID de la Factura: ");
                Long idFactura = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Fecha de Pago (yyyy-MM-dd): ");
                String fechaPagoStr = scanner.nextLine();
                Date fechaPago = java.sql.Date.valueOf(fechaPagoStr);

                System.out.print("Valor Pagado: ");
                Double valorPagado = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("ID del Método de Pago: ");
                Long idMetodoPago = scanner.nextLong();
                scanner.nextLine();

                paymentRepo.save(new Payment(idFactura, fechaPago, valorPagado, idMetodoPago));
                System.out.println("Pago registrado.");
            }
            case 2 ->
                paymentRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID del Pago a actualizar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                paymentRepo.findById(id).ifPresentOrElse(p -> {
                    System.out.print("Nuevo ID Factura: ");
                    p.setIdFactura(scanner.nextLong());
                    scanner.nextLine();

                    System.out.print("Nueva Fecha de Pago (yyyy-MM-dd): ");
                    p.setFechaPago(java.sql.Date.valueOf(scanner.nextLine()));

                    System.out.print("Nuevo Valor Pagado: ");
                    p.setValorPagado(scanner.nextDouble());
                    scanner.nextLine();

                    System.out.print("Nuevo ID Método de Pago: ");
                    p.setIdMetodoPago(scanner.nextLong());
                    scanner.nextLine();

                    paymentRepo.update(p);
                    System.out.println("Pago actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID del Pago a eliminar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                paymentRepo.findById(id).ifPresentOrElse(p -> {
                    paymentRepo.delete(p);
                    System.out.println("Pago eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuBillStatuses() {
        System.out.println("\n--- GESTIÓN DE ESTADOS DE FACTURAS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");

        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                System.out.print("Descripción del Estado de Factura: ");
                String descripcion = scanner.nextLine();

                billStatusRepo.save(new BillStatus(descripcion));
                System.out.println("Estado de Factura guardado.");
            }
            case 2 ->
                billStatusRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID del Estado de Factura a actualizar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                billStatusRepo.findById(id).ifPresentOrElse(bs -> {
                    System.out.print("Nueva descripción: ");
                    bs.setDescripcion(scanner.nextLine());
                    billStatusRepo.update(bs);
                    System.out.println("Estado de Factura actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID del Estado de Factura a eliminar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                billStatusRepo.findById(id).ifPresentOrElse(bs -> {
                    billStatusRepo.delete(bs);
                    System.out.println("Estado de Factura eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuBills() throws Exception {
        System.out.println("\n--- GESTIÓN DE FACTURAS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Generar PDF");
        System.out.println("5. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                System.out.print("Código del Predio: ");
                Long codigoPredio = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Fecha de Emisión (yyyy-MM-dd): ");
                String fechaEmisionStr = scanner.nextLine();
                Date fechaEmision = java.sql.Date.valueOf(fechaEmisionStr);

                System.out.print("Valor de la Tarifa: ");
                Double valorTarifa = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Mes Facturado (yyyy-MM-dd): ");
                String mesFacturadoStr = scanner.nextLine();
                Date mesFacturado = java.sql.Date.valueOf(mesFacturadoStr);

                System.out.print("Año Facturado (yyyy-MM-dd): ");
                String añoFacturadoStr = scanner.nextLine();
                Date añoFacturado = java.sql.Date.valueOf(añoFacturadoStr);

                System.out.print("ID Estado Factura: ");
                Long idEstado = scanner.nextLong();
                scanner.nextLine();

                billRepo.save(new Bill(codigoPredio, fechaEmision, valorTarifa, mesFacturado, añoFacturado, idEstado));
                System.out.println("Factura registrada.");
            }
            case 2 ->
                billRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID de la Factura a actualizar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                billRepo.findById(id).ifPresentOrElse(b -> {
                    System.out.print("Nuevo Código Predio: ");
                    b.setCodigoPredio(scanner.nextLong());
                    scanner.nextLine();

                    System.out.print("Nueva Fecha de Emisión (yyyy-MM-dd): ");
                    b.setFechaEmision(java.sql.Date.valueOf(scanner.nextLine()));

                    System.out.print("Nuevo Valor Tarifa: ");
                    b.setValorTarifa(scanner.nextDouble());
                    scanner.nextLine();

                    System.out.print("Nuevo Mes Facturado (yyyy-MM-dd): ");
                    b.setMesFacturado(java.sql.Date.valueOf(scanner.nextLine()));

                    System.out.print("Nuevo Año Facturado (yyyy-MM-dd): ");
                    b.setAñoFacturado(java.sql.Date.valueOf(scanner.nextLine()));

                    System.out.print("Nuevo ID Estado Factura: ");
                    b.setIdEstado(scanner.nextLong());
                    scanner.nextLine();

                    billRepo.update(b);
                    System.out.println("Factura actualizada.");
                }, () -> System.out.println("No encontrada."));
            }
            
            case 4 -> {
                String pdfPath = "reporte.pdf";
                var doc = new Document();

                // Crear el escritor que genera el archivo
                PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));

                // Abrir el documento para agregar contenido
                doc.open();

                for (Bill bill : billRepo.findAll()) {
                    doc.add(new com.lowagie.text.Paragraph(bill.toString()));
                }

                // Agregar título
                var titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
                doc.add(new com.lowagie.text.Paragraph("Reporte de Sistema", titleFont));
                doc.add(new com.lowagie.text.Paragraph(" ")); // Espacio vacío

                // Cerrar el documento (genera el archivo)
                doc.close();

                System.out.println("✓ PDF generado: " + pdfPath);
                
                // Menú para enviar por correo
                System.out.println("\n--- OPCIONES DEL PDF ---");
                System.out.println("1. Enviar por correo");
                System.out.println("2. Solo guardar (sin enviar)");
                System.out.print("Opción: ");
                int pdfOption = scanner.nextInt();
                scanner.nextLine();
                
                if (pdfOption == 1) {
                    enviarPdfPorCorreo(pdfPath);
                } else {
                    System.out.println("PDF guardado localmente.");
                }
            }
            
            case 5 -> {
                System.out.print("ID de la Factura a eliminar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                billRepo.findById(id).ifPresentOrElse(b -> {
                    billRepo.delete(b);
                    System.out.println("Factura eliminada.");
                }, () -> System.out.println("No encontrada."));
            }
        }
    }

    /**
     * Método auxiliar para enviar PDF por correo
     */
    private static void enviarPdfPorCorreo(String pdfPath) {
        // Verificar si está configurado
        if (!EmailConfig.isConfigured()) {
            System.out.println("\nERROR: Debes configurar los datos del correo primero.");
            System.out.println("PASOS:");
            System.out.println("1. Abre: src/main/java/org/example/util/EmailConfig.java");
            System.out.println("2. Reemplaza los valores de:");
            System.out.println("   - SENDER_EMAIL: tu correo de Gmail");
            System.out.println("   - SENDER_PASSWORD: contraseña de aplicación");
            System.out.println("3. Guarda el archivo y vuelve a intentar");
            System.out.println("\nGuía: https://myaccount.google.com/apppasswords");
            return;
        }

        System.out.print("\nEmail del destinatario: ");
        String recipientEmail = scanner.nextLine();
        
        if (!recipientEmail.contains("@")) {
            System.out.println("Email inválido.");
            return;
        }

        System.out.print("Asunto del correo [Reporte de Facturas]: ");
        String subject = scanner.nextLine();
        if (subject.isEmpty()) {
            subject = "Reporte de Facturas";
        }

        System.out.print("Mensaje (opcional): ");
        String body = scanner.nextLine();
        if (body.isEmpty()) {
            body = "Adjunto encontrará el reporte del sistema de facturas.";
        }

        System.out.println("\n Enviando correo...");
        
        // Inicializar servicio de correo
        EmailService emailService = new EmailService(
            EmailConfig.SENDER_EMAIL,
            EmailConfig.SENDER_PASSWORD,
            EmailConfig.SMTP_SERVER,
            EmailConfig.SMTP_PORT
        );

        // Enviar correo
        boolean enviado = emailService.sendEmailWithAttachment(recipientEmail, subject, body, pdfPath);
        
        if (enviado) {
            System.out.println("Correo enviado exitosamente a: " + recipientEmail);
        } else {
            System.out.println("Error al enviar el correo. Verifica:");
            System.out.println("   - La configuración en EmailConfig.java");
            System.out.println("   - Tu conexión a internet");
            System.out.println("   - El email del destinatario");
        }
    }

    // Aquí se replica la misma logica de menu CRUD para cada entidad
}
