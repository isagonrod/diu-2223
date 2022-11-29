package ejemplo_CalculadoraEurosPesetas;

// VIEW
public interface InterfazVista {
	void setControlador(ControlConversor c);
	void arranca();

	double getCantidad();
	void escribeCambio(String s);

	static final String AEUROS="Pesetas a Euros";
	static final String APESETAS="Euros a Pesetas";
}
