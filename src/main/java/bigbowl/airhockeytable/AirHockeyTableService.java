package bigbowl.airhockeytable;

import org.springframework.stereotype.Service;

@Service
public class AirHockeyTableService {
    private final AirHockeyTableRepository repository;

    public AirHockeyTableService(AirHockeyTableRepository repository) {
        this.repository = repository;
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
}
