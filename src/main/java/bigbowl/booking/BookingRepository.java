package bigbowl.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Iterable<Booking> findByUserUsernameIgnoreCase(String username);

}
