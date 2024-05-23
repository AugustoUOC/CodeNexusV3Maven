package com.codenexus.codenexusp4.utilidad;

import java.util.Scanner;

public class Teclado {

    private static Scanner scanner;

    public static String pedirString(String mensaje) {
        scanner = new Scanner(System.in);
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public static int pedirInt(String mensaje) {
        scanner = new Scanner(System.in);
        System.out.print(mensaje);
        return scanner.nextInt();
    }

    public static double pedirDouble(String mensaje) {
        scanner = new Scanner(System.in);
        System.out.print(mensaje);
        return scanner.nextDouble();
    }

    public static boolean confirmarAccion(String mensaje) {
        String respuesta = "";
        while (true) {
            respuesta = pedirString(mensaje + "(si/no): ");
            if(respuesta.equals("si")) {
                return true;
            }else if (respuesta.equals("no")){
                return false;
            }
            System.out.println("Te has equivocado prueba otra vez");
        }
    }


}
