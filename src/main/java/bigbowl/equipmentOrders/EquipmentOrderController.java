package bigbowl.equipmentOrders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment_orders")
public class EquipmentOrderController {

    private final EquipmentOrderService equipmentOrderService;

    public EquipmentOrderController(EquipmentOrderService equipmentOrderService) {
        this.equipmentOrderService = equipmentOrderService;
    }

    @GetMapping
    public List<EquipmentOrder> getEquipmentOrders() {
        return equipmentOrderService.findAllEquipmentOrders();
    }

    @PostMapping
    public ResponseEntity<EquipmentOrder> createEquipmentOrder(@RequestBody EquipmentOrder newEquipmentOrder) {
        EquipmentOrder savedEquipmentOrder = equipmentOrderService.saveEquipmentOrder(newEquipmentOrder);
        return ResponseEntity.ok(savedEquipmentOrder);
    }
}