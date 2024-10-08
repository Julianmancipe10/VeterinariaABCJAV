package Dao;

import modelo.operaciones.MascotaVO;
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
        String sql = "INSERT INTO mascota (nombre, raza, documento_propietario) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getRaza());
            stmt.setString(3, mascota.getPropietario().getDocumento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MascotaVO consultarMascota(String nombre) {
        String sql = "SELECT * FROM mascota WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MascotaVO(rs.getString("nombre"), rs.getString("raza"), null); // Asocia el propietario después
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarMascota(MascotaVO mascota) {
        String sql = "UPDATE mascota SET raza = ?, documento_propietario = ? WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mascota.getRaza());
            stmt.setString(2, mascota.getPropietario().getDocumento());
            stmt.setString(3, mascota.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarMascota(String nombre) {
        String sql = "DELETE FROM mascota WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MascotaVO> listarMascotas() {
        String sql = "SELECT * FROM mascota";
        List<MascotaVO> mascotas = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                mascotas.add(new MascotaVO(rs.getString("nombre"), rs.getString("raza"), null)); // Propietario después
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }
}

