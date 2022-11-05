package model.repository.impl;

import model.BookingException;
import model.BookingVO;
import model.CustomerException;
import model.CustomerVO;
import model.repository.HotelRepository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class HotelRepositoryImpl implements HotelRepository {


    @Override
    public void saveBooking(BookingVO newBooking) throws BookingException {

    }

    @Override
    public void deleteBooking(int codReserva) throws BookingException {

    }

    @Override
    public void editBooking(BookingVO booking) throws BookingException {

    }

    @Override
    public List<BookingVO> loadBookingList() throws BookingException {
        return null;
    }

    @Override
    public void saveCustomer(CustomerVO newCustomer) throws CustomerException {

    }

    @Override
    public void deleteCustomer(String dniCustomer) throws CustomerException {

    }

    @Override
    public void editCustomer(CustomerVO customer) throws CustomerException {

    }

    @Override
    public List<CustomerVO> loadCustomerList() throws CustomerException {
        return null;
    }
}
