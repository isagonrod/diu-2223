package main.model;

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

    public ArrayList<MonedaVO> getCoinList() throws ExcepcionMoneda {
        return this.repository.ObtenerListaMonedas();
    }

	public void convertCoin(Moneda coinToConvert) throws ExcepcionMoneda {
		repository.ObtenerListaMonedas();
	}

	public void deleteCoin(Moneda coinToDelete) throws ExcepcionMoneda {
		repository.deleteMoneda(coinToDelete.getCodigo());
	}
}
