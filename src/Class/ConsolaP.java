package Class;

import java.io.*;
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

    public static void main(String[] args) {

        Limpiar();

        do {
            System.out.print(Console);
            comand = scan.next();

            switch (comand) {
                case "cd":

                    cd();
                    
                    break;
                case "System":

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
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

}
