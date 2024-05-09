package bigbowl.bowlinglane;

import org.springframework.stereotype.Service;

@Service
public class BowlingLaneService {
private final BowlingLaneRepository repository;

    public BowlingLaneService(BowlingLaneRepository repository) {
        this.repository = repository;
    }

    public BowlingLane saveOrUpdate(BowlingLane bowlingLane) {
        if (bowlingLane.getId() == null) {
            return repository.save(bowlingLane);
        } else {
            return repository.save(bowlingLane);
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(Math.toIntExact(id));
    }

    public BowlingLane findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Iterable<BowlingLane> findAll() {
        return repository.findAll();
    }
}
