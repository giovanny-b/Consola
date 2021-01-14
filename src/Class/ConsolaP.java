/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

/**
 *
 * @author giovannyb
 */
public class ConsolaP {

    //----------Colores---------
    private static String negro = "\u001B[30m";
    private static String rojo = "\u001B[31m";
    private static String verde = "\u001B[32m";
    private static String amarillo = "\u001B[33m";
    private static String azul = "\u001B[34m";
    private static String morado = "\u001B[35m";
    private static String cyan = "\u001B[36m";
    private static String blanco = "\u001B[37m";

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

        System.out.print("\033[H\033[2J");
        System.out.flush();

        do {
            System.out.print(Console);
            comand = scan.next();

            switch (comand) {
                case "cd":
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

                        break;
                    } else {

                        if (path.exists()) {
                            url = url + ComandUrl;
                            path = new File(url);
                            Console = verde + NameEq + "@" + Kernel + blanco + ":" + cyan + path.getName() + blanco + "$ ";
                        }else{
                            System.out.println(rojo + "¡La carpeta no existe!");
                            path = new File(url);
                        }

                    }
                    break;
                case "System":
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
                    break;
                case "dir":

                    String[] listado = path.list();
                    if (listado == null || listado.length == 0) {
                        System.out.println(rojo + "Carpeta vacia");
                        return;
                    } else {
                        for (int i = 0; i < listado.length; i++) {
                            System.out.println(listado[i]);
                        }
                    }

                    break;

                case "clear":
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    break;
                case "tp":

                    if (active) {
                        Console = verde + NameEq + "@" + Kernel + blanco + ":" + cyan + path.getName() + blanco + "$ ";
                        active = !active;
                    } else {
                        Console = verde + NameEq + "@" + Kernel + blanco + ":" + cyan + "~" + blanco + "$ ";
                        active = !active;
                    }

                    break;
            }

        } while (!comand.equalsIgnoreCase("Exit"));

        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

}
