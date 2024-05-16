package bigbowl.bookingactivity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingActivityRepository extends JpaRepository<BookingActivity, Integer> {
    List<BookingActivity> findByStartTimeBetweenAndEndTimeBetween(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime startTime1, LocalDateTime endTime1);
}


