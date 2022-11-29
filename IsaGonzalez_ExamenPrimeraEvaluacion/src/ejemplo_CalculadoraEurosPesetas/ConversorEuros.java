package ejemplo_CalculadoraEurosPesetas;

// MODEL
public class ConversorEuros {
	private double cambio;

	public ConversorEuros(double valorCambio) {
		// valor en la moneda de 1 euro
		cambio = valorCambio;
	}

	public double euroAmoneda(double cantidad) {
		return cantidad * cambio;
	}

	public double monedaAeuros(double cantidad) {
		return cantidad / cambio;
	}
}
