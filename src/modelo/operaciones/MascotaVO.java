package modelo.operaciones;

public class MascotaVO {
    private String nombre;
    private String raza;
    private String sexo;
    private PersonaVO propietario;

    public MascotaVO(String nombre, String raza, String sexo, PersonaVO propietario) {
        this.nombre = nombre;  
        this.raza = raza;
        this.sexo = sexo; // Asignación del atributo sexo
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

    public String getSexo() {
        return sexo; 
    }

    public void setSexo(String sexo) {
        this.sexo = sexo; 
    }

    public PersonaVO getPropietario() {
        return propietario;
    }

    public void setPropietario(PersonaVO propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Mascota: " + nombre + ", Raza: " + raza + ", Sexo: " + sexo + ", Propietario: " + propietario.getNombre(); // Asegúrate de que PersonaVO tenga un método getNombre()
    }
}



