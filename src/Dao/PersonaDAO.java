package Dao;

import Conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.operaciones.PersonaVO;

public class PersonaDAO {

    private Connection conn;

    public PersonaDAO() {
        conn = conexion.getConnection();
    }

    public void registrarPersona(PersonaVO persona) {
        String sql = "INSERT INTO persona (documento,nombre,telefono) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, persona.getDocumento());
            String nombreCompleto = persona.getNombre() + " " + persona.getApellido() ;
            ps.setString(2, nombreCompleto);
            ps.setString(3, persona.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar persona: " + e.getMessage());
        }
    }

    public PersonaVO consultarPersona(String documento) {
        PersonaVO persona = null;
        String sql = "SELECT * FROM persona WHERE documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                persona = new PersonaVO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("documento"),
                    rs.getString("telefono")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar persona: " + e.getMessage());
        }
        return persona;
    }

    public void actualizarPersona(PersonaVO persona) {
        String sql = "UPDATE persona SET nombre = ?, apellido = ?, telefono = ? WHERE documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getTelefono());
            ps.setString(4, persona.getDocumento());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
    }

    public void eliminarPersona(String documento) {
        String sql = "DELETE FROM persona WHERE documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, documento);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
        }
    }

    public List<PersonaVO> consultarListaPersonas() {
        List<PersonaVO> listaPersonas = new ArrayList<>();
        String sql = "SELECT * FROM persona";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PersonaVO persona = new PersonaVO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("documento"),
                    rs.getString("telefono")
                );
                listaPersonas.add(persona);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar lista de personas: " + e.getMessage());
        }
        return listaPersonas;
    }
}

