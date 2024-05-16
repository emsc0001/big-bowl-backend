package bigbowl.equipmentOrders;

import bigbowl.employees.Employee;
import bigbowl.equipment.Equipment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderWithEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Equipment equipment;
    @ManyToOne
    private EquipmentOrder order;
    private int quantity;
}