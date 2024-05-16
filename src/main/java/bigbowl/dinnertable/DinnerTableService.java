package bigbowl.dinnertable;

import bigbowl.bookingactivity.BookingActivity;
import bigbowl.bookingactivity.BookingActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DinnerTableService {
    private final DinnerTableRepository dinnerTableRepository;
    private final BookingActivityRepository bookingActivityRepository;

    public DinnerTableService(DinnerTableRepository dinnerTableRepository, BookingActivityRepository bookingActivityRepository) {
        this.dinnerTableRepository = dinnerTableRepository;
        this.bookingActivityRepository = bookingActivityRepository;
    }

    public Iterable<DinnerTable> findAll() {
        return dinnerTableRepository.findAll();
    }

    public DinnerTable addDinnerTable(DinnerTable dinnerTable) {
        return dinnerTableRepository.save(dinnerTable);
    }

    public DinnerTable getDinnerTable(Long id) {
        return dinnerTableRepository.findById(Math.toIntExact(id)).orElse(null);
    }


    public void deleteDinnerTable(Long id) {
        dinnerTableRepository.deleteById(Math.toIntExact(id));
    }

    public List<DinnerTable> findAvailableDinnerTables(LocalDateTime startTime, LocalDateTime endTime) {
        // Hent alle bookingaktiviteter, der overlapper med det angivne tidsrum
        List<BookingActivity> overlappingActivities = bookingActivityRepository.findByStartTimeBetweenAndEndTimeBetween(startTime, endTime, startTime, endTime);

        // Hent alle bowlingbaner
        List<DinnerTable> allDinnerTables = dinnerTableRepository.findAll();

        // Fjern de bowlingbaner, der allerede er booket i det angivne tidsrum
        for (BookingActivity activity : overlappingActivities) {
            allDinnerTables.removeAll(activity.getDinnerTables());
        }

        return allDinnerTables;
    }
}
