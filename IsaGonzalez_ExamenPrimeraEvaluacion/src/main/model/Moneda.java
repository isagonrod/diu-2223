package main.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Moneda {
    private IntegerProperty codigo;
    private StringProperty nombre;
    private FloatProperty multiplicador;

    public Moneda() {
        this.codigo = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.multiplicador = new SimpleFloatProperty();
    }

    public int getCodigo() {
        return codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public float getMultiplicador() {
        return multiplicador.get();
    }

    public FloatProperty multiplicadorProperty() {
        return multiplicador;
    }

    public void setMultiplicador(float multiplicador) {
        this.multiplicador.set(multiplicador);
    }

    @Override
    public String toString() {
        return nombre.getValue();
    }
}
