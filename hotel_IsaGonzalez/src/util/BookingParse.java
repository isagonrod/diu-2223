package util;

import model.Booking;
import model.BookingVO;

/**
 * Clase para convertir un objeto de la clase Booking en otro de la clase BookingVO, y viceversa.
 *
 * @author Isa González
 */
public class BookingParse {
    public static BookingVO parseToBookingVO(Booking booking) {
        BookingVO bookingVO = new BookingVO();

        bookingVO.setCodReserva(booking.getCodReserva());
        bookingVO.setFechaLlegada(booking.getFechaLlegada());
        bookingVO.setFechaSalida(booking.getFechaSalida());
        bookingVO.setNumHabitaciones(booking.getNumHabitaciones());
        bookingVO.setTipoHabitacion(booking.getTipoHabitacion());
        bookingVO.setFumador(booking.isFumador());
        bookingVO.setRegimenAlojamiento(booking.getRegimenAlojamiento());
        bookingVO.setDniCliente(booking.getDniCliente());

        return bookingVO;
    }

    public static Booking parseToBooking(BookingVO bookingVO) {
        Booking booking = new Booking();

        booking.setCodReserva(bookingVO.getCodReserva());
        booking.setFechaLlegada(bookingVO.getFechaLlegada());
        booking.setFechaSalida(bookingVO.getFechaSalida());
        booking.setNumHabitaciones(bookingVO.getNumHabitaciones());
        booking.setTipoHabitacion(bookingVO.getTipoHabitacion());
        booking.setFumador(bookingVO.isFumador());
        booking.setRegimenAlojamiento(bookingVO.getRegimenAlojamiento());
        booking.setDniCliente(bookingVO.getDniCliente());

        return booking;
    }
}
