package bigbowl.equipmentOrders;

import bigbowl.employees.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class EquipmentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String type;
    private String status;
    private String additionalDetails;

    @ManyToOne
    private Employee employee;
    private LocalDate orderDate;
    private int quantity;

    // Default constructor
    public EquipmentOrder() {
    }

    // Constructor with required fields
    public EquipmentOrder(String name, String type, String status, String additionalDetails, int quantity) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.additionalDetails = additionalDetails;
        this.quantity = quantity;
    }
}
