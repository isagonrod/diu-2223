package model.repository.impl;

import model.*;
import model.repository.HotelRepository;
import util.DatabaseConnection;
import util.DatabaseStatement;
import util.MySqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryImpl implements HotelRepository {
    protected final DatabaseConnection connection;
    protected final DatabaseStatement statement;

    public HotelRepositoryImpl() {
        this.connection = new MySqlConnection();
        this.connection.closeDataBase();
        this.statement = this.connection.getNewStatement();
    }

    @Override
    public void saveBooking(BookingVO newBooking) throws BookingException {
        String fields = "codReserva, fechaLlega, fechaSalida, numHabitaciones, tipoHabitacion, fumador, regimenAlojamiento";
        newBooking.setCodReserva(this.statement.getNextCodReserva("reserva", "codReserva"));
        String values = String.format("%d, '%s', '%s', %d, '%s', '%s', '%s'",
                newBooking.getCodReserva(), newBooking.getFechaLlegada(), newBooking.getFechaSalida(),
                newBooking.getNumHabitaciones(), newBooking.getTipoHabitacion(), newBooking.isFumador(),
                newBooking.getRegimenAlojamiento());
        if (this.statement.insert(fields, values, "reserva") == -1) {
            this.statement.closeStatement();
            this.connection.closeDataBase();
            throw new BookingException("Error al guardar la reserva");
        }
        this.statement.closeStatement();
        this.connection.closeDataBase();
    }

    @Override
    public void deleteBooking(int codReserva) throws BookingException {
        if (this.statement.delete("reserva", "codReserva=" + codReserva) == -1) {
            this.statement.closeStatement();
            this.connection.closeDataBase();
            throw new BookingException("Error al borrar la persona");
        }
        this.statement.closeStatement();
        this.connection.closeDataBase();
    }

    @Override
    public void editBooking(BookingVO booking) throws BookingException {
        String fields = String.format("codReserva=%d, fechaLlegada='%s', fechaSalida='%s', numHabitaciones=%d, tipoHabitacion='%s', fumador='%s', regimenAlojamiento='%s'",
                booking.getCodReserva(), booking.getFechaLlegada(), booking.getFechaSalida(), booking.getNumHabitaciones(),
                booking.getTipoHabitacion(), booking.isFumador(), booking.getRegimenAlojamiento());
        if (this.statement.update(fields, "reserva", "codReserva=" + booking.getCodReserva()) == -1) {
            this.statement.closeStatement();
            this.connection.closeDataBase();
            throw new BookingException("Error al editar la reserva");
        }
        this.statement.closeStatement();
        this.connection.closeDataBase();
    }

    @Override
    public List<BookingVO> loadBookingList() throws BookingException {
        ResultSet rs = this.statement.select("*", "reserva", null, "fechaLlegada DESC");
        List<BookingVO> result = new ArrayList<>();
        BookingVO tmp;
        try {
            while (rs.next()) {
                tmp = new BookingVO();
                tmp.setCodReserva(rs.getInt("codReserva"));
                tmp.setFechaLlegada(rs.getString("fechaLlegada"));
                tmp.setFechaSalida(rs.getString("fechaSalida"));
                tmp.setNumHabitaciones(rs.getInt("numHabitaciones"));
                tmp.setTipoHabitacion(rs.getString("tipoHabitacion"));
                tmp.setFumador(rs.getBoolean("fumador"));
                tmp.setRegimenAlojamiento(rs.getString("regimenAlojamiento"));
                result.add(tmp);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            this.statement.closeStatement();
            this.connection.closeDataBase();
            throw new BookingException("Error al listar las reservas");
        }
        this.statement.closeStatement();
        this.connection.closeDataBase();
        return result;
    }

    @Override
    public void saveCustomer(CustomerVO newCustomer) throws CustomerException {
        String fields = "dni, nombre, apellidos, direccion, localidad, provincia";
        newCustomer.setDni(this.statement.getNextDni("cliente", "dni"));
        String values = String.format("'%s', '%s', '%s', '%s', '%s', '%s'",
                newCustomer.getDni(), newCustomer.getNombre(), newCustomer.getApellidos(),
                newCustomer.getDireccion(), newCustomer.getLocalidad(), newCustomer.getProvincia());
        if (this.statement.insert(fields, values, "cliente") == -1) {
            this.statement.closeStatement();
            this.connection.closeDataBase();
            throw new CustomerException("Error al guardar el cliente");
        }
        this.statement.closeStatement();
        this.connection.closeDataBase();
    }

    @Override
    public void deleteCustomer(String dniCustomer) throws CustomerException {
        if(this.statement.delete("cliente", "dni=" + dniCustomer) == -1) {
            this.statement.closeStatement();
            this.connection.closeDataBase();
            throw new CustomerException("Error al borrar el cliente");
        }
        this.statement.closeStatement();
        this.connection.closeDataBase();
    }

    @Override
    public void editCustomer(CustomerVO customer) throws CustomerException {
        String fields = String.format("dni='%s', nombre='%s', apellidos='%s', direccion='%s', localidad='%s', provincia='%s'",
                customer.getDni(), customer.getNombre(), customer.getApellidos(),
                customer.getDireccion(), customer.getLocalidad(), customer.getProvincia());
        if (this.statement.update(fields, "cliente", "dni=" + customer.getDni()) == -1) {
            this.statement.closeStatement();
            this.connection.closeDataBase();
            throw new CustomerException("Error al editar el cliente");
        }
    }

    @Override
    public List<CustomerVO> loadCustomerList() throws CustomerException {
        ResultSet rs = this.statement.select("*", "cliente", null, "apellidos ASC");
        List<CustomerVO> result = new ArrayList<>();
        CustomerVO tmp;

        try {
            while (rs.next()) {
                tmp = new CustomerVO();
                tmp.setDni(rs.getString("dni"));
                tmp.setNombre(rs.getString("nombre"));
                tmp.setApellidos(rs.getString("apellidos"));
                tmp.setDireccion(rs.getString("direccion"));
                tmp.setLocalidad(rs.getString("localidad"));
                tmp.setProvincia(rs.getString("provincia"));
                result.add(tmp);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            this.statement.closeStatement();
            this.connection.closeDataBase();
            throw new CustomerException("Error al listar los clientes");
        }
        this.statement.closeStatement();
        this.connection.closeDataBase();
        return result;
    }
}
