package bigbowl.booking;

import bigbowl.security_demo.entity.SpecialUser;
import bigbowl.security_demo.repository.SpecialUserRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository repository;
    private final SpecialUserRepository specialUserRepository;

    public BookingService(BookingRepository repository, SpecialUserRepository specialUserRepository) {
        this.repository = repository;
        this.specialUserRepository = specialUserRepository;
    }

    public Booking saveOrUpdate(Booking booking) {
        System.out.println("User in booking: " + booking.getUser()); // Temporary print statement

        SpecialUser user = specialUserRepository.findByUsernameIgnoreCase(booking.getUser().getUsername());
        if (user != null) {
            booking.setUser(user);
        }
        if (booking.getId() == null) {
            return repository.save(booking);
        } else {
            return repository.save(booking);
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(Math.toIntExact(id));
    }

    public Booking findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Iterable<Booking> findAll() {
        return repository.findAll();
    }
}
