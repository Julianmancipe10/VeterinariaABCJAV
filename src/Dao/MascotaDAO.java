package Dao;

import modelo.operaciones.MascotaVO;
import modelo.operaciones.PersonaVO; 
import Conexion.conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    private Connection conn;

    public MascotaDAO() {
        conn = conexion.getConnection();
    }

    public void registrarMascota(MascotaVO mascota) {
    	
        if (!dueñoExiste(mascota.getPropietario().getDocumento())) {
            System.out.println("El dueño con documento " + mascota.getPropietario().getId() + " no existe.");
            return; 
        }

        String sql = "INSERT INTO mascota (nombre, raza, sexo, idDueño) VALUES (?, ?, ? ,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getRaza());
            stmt.setString(3, mascota.getSexo());
            stmt.setString(4, mascota.getPropietario().getDocumento());
            stmt.executeUpdate();
            System.out.println("Mascota registrada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean dueñoExiste(String documento) {
        String sql = "SELECT COUNT(*) FROM persona WHERE documento = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento); 
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si existe al menos un registro
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false si no existe
    }

    public MascotaVO consultarMascota(String nombre) {
        String sql = "SELECT * FROM mascota WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Obtener el propietario desde la base de datos
                int idPropietario = rs.getInt("idDueño");
                PersonaVO propietario = new PersonaVO(idPropietario, sql, sql, sql, sql); 
                propietario.setId(idPropietario); 
                return new MascotaVO(rs.getString("nombre"), rs.getString("raza"), sql, propietario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Manejar el caso donde no se encuentra
    }

    public void actualizarMascota(MascotaVO mascota) {
        String sql = "UPDATE mascota SET raza = ?, idDueño = ? WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mascota.getRaza());
            stmt.setLong(2, mascota.getPropietario().getId()); 
            stmt.setString(3, mascota.getNombre());
            stmt.executeUpdate();
            System.out.println("Mascota actualizada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarMascota(String nombre) {
        String sql = "DELETE FROM mascota WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            System.out.println("Mascota eliminada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MascotaVO> listarMascotas() {
        String sql = "SELECT * FROM mascota";
        List<MascotaVO> mascotas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                int idPropietario = rs.getInt("idDueño");
                PersonaVO propietario = new PersonaVO(idPropietario, raza, raza, raza, raza);
                propietario.setId(idPropietario); 
                mascotas.add(new MascotaVO(nombre, raza, raza, propietario));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }
}


