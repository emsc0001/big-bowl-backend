package bigbowl.bookingactivity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingActivityService {
    private final BookingActivityRepository repository;

    @PersistenceContext
    private EntityManager entityManager; // Injicer EntityManager

    public BookingActivityService(BookingActivityRepository repository) {
        this.repository = repository;
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
}
