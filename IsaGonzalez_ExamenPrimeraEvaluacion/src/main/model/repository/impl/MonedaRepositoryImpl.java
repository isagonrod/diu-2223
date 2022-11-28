package main.model.repository.impl;

import Modelo.ExcepcionMoneda;
import com.mysql.cj.protocol.Resultset;
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
    public List<MonedaVO> loadMonedaList() throws ExcepcionMoneda {
        ResultSet rs = this.stmt.select("*", "moneda", null, null);
        List<MonedaVO> result = new ArrayList<>();
        MonedaVO tmp;

        try {
            while (rs.next()) {
                tmp = new MonedaVO();
                tmp.setCodigo(rs.getInt("codigo"));
                tmp.setNombre(rs.getString("nombre"));
                tmp.setMultiplicador(rs.getFloat("multiplicador"));
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
}
