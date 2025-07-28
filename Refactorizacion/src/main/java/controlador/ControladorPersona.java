/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import dao.DaoPersona;
import modelo.Persona;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kevin
 */
public class ControladorPersona {

    private final DaoPersona dao;
    private ArrayList<Persona> personas;

    public ControladorPersona() {
        this.dao = new DaoPersona();
        this.personas = dao.cargarPersonas();
    }

    public ArrayList<Persona> listar() {
        return personas;
    }

    public void agregar(Persona persona) {
        personas.add(persona);
        dao.guardarPersonas(personas);
    }
    public Persona buscarPersona(String id){
       return dao.buscarPersona(id);
    }

    public void eliminarPorIdentificacion(String id) {
        personas.removeIf(p -> p.getIdentificacion().equals(id));
        dao.guardarPersonas(personas);
    }
}

