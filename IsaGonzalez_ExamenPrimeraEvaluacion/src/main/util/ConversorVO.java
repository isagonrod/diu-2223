package main.util;

import main.model.Moneda;
import main.model.MonedaVO;

public class ConversorVO {
    public static MonedaVO parseToMonedaVO(Moneda moneda) {
        MonedaVO monedaVO = new MonedaVO();
        monedaVO.setCodigo(moneda.getCodigo());
        monedaVO.setNombre(moneda.getNombre());
        monedaVO.setMultiplicador(moneda.getMultiplicador());
        return monedaVO;
    }

    public static Moneda parseToMoneda(Modelo.MonedaVO monedaVO) {
        Moneda moneda = new Moneda();
        moneda.setCodigo(monedaVO.getCodigo());
        moneda.setNombre(monedaVO.getNombre());
        moneda.setMultiplicador(monedaVO.getMultiplicador());
        return moneda;
    }
}
