package bigbowl.booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Iterable<Booking> findByUserUsernameIgnoreCase(String username);
}
