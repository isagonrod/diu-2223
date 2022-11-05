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
    void saveBooking(BookingVO newBooking) throws BookingException;
    void deleteBooking(int codReserva) throws BookingException;
    void editBooking(BookingVO booking) throws BookingException;
    List<BookingVO> loadBookingList() throws BookingException;

    void saveCustomer(CustomerVO newCustomer) throws CustomerException;
    void deleteCustomer(String dniCustomer) throws CustomerException;
    void editCustomer(CustomerVO customer) throws CustomerException;
    List<CustomerVO> loadCustomerList() throws CustomerException;
}
