package bigbowl.booking;

import bigbowl.bookingactivity.BookingActivity;
import bigbowl.bookingactivity.BookingActivityRepository;
import bigbowl.bowlinglane.BowlinLaneRepository;
import bigbowl.bowlinglane.BowlingLane;
import bigbowl.security_demo.entity.SpecialUser;
import bigbowl.security_demo.repository.SpecialUserRepository;
import org.hibernate.PersistentObjectException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository repository;
    private final SpecialUserRepository specialUserRepository;
    private final BookingActivityRepository bookingActivityRepository;

    public BookingService(BookingRepository repository, SpecialUserRepository specialUserRepository, BookingActivityRepository bookingActivityRepository) {
        this.repository = repository;
        this.specialUserRepository = specialUserRepository;
        this.bookingActivityRepository = bookingActivityRepository;
    }

    public Booking saveOrUpdate(Booking booking) {
        if (booking.getUser() != null) {
            System.out.println("User in booking: " + booking.getUser()); // Temporary print statement

            SpecialUser user = specialUserRepository.findByUsernameIgnoreCase(booking.getUser().getUsername());
            if (user != null) {
                booking.setUser(user);
            }
        }

        // Gem bookingen i databasen først
        Booking savedBooking;
        if (booking.getId() == null) {
            savedBooking = repository.save(booking);
        } else {
            savedBooking = repository.save(booking);
        }

        // Derefter opdater hver BookingActivity i databasen
        if (booking.getActivities() != null) {
            for (int i = 0; i < booking.getActivities().size(); i++) {
                BookingActivity activity = booking.getActivities().get(i);
                if (activity.getId() != null) {
                    // Hvis activity har et id, hent det eksisterende BookingActivity objekt fra databasen
                    BookingActivity existingActivity = bookingActivityRepository.findById(activity.getId()).orElse(null);
                    if (existingActivity != null) {
                        // Opdater det eksisterende BookingActivity objekt med de nye værdier
                        existingActivity.setBooking(savedBooking);
                        existingActivity.setStartTime(activity.getStartTime());
                        existingActivity.setEndTime(activity.getEndTime());

                        // Gem det opdaterede BookingActivity objekt i databasen
                        BookingActivity savedActivity = bookingActivityRepository.save(existingActivity);
                        savedBooking.getActivities().set(i, savedActivity);
                    }
                } else {
                    // Hvis activity ikke har et id, gem det som et nyt BookingActivity objekt i databasen
                    activity.setBooking(savedBooking);
                    BookingActivity savedActivity = bookingActivityRepository.save(activity);
                    savedBooking.getActivities().set(i, savedActivity);
                }
            }
        }

        return savedBooking;
    }

    public void deleteById(Long id) {
        repository.deleteById(Math.toIntExact(id));
    }

    public List<Booking> findBookingsByUsername(String username) {
        return (List<Booking>) repository.findByUserUsernameIgnoreCase(username);
    }


    public Booking findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Iterable<Booking> findAll() {
        return repository.findAll();
    }
}


