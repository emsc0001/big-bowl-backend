package bigbowl.booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("")
    public Iterable<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @PostMapping
    public Booking createBooking(Booking booking) {
        return bookingService.saveOrUpdate(booking);
    }


    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, Booking booking) {
        booking.setId(id);
        return bookingService.saveOrUpdate(booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
    }
}
