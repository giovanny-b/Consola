/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author giovannyb
 */
public class Pruebas {

    public static void main(String[] args) {
        
        String path = "/home/usr/giovannyb";
        String pathI = "";
        String pathUrl = "";
        String pathT = "";
        
        for (int x = path.length() - 1; x >= 0; x--) {
            pathI = pathI + path.charAt(x);
        }
                
        int locali = pathI.indexOf("/");
        int ultChar = pathI.length();
        
        pathT = pathI.substring(locali, ultChar);
                
        for (int x = pathT.length() - 1; x >= 0; x--) {
            pathUrl = pathUrl + pathT.charAt(x);
        }
        
        System.out.println(pathUrl);

    }

}
