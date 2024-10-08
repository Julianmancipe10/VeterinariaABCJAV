package Controlador;

import java.util.List;

import Dao.MascotaDAO;
import Dao.PersonaDAO;
import modelo.operaciones.MascotaVO;
import modelo.operaciones.PersonaVO;

public class Controlador {

    private PersonaDAO personaDAO;
    private MascotaDAO mascotaDAO;

    public Controlador() {
        personaDAO = new PersonaDAO();
        mascotaDAO = new MascotaDAO();
    }

    // Métodos para Persona
    public void registrarPersona(PersonaVO persona) {
        personaDAO.registrarPersona(persona);
    }

    public PersonaVO consultarPersona(String documento) {
        return personaDAO.consultarPersona(documento);
    }

    public void actualizarPersona(PersonaVO persona) {
        personaDAO.actualizarPersona(persona);
    }

    public void eliminarPersona(String documento) {
        personaDAO.eliminarPersona(documento);
    }

    public String consultarListaPersonas() {
        List<PersonaVO> personas = personaDAO.consultarListaPersonas();
        if (personas.isEmpty()) {
            return "No hay personas registradas.";
        }
        StringBuilder lista = new StringBuilder("Lista de Personas:\n");
        for (PersonaVO persona : personas) {
            lista.append(persona.toString()).append("\n");
        }
        return lista.toString();
    }

    // Métodos para Mascota
    public void registrarMascota(MascotaVO mascota) {
        mascotaDAO.registrarMascota(mascota);
    }

    public MascotaVO consultarMascota(String nombre) {
        return mascotaDAO.consultarMascota(nombre);
    }

    public void actualizarMascota(MascotaVO mascota) {
        mascotaDAO.actualizarMascota(mascota);
    }

    public void eliminarMascota(String nombre) {
        mascotaDAO.eliminarMascota(nombre);
    }

    public String consultarListaMascotas() {
        List<MascotaVO> mascotas = mascotaDAO.listarMascotas();
        if (mascotas.isEmpty()) {
            return "No hay mascotas registradas.";
        }
        StringBuilder lista = new StringBuilder("Lista de Mascotas:\n");
        for (MascotaVO mascota : mascotas) {
            lista.append(mascota.toString()).append("\n");
        }
        return lista.toString();
    }
}

