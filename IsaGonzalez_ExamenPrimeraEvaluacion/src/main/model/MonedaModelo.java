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

    public ArrayList<MonedaVO> getCurrencyList() throws ExcepcionMoneda {
        return this.repository.ObtenerListaMonedas();
    }

	public void convertCurrency(Moneda currencyToConvert) throws ExcepcionMoneda {
		repository.ObtenerListaMonedas();
	}

	public void deleteCurrency(Moneda currencyToDelete) throws ExcepcionMoneda {
		repository.deleteMoneda(currencyToDelete.getCodigo());
	}
}
