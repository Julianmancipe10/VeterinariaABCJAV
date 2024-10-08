package ScriptBD;
/*CREATE DATABASE ClinicaVeterinariaABC;

USE ClinicaVeterinariaABC;

-- Tabla para almacenar las personas
CREATE TABLE Persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    documento VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(15),
    direccion VARCHAR(255)
);

-- Tabla para almacenar las mascotas
CREATE TABLE Mascota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raza VARCHAR(50),
    edad INT,
    propietario_id INT,
    FOREIGN KEY (propietario_id) REFERENCES Persona(id) ON DELETE CASCADE
);
*/