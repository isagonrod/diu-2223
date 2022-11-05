package ch.makery.money.model;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;

import java.util.ArrayList;

public class MonedaModelo {
	private MonedaRepository repository;

	public MonedaModelo() {}

	public MonedaRepository getRepository() {
		return repository;
	}

	public void setRepository(MonedaRepository repository) {
		this.repository = repository;
	}

	public ArrayList<MonedaVO> obtenerListaMonedas() throws ExcepcionMoneda {
		return this.repository.ObtenerListaMonedas();
	}
}
