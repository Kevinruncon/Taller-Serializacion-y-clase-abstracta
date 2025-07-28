/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;
import persistencia.ArchivoManager;
import persistencia.ArchivoUtil;
/**
 *
 * @author Kevin
 */
public class DaoPersona {

    private final String archivo = "data/personas.dat";

    public void guardarPersonas(ArrayList<Persona> personas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(personas);
        } catch (IOException e) {
            System.err.println("Error guardando personas: " + e.getMessage());
        }
    }
    
    public Persona buscarPersona(String id){
        ArrayList<Persona> personas = cargarPersonas();
        for(    Persona p : personas){
            if(p.getIdentificacion().equals(id)){
                return p;
            }
            
        }
        return null;
                                                                                
    }

       public ArrayList<Persona> cargarPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();
        File archivoPersonas = new File(archivo);
        if (!archivoPersonas.exists()) {
            return personas; // El archivo no existe, se retorna lista vacía
        }
        try {
            if (archivoPersonas.length() > 0) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                    personas = (ArrayList<Persona>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar personas: " + e.getMessage());
        }
           return personas; 
       }
       
}



