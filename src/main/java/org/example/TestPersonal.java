package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class TestPersonal {
    public static void main(String[] args) {

        Scanner key = new Scanner(System.in);

        System.out.println("Nombre del fichero:");
        String fichero = key.nextLine();

        Personal personal = new Personal(Auxiliar.leerCSV(fichero));

        menu(key, personal);

        Auxiliar.escribirCSV(personal.getLista());

        key.close();
    }
    public static void menu(Scanner key, Personal personal) {

        int opcion;

        do {
            System.out.println("0. Salir");
            System.out.println("1. Mostrar personas de un país");
            System.out.println("2. Borrar una persona por email");

            opcion = Integer.parseInt(key.nextLine());

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo...");
                    break;
                case 1:
                    System.out.println("País:");
                    String pais = key.nextLine();
                   ArrayList<Persona> resultado = personal.personaPorPais(pais);
                   for (Persona p : resultado){
                       System.out.println(p);
                   }


                    break;
                case 2:
                    System.out.println("Email:");
                    String email = key.nextLine();
                    personal.borrarPersona(email);
                    break;

                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }
}
