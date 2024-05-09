package bigbowl.booking;

import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking saveOrUpdate(Booking booking) {
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
