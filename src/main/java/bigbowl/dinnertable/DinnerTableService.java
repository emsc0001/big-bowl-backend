package bigbowl.dinnertable;

import org.springframework.stereotype.Service;

@Service
public class DinnerTableService {
    private final DinnerTableRepository dinnerTableRepository;

    public DinnerTableService(DinnerTableRepository dinnerTableRepository) {
        this.dinnerTableRepository = dinnerTableRepository;
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
}
