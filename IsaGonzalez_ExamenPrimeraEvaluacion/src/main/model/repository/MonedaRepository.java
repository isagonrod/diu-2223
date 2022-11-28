package main.model.repository;

import Modelo.ExcepcionMoneda;
import main.model.MonedaVO;

import java.util.List;

public interface MonedaRepository {
    List<MonedaVO> loadMonedaList() throws ExcepcionMoneda;
}
