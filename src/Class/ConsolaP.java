package Class;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Esta es la clase principal.
 *
 * @author giovannyb
 * @version 14/01/21/A
 */
public class ConsolaP {

    //----------Colores---------
    public static String negro = "\u001B[30m";
    public static String rojo = "\u001B[31m";
    public static String verde = "\u001B[32m";
    public static String amarillo = "\u001B[33m";
    public static String azul = "\u001B[34m";
    public static String morado = "\u001B[35m";
    public static String cyan = "\u001B[36m";
    public static String blanco = "\u001B[37m";

    /*----Base de datos----*/
    public static String host = "localhost";
    public static String user = "GiovannyBernal";
    public static String pass = "1096539141";
    public static String BD = "";
    public static String URLcon = "jdbc:mysql://localhost:3306/Prueba1";

    private static String NameEq = System.getProperty("user.name");
    private static String Kernel = System.getProperty("os.name");
    private static String VKR = System.getProperty("os.version");
    private static String arch = System.getProperty("os.arch");

    private static String java = System.getProperty("java.home");
    private static String javaV = System.getProperty("java.version");
    private static String home = System.getProperty("user.home");

    private static String sep = System.getProperty("file.separator");

    private static String comand = "";
    private static boolean active = false;

    private static Scanner scan = new Scanner(System.in);

    private static String url = home;
    private static File path = new File(url);

    private static String Console = verde + NameEq + "@" + Kernel + blanco + ":" + cyan + path.getName() + blanco + "$ ";

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        
        Limpiar();

        Obtener();

        Equipo();

        do {
            System.out.print(Console);
            comand = scan.next();

            switch (comand) {
                case "cd":

                    cd();

                    break;
                case "system":

                    System();

                    break;
                case "dir":

                    dir();

                    break;

                case "clear":

                    Limpiar();

                    break;
                case "tp":

                    TotalPath();

                    break;
                case "delete":

                    Delete();

                    break;
                case "create":

                    Create();

                    break;

                case "git":
                    String command = scan.next();

                    if (command.equals("clone")) {
                        String url = scan.next();

                        String comando = command + " " + url;

                        Process proc = Runtime.getRuntime().exec("git " + comando);

                        // Read the output
                        BufferedReader reader
                                = new BufferedReader(new InputStreamReader(proc.getInputStream()));

                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            System.out.print(line + "\n");
                        }

                        proc.waitFor();

                    }

                    break;
                case "createdb":
                    
                    
                    
                    break;
            }

        } while (!comand.equalsIgnoreCase("Exit"));

        Limpiar();

    }

    /**
     * Metodo para limpiar la consola
     *
     * @param Limpiar limpia la consola.
     */
    private static void Limpiar() {
        if (Kernel.equalsIgnoreCase("linux")) {

            System.out.print("\033[H\033[2J");
            System.out.flush();

        } else {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                /*No hacer nada*/
            }
        }

    }

    /**
     * muestra el path completo de la localizacion actual.
     *
     * @param TotalPath activa o desactiva el path actual.
     */
    private static void TotalPath() {
        if (active) {
            Console = verde + NameEq + "@" + Kernel + blanco + ":" + cyan + path.getName() + blanco + "$ ";
            active = !active;
        } else {
            Console = verde + NameEq + "@" + Kernel + blanco + ":" + cyan + "~" + blanco + "$ ";
            active = !active;
        }
    }

    /**
     * Muestra los archivos de la localizacion actual.
     *
     * @param dir Muestra los archivos de la carpeta accedida.
     */
    private static void dir() {
        File[] archivos = path.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            return;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            for (int i = 0; i < archivos.length; i++) {
                File archivo = archivos[i];
                System.out.println(String.format(cyan + "%s" + blanco + "(%s)" + rojo + " - %d *bytes*" + morado + " - %s",
                        archivo.getName(),
                        archivo.isDirectory() ? "Carpeta" : "Archivo",
                        archivo.length(),
                        sdf.format(archivo.lastModified())
                ));
            }
        }
    }

    /**
     * Muestra especificaciones del sistema con los comando especificos
     *
     * @param System Muestra especificaciones del sistema
     */
    private static void System() {
        String system = scan.next();

        switch (system) {
            case "kernel":

                System.out.println(verde + "Kernel: " + blanco + arch + " " + Kernel + " " + VKR);

                break;
            case "java":

                String ComandJava = scan.next();

                switch (ComandJava) {
                    case "path":
                        System.out.println(verde + "Java path: " + blanco + java);
                        break;
                    case "version":
                        System.out.println(verde + "Java version: " + blanco + javaV);
                        break;
                }

                break;
        }
    }

    /**
     * accede a rutas del disco o carpetas por medio de url
     *
     * @param cd accede a rutas del disco o carpetas
     */
    private static void cd() {
        String ComandUrl = scan.next();

        path = new File(ComandUrl);

        if (ComandUrl.startsWith("/home/")) {
            if (path.exists()) {
                url = ComandUrl;
                path = new File(url);
                Console = verde + NameEq + "@" + Kernel + blanco + ":" + cyan + path.getName() + blanco + "$ ";
            } else {
                System.out.println(rojo + "¡El directorio no existe!");
                path = new File(url);
            }
        } else if (ComandUrl.equals("../")) {

            String pathI = "";
            String pathUrl = "";
            String pathT = "";

            for (int x = url.length() - 1; x >= 0; x--) {
                pathI = pathI + url.charAt(x);
            }

            String pathEnd = pathI.substring(1, pathI.length());

            int locali = pathEnd.indexOf(sep);
            int ultChar = pathEnd.length();

            pathT = pathEnd.substring(locali, ultChar);

            for (int x = pathT.length() - 1; x >= 0; x--) {
                pathUrl = pathUrl + pathT.charAt(x);
            }

            url = pathUrl;
            path = new File(url);
            Console = verde + NameEq + "@" + Kernel + blanco + ":" + cyan + path.getName() + blanco + "$ ";

        } else {

            if (path.exists()) {
                url = url + ComandUrl;
                path = new File(url);
                Console = verde + NameEq + "@" + Kernel + blanco + ":" + cyan + path.getName() + blanco + "$ ";
            } else {
                System.out.println(rojo + "¡La carpeta no existe!");
                path = new File(url);
            }

        }
    }

    /**
     * Elimina una carpeta o archivo seleccionado
     *
     * @param Delete Elimina carpetas o archivos
     *
     */
    private static void Delete() {
        String borrar = scan.next();

        path = new File(borrar);

        if (path.delete()) {
            System.out.println(rojo + "Se a eliminado el archivo " + blanco + path.getName());
        } else {
            System.out.println("¡Error!");
        }

        path = new File(url);

    }

    /**
     * crea archivos con plantillas diseñadas y editables. y carpetas
     *
     * @param Create crea archivos y carpetas
     *
     */
    private static void Create() throws IOException {
        String Tipo = scan.next();
        String nombre = scan.next();

        if (Tipo.equalsIgnoreCase("w")) {
            path = new File(nombre + ".docx");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);

        } else if (Tipo.equalsIgnoreCase("t")) {
            path = new File(nombre + ".txt");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("p")) {
            path = new File(nombre + ".pdf");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("hc")) {
            path = new File(nombre + ".xlsx");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("pp")) {
            path = new File(nombre + ".pptx");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("jc")) {
            path = new File(nombre + ".java");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("pf")) {
            path = new File(nombre + ".py");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("cpp")) {
            path = new File(nombre + ".cpp");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("cpph")) {
            path = new File(nombre + ".h");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("wh")) {
            path = new File(nombre + ".html");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("php")) {
            path = new File(nombre + ".php");

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("cst")) {

            path = new File(nombre);

            if (path.createNewFile()) {
                System.out.println(cyan + "se a creado el archivo " + path.getName());
            } else {
                System.out.println(rojo + "El archivo ya existe");
            }

            path = new File(url);
        } else if (Tipo.equalsIgnoreCase("fold")) {
            path = new File(nombre);

            if (path.mkdir()) {
                System.out.println(cyan + "se a creado la carpeta " + path.getName());
            } else {
                System.out.println(rojo + "La carpeta ya existe");
            }

            path = new File(url);
        }

    }

    private static void Equipo() throws IOException, InterruptedException {
        String comando = "screenfetch";

        Process proc = Runtime.getRuntime().exec(comando);

        // Read the output
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        proc.waitFor();
    }

    public static void Obtener() throws SQLException {
        
        Connection con = DriverManager.getConnection(URLcon, user, pass);
        
        try ( PreparedStatement stmt = con.prepareStatement("SELECT * FROM clientes")) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("telefono"));
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }

    }
    
    public static void create() throws SQLException{
        
        
        
    }

}
