package bigbowl.bookingactivity;

import bigbowl.bowlinglane.BowlingLane;
import bigbowl.bowlinglane.BowlingLaneRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingActivityService {
    private final BookingActivityRepository repository;
    private final BowlingLaneRepository bowlingLaneRepository;

    @PersistenceContext
    private EntityManager entityManager; // Injicer EntityManager

    public BookingActivityService(BookingActivityRepository repository, BowlingLaneRepository bowlingLaneRepository) {
        this.repository = repository;
        this.bowlingLaneRepository = bowlingLaneRepository;
    }

    @Transactional
    public BookingActivity saveOrUpdate(BookingActivity activity) {
        if (activity.getId() == null) {
            // Hvis objektet er nyt, brug repository's save-metode
            return repository.save(activity);
        } else {
            // Brug EntityManager's merge til at opdatere eksisterende objekt
            return entityManager.merge(activity);
        }
    }

    

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public BookingActivity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<BookingActivity> findAll() {
        return repository.findAll();
    }


    public void bookWholeDayForBowlingLanes(LocalDate date) {
        // Check if there are existing bookings for the date
        List<BookingActivity> existingBookings = repository.findByStartTimeBetween(date.atStartOfDay(), date.atTime(23, 59));
        if (!existingBookings.isEmpty()) {
            throw new IllegalArgumentException("The day already has bookings");
        }

        // Get all bowling lanes
        List<BowlingLane> bowlingLanes = bowlingLaneRepository.findAll();

        // Create BookingActivity entity for the whole day
        BookingActivity activity = new BookingActivity();
        activity.setStartTime(date.atStartOfDay());
        activity.setEndTime(date.atTime(23, 59));
        activity.setActivityType("Bowling");
        activity.setBowlingLanes(bowlingLanes);

        repository.save(activity);
    }
}
