package bigbowl.equipment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public List<Equipment> getAllEquipment() {
        return equipmentService.findAllEquipment();
    }


@PutMapping("/{id}")
public ResponseEntity<Equipment> updateEquipmentStatus(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
    Equipment equipment = equipmentService.findEquipmentById(id);
    if (equipment == null) {
        return ResponseEntity.notFound().build();
    }

    equipment.setStatus(updatedEquipment.getStatus());
    Equipment savedEquipment = equipmentService.saveEquipment(equipment);
    return ResponseEntity.ok(savedEquipment);
}
}