package modelo.operaciones;

public class MascotaVO {
    private String nombre;
    private String raza;
    private PersonaVO propietario;

   
    public MascotaVO(String nombre, String raza, PersonaVO propietario) {
        this.nombre = nombre;
        this.raza = raza;
        this.propietario = propietario;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }


    public PersonaVO getPropietario() {
        return propietario;
    }

    public void setPropietario(PersonaVO propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Mascota: " + nombre + ", Raza: " + raza + ", Propietario: " + propietario.getNombre() + " " + propietario.getApellido();
    }
}



