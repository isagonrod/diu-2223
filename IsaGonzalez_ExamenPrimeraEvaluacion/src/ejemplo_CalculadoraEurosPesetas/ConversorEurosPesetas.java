package ejemplo_CalculadoraEurosPesetas;

// MODEL
public class ConversorEurosPesetas extends ConversorEuros {
	public ConversorEurosPesetas() {
		super(166.386D);
	}

	public double eurosApesetas(double cantidad) {
		return euroAmoneda(cantidad);
	}

	public double pesetasAeuros(double cantidad) {
		return monedaAeuros(cantidad);
	}
}
