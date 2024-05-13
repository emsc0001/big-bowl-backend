package bigbowl.repository;

import bigbowl.employees.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Additional custom query methods if needed
}

