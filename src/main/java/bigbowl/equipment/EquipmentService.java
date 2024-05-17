package bigbowl.equipment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> findAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment findEquipmentById(Long id) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        return optionalEquipment.orElse(null);
    }

    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }
}
