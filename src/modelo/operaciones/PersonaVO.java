package modelo.operaciones;
public class PersonaVO {
    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;
    private int id;  


    public PersonaVO(int id, String nombre, String apellido, String documento, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
    }

    
    public PersonaVO(String documento, String nombre, String apellido, String telefono) {
        this.documento = documento; 
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "PersonaVO [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento
                + ", telefono=" + telefono + "]";
    }
}
