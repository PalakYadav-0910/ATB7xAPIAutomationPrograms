package org.apiautomation.ex_22092024.gson.Serialization;

public class BookingResponse {

    private Integer bookingid;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    private Booking booking;

}
