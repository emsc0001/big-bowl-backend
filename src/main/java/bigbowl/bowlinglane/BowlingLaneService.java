package bigbowl.bowlinglane;

import bigbowl.bookingactivity.BookingActivity;
import bigbowl.bookingactivity.BookingActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BowlingLaneService {
private final BowlingLaneRepository bowlingLaneRepository;
private final BookingActivityRepository bookingActivityRepository;

    public BowlingLaneService(BowlingLaneRepository repository, BookingActivityRepository bookingActivityRepository) {
        this.bowlingLaneRepository = repository;
        this.bookingActivityRepository = bookingActivityRepository;
    }

    public BowlingLane saveOrUpdate(BowlingLane bowlingLane) {
        if (bowlingLane.getId() == null) {
            return bowlingLaneRepository.save(bowlingLane);
        } else {
            return bowlingLaneRepository.save(bowlingLane);
        }
    }

    public void deleteById(Long id) {
        bowlingLaneRepository.deleteById(Math.toIntExact(id));
    }

    public BowlingLane findById(Long id) {
        return bowlingLaneRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Iterable<BowlingLane> findAll() {
        return bowlingLaneRepository.findAll();
    }

    public List<BowlingLane> findAvailableBowlingLanes(LocalDateTime startTime, LocalDateTime endTime) {
        // Hent alle bookingaktiviteter, der overlapper med det angivne tidsrum
        List<BookingActivity> overlappingActivities = bookingActivityRepository.findByStartTimeBetweenAndEndTimeBetween(startTime, endTime, startTime, endTime);

        // Hent alle bowlingbaner
        List<BowlingLane> allBowlingLanes = bowlingLaneRepository.findAll();

        // Fjern de bowlingbaner, der allerede er booket i det angivne tidsrum
        for (BookingActivity activity : overlappingActivities) {
            allBowlingLanes.removeAll(activity.getBowlingLanes());
        }

        allBowlingLanes.removeIf(BowlingLane::isUnderMaintenance);

        return allBowlingLanes;
    }

}
