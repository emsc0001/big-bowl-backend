package bigbowl.bookingactivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/booking-activities")
public class BookingActivityController {
    private final BookingActivityService bookingActivityService;

    @Autowired
    public BookingActivityController(BookingActivityService bookingActivityService) {
        this.bookingActivityService = bookingActivityService;
    }

    @GetMapping("")
    public ResponseEntity<List<BookingActivity>> getAllActivities() {
        List<BookingActivity> activities = bookingActivityService.findAll();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingActivity> getActivityById(@PathVariable Long id) {
        BookingActivity activity = bookingActivityService.findById(id);
        if (activity != null) {
            return ResponseEntity.ok(activity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<BookingActivity> createActivity(@RequestBody BookingActivity bookingActivity) {
        BookingActivity savedActivity = bookingActivityService.saveOrUpdate(bookingActivity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingActivity> updateActivity(
            @PathVariable Long id, @RequestBody BookingActivity bookingActivity) {
        bookingActivity.setId(id); // Indstil ID'et for at sikre korrekt opdatering
        BookingActivity updatedActivity = bookingActivityService.saveOrUpdate(bookingActivity);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        bookingActivityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/book-whole-day")
    public ResponseEntity<String> bookWholeDayForBowlingLanes(@RequestParam String date) {
        try {
            LocalDate bookingDate = LocalDate.parse(date);
            bookingActivityService.bookWholeDayForBowlingLanes(bookingDate);
            return ResponseEntity.ok("The whole day has been booked for bowling lanes");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }
}
