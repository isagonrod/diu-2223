package main.model;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import main.util.ConversorVO;

import java.util.ArrayList;
import java.util.List;

public class MonedaModelo {
    private MonedaRepository repository;

    public MonedaModelo() {}

    public MonedaRepository getRepository() {
        return repository;
    }

    public void setRepository(MonedaRepository repository) {
        this.repository = repository;
    }

    public List<Moneda> getCurrencyList() throws ExcepcionMoneda {
        List<MonedaVO> lista = this.repository.ObtenerListaMonedas();
        List<Moneda> listaParseada = new ArrayList<>();

        for (MonedaVO moneda : lista) {
            listaParseada.add(ConversorVO.parseToMoneda(moneda));
        }

        return listaParseada;
    }

	public void convertCurrency(Moneda currencyToConvert) throws ExcepcionMoneda {
		repository.ObtenerListaMonedas();
	}

	public void deleteCurrency(Moneda currencyToDelete) throws ExcepcionMoneda {
		repository.deleteMoneda(currencyToDelete.getCodigo());
	}
}
