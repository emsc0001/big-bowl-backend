package bigbowl.airhockeytable;

import bigbowl.bookingactivity.BookingActivity;
import bigbowl.bookingactivity.BookingActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AirHockeyTableService {
    private final AirHockeyTableRepository repository;
    private final BookingActivityRepository bookingActivityRepository;

    public AirHockeyTableService(AirHockeyTableRepository repository, BookingActivityRepository bookingActivityRepository) {
        this.repository = repository;
        this.bookingActivityRepository = bookingActivityRepository;
    }

    public AirHockeyTable saveOrUpdate(AirHockeyTable airHockeyTable) {
        if (airHockeyTable.getId() == null) {
            return repository.save(airHockeyTable);
        } else {
            return repository.save(airHockeyTable);
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(Math.toIntExact(id));
    }

    public AirHockeyTable findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Iterable<AirHockeyTable> findAll() {
        return repository.findAll();
    }

    public List<AirHockeyTable> findAvailableAirHockeyTables(LocalDateTime startTime, LocalDateTime endTime) {
        // Hent alle bookingaktiviteter, der overlapper med det angivne tidsrum
        List<BookingActivity> overlappingActivities = bookingActivityRepository.findByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(endTime, startTime);

        // Hent alle airhockeyborde
        List<AirHockeyTable> allAirHockeyTables = repository.findAll();

        // Fjern de airhockeyborde, der allerede er booket i det angivne tidsrum
        for (BookingActivity activity : overlappingActivities) {
            allAirHockeyTables.removeAll(activity.getAirHockeyTables());
        }

        return allAirHockeyTables;
    }

}
