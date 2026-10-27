package org.example;

import java.util.ArrayList;

public class Personal {

    private ArrayList<Persona> lista;

    //Métodos


    public Personal(ArrayList<Persona> lista) {
        this.lista = lista;
    }


    //Getter Setter


    public ArrayList<Persona> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Persona> lista) {
        this.lista = lista;
    }

    public void addPersona(Persona p) {

        lista.add(p);


    }



    public ArrayList<Persona> personaPorPais(String pais) {

        ArrayList<Persona> resultado = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Persona p = lista.get(i);
            if (p.getCountry().equalsIgnoreCase(pais))
                resultado.add(p);
        }
        return resultado;
    }

    public void borrarPersona(String email) {

        for (int i = 0; i < lista.size(); i++) {

            Persona p = lista.get(i);

            if (p.getEmail().equalsIgnoreCase(email)) {

                lista.remove(p);
            break;
            }
        }
    }

    public void addDesdeCSV(String path) {
        ArrayList<Persona> personas = Auxiliar.leerCSV(path);

        for (Persona p : personas) {
            lista.add(p);
        }
    }

    public void addDesdeXML(String path) {
        ArrayList<Persona> personas = Auxiliar.leerXML(path);

        for (Persona p : personas) {
            lista.add(p);
        }
    }
    public void addDesdeJSON (String path) {
        ArrayList<Persona> personas = Auxiliar.leerJSON(path);

        for (Persona p : personas) {
            lista.add(p);
        }
    }
}
