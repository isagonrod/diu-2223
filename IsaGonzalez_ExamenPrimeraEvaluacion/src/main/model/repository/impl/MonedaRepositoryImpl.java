package main.model.repository.impl;

import Modelo.ExcepcionMoneda;
import main.model.MonedaVO;
import main.model.repository.MonedaRepository;
import main.util.DatabaseConnection;
import main.util.DatabaseStatement;
import main.util.MySqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonedaRepositoryImpl implements MonedaRepository {
    private final DatabaseConnection conn;
    private final DatabaseStatement stmt;

    public MonedaRepositoryImpl() {
        this.conn = new MySqlConnection();
        this.conn.connectToDataBase();
        this.stmt = this.conn.getNewStatement();
    }

    @Override
    public List<Modelo.MonedaVO> obtenerListaMonedas() throws ExcepcionMoneda {
        ResultSet rs = this.stmt.select("*", "moneda", null, null);
        List<Modelo.MonedaVO> result = new ArrayList<>();
        Modelo.MonedaVO tmp;

        try {
            while (rs.next()) {
                tmp = new Modelo.MonedaVO(
                        rs.getString("nombre"),
                        rs.getFloat("multiplicador"),
                        rs.getInt("codigo"));
                result.add(tmp);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            this.stmt.closeStatement();
            this.conn.closeDataBase();
            throw new ExcepcionMoneda("Error al listar las monedas");
        }

        this.stmt.closeStatement();
        this.conn.closeDataBase();

        return result;
    }

    @Override
    public void addMoneda(Modelo.MonedaVO var1) throws ExcepcionMoneda {
    }

    @Override
    public void deleteMoneda(Integer var1) throws ExcepcionMoneda {
    }

    @Override
    public void editMoneda(Modelo.MonedaVO var1) throws ExcepcionMoneda {
    }

    @Override
    public int lastId() throws ExcepcionMoneda {
        return -1;
    }
}
