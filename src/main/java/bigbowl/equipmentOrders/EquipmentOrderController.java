package bigbowl.equipmentOrders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}