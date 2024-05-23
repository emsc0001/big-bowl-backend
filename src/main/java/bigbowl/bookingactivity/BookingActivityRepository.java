package bigbowl.bookingactivity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingActivityRepository extends JpaRepository<BookingActivity, Long> {
    List<BookingActivity> findByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(LocalDateTime endTime, LocalDateTime startTime);

    List<BookingActivity> findByStartTimeBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);
}


