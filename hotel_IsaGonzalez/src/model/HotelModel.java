package model;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import model.repository.HotelRepository;
import model.repository.impl.HotelRepositoryImpl;
import util.BookingParse;
import util.CustomerParse;

public class HotelModel {
    private HotelRepository repository;

    public HotelModel() throws CommunicationsException {
        this.repository = new HotelRepositoryImpl();
    }

    public HotelModel(HotelRepository repository) {
        this.repository = repository;
    }

    public HotelRepository getRepository() {
        return this.repository;
    }

    public void setRepository(HotelRepository repository) {
        this.repository = repository;
    }

    public Customer getCustomer(String dni) throws CustomerException {
        return CustomerParse.parseToCustomer(repository.getCustomer(dni));
    }

    public void saveCustomer(Customer customer) throws CustomerException {
        repository.saveCustomer(CustomerParse.parseToCustomerVO(customer));
    }

    public void editCustomer(Customer customer) throws CustomerException {
        repository.editCustomer(CustomerParse.parseToCustomerVO(customer));
    }

    public void deleteCustomer(Customer customer) throws CustomerException {
        repository.deleteCustomer(customer.getDni());
    }

    public void saveBooking(Booking booking) throws BookingException {
        repository.saveBooking(BookingParse.parseToBookingVO(booking));
    }

    public void editBooking(Booking booking) throws BookingException {
        repository.editBooking(BookingParse.parseToBookingVO(booking));
    }

    public void deleteBooking(Booking booking) throws BookingException {
        repository.deleteBooking(booking.getCodReserva());
    }
}
