package model;

public class CustomerVO {
    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String localidad;
    private String provincia;

    public CustomerVO() {}

    public CustomerVO(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String toString() {
        return "CLIENTE: " + nombre + " " + apellidos + "(DNI: " + dni + ")"
                + "\n\tDirección: " + direccion + " | Localidad: " + localidad + " | Provincia: " + provincia;
    }
}
