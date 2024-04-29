package pl.wsb.hotel.exceptions;

import java.time.LocalDate;

public class RoomReservedException extends RuntimeException {

    public RoomReservedException(String roomId, LocalDate reservationDate) {
        super(String.format("On %s, the room %s is booked.", reservationDate, roomId));
    }
}
