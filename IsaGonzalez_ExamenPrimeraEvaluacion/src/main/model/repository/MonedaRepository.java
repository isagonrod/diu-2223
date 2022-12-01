package main.model.repository;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;

import java.util.List;

public interface MonedaRepository {
	List<MonedaVO> obtenerListaMonedas() throws ExcepcionMoneda;

	void addMoneda(MonedaVO var1) throws ExcepcionMoneda;

	void deleteMoneda(Integer var1) throws ExcepcionMoneda;

	void editMoneda(MonedaVO var1) throws ExcepcionMoneda;

	int lastId() throws ExcepcionMoneda;
}