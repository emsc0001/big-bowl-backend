package bigbowl.booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user/{username}")
    public List<Booking> getBookingsByUsername(@PathVariable String username) {
        return bookingService.findBookingsByUsername(username);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        System.out.println("Booking received: " + booking.toString());
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
