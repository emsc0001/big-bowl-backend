package bigbowl.bowlinglane;

import bigbowl.bookingactivity.BookingActivity;
import bigbowl.bookingactivity.BookingActivityRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
        // Konverter LocalDateTime til ZonedDateTime
        ZoneId zoneId = ZoneId.of("Europe/Copenhagen"); // Erstat med din tidszone
        ZonedDateTime zonedStartTime = startTime.atZone(zoneId);
        ZonedDateTime zonedEndTime = endTime.atZone(zoneId);

        // Hent alle bookingaktiviteter, der overlapper med det angivne tidsrum
        List<BookingActivity> overlappingActivities = bookingActivityRepository.findByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(zonedEndTime.toLocalDateTime(), zonedStartTime.toLocalDateTime());

        System.out.println("Overlapping activities: " + overlappingActivities);

        // Hent alle bowlingbaner
        List<BowlingLane> allBowlingLanes = bowlingLaneRepository.findAll();

        // Fjern de bowlingbaner, der allerede er booket i det angivne tidsrum
        for (BookingActivity activity : overlappingActivities) {
            allBowlingLanes.removeAll(activity.getBowlingLanes());
        }

        allBowlingLanes.removeIf(BowlingLane::isUnderMaintenance);

        // Hvis det aktuelle tidspunkt er mellem kl. 10-17, fjern de baner, der er reserveret til klubben, og returner kun de første 10 tilgængelige baner
        if (startTime.getHour() >= 10 && endTime.getHour() <= 17 && !(startTime.getDayOfWeek() == DayOfWeek.SATURDAY || startTime.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            List<BowlingLane> clubReservedLanes = bowlingLaneRepository.findAll().subList(0, 14);
            allBowlingLanes.removeAll(clubReservedLanes);
            return allBowlingLanes.subList(0, Math.min(allBowlingLanes.size(), 10));
        }

        // Hvis det aktuelle tidspunkt er uden for kl. 10-17, returner alle tilgængelige baner
        return allBowlingLanes;
    }


}
