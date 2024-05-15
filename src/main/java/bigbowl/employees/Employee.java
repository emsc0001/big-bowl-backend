package bigbowl.employees;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "employees")
public class Employee {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;
}


