package bigbowl.equipmentOrders;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentOrderService {

    public final EquipmentOrderRepository equipmentOrderRepository;

    public EquipmentOrderService(EquipmentOrderRepository equipmentOrderRepository) {
        this.equipmentOrderRepository = equipmentOrderRepository;
    }

    public List<EquipmentOrder> findAllEquipmentOrders() {
        return equipmentOrderRepository.findAll();
    }
}