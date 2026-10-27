package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class Auxiliar {


    public static ArrayList<Persona> leerCSV(String path) {

        ArrayList<Persona> lista = new ArrayList<>();

        try (Scanner key = new Scanner(new File(path))) {

            key.nextLine();

            while (key.hasNextLine()) {
                String linea = key.nextLine();

                if (!linea.isBlank()) {
                    String[] campos = linea.split(",", 5);
                    Persona persona = new Persona(campos[0], campos[1], campos[2], campos[3], campos[4]);
                    lista.add(persona);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public static void escribirCSV(ArrayList<Persona> lista) {
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String nombreArchivo = "personal_" + fechaHora.format(formato) + ".csv";


        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            pw.println("firstName,lastName,email,gender,country");
            for (Persona persona : lista) {
                pw.println(persona);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Persona> leerXML(String path) {

        ArrayList<Persona> lista = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(path));

            NodeList personas = documento.getElementsByTagName("persona");

            for (int i = 0; i < personas.getLength(); i++) {

                Element persona = (Element) personas.item(i);

                String firstName = persona.getElementsByTagName("firstName").item(0).getTextContent();
                String lastName = persona.getElementsByTagName("lastName").item(0).getTextContent();
                String email = persona.getElementsByTagName("email").item(0).getTextContent();
                String gender = persona.getElementsByTagName("gender").item(0).getTextContent();
                String country = persona.getElementsByTagName("country").item(0).getTextContent();

                Persona p = new Persona(firstName, lastName, email, gender, country);

                lista.add(p);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static ArrayList<Persona> leerJSON(String path) {

        ArrayList<Persona> lista = new ArrayList<>();

        try (FileReader fr = new FileReader(path)) {

            Gson gson = new Gson();
            Persona[] personas = gson.fromJson(fr, Persona[].class);

            lista.addAll(Arrays.asList(personas));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
