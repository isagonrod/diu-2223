package main.model;

public class MonedaVO {
    private Integer codigo;
    private String nombre;
    private Float multiplicador;

    public MonedaVO() {}

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(Float multiplicador) {
        this.multiplicador = multiplicador;
    }
}
