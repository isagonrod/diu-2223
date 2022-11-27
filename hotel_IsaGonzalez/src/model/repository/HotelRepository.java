package model.repository;

import model.BookingException;
import model.BookingVO;
import model.CustomerException;
import model.CustomerVO;

import java.util.List;

/**
 * Interfaz que define cómo implementar el repositorio del hotel.
 *
 * @author Isa González
 */
public interface HotelRepository {
	/**
	 * Libera recursos mediante el cierre de la sentencia y la conexión usados por esta instancia del repositorio.
	 */
	void closeConnection();

	/**
	 * Abre una conexión a la base de datos e inserta una nueva reserva.
	 *
	 * @param newBooking Objeto BookingVO con los datos de la nueva reserva
	 * @return El identificador autogenerado de la nueva reserva
	 * @throws BookingException Excepción lanzada si ocurre un error controlado
	 */
    int saveBooking(BookingVO newBooking) throws BookingException;

	/**
	 * Abre una conexión a la base de datos y borra una reserva especificada por su identificador.
	 *
	 * @param codReserva Identificador único de cada reserva
	 * @throws BookingException Excepción lanzada si ocurre un error controlado
	 */
    void deleteBooking(int codReserva) throws BookingException;

	/**
	 * Abre una conexión a la base de datos y edita la reserva recibida como parámetro.
	 *
	 * @param booking Datos para enviar a la base de datos
	 * @throws BookingException Excepción lanzada si ocurre un error controlado
	 */
    void editBooking(BookingVO booking) throws BookingException;

	/**
	 * Abre una conexión a la base de datos y hace una consulta de todos los datos de la tabla 'reserva'.
	 *
	 * @return Devuelve una lista de objetos BookingVO con los datos de las reservas
	 * @throws BookingException Excepción lanzada si ocurre un error controlado
	 */
    List<BookingVO> loadBookingList() throws BookingException;

	/**
	 * Abre una conexión a la base de datos y busca un elemento en la tabla 'cliente'.
	 *
	 * @param dni El DNI del cliente a buscar
	 * @return Devuelve un objeto CustomerVO con los datos del cliente, o null si no existe
	 * @throws CustomerException Excepción lanzada si ocurre un error controlado
	 */
	CustomerVO getCustomer(String dni) throws CustomerException;

	/**
	 * Abre una conexión a la base de datos e inserta un nuevo cliente.
	 *
	 * @param newCustomer Objeto CustomerVO con los datos del nuevo cliente
	 * @throws CustomerException Excepción lanzada si ocurre un error controlado
	 */
    void saveCustomer(CustomerVO newCustomer) throws CustomerException;

	/**
	 * Abre una conexión a la base de datos y borra un cliente especificado por su identificador.
	 *
	 * @param dniCustomer Identificador único del cliente
	 * @throws CustomerException Excepción lanzada si ocurre un error controlado
	 */
    void deleteCustomer(String dniCustomer) throws CustomerException;

	/**
	 * Abre una conexión a la base de datos y edita los datos del cliente recibido por parámetro.
	 *
	 * @param customer Datos para enviar a la base de datos
	 * @throws CustomerException Excepción lanzada si ocurre un error controlado
	 */
    void editCustomer(CustomerVO customer) throws CustomerException;

	/**
	 * Abre una conexión a la base de datos y hace una consulta de todos los datos de la tabla 'cliente'.
	 *
	 * @return Devuelve una lista de objetos CustomerVO con los datos de los clientes
	 * @throws CustomerException Excepción lanzada si ocurre un error controlado
	 */
    List<CustomerVO> loadCustomerList() throws CustomerException;
}
