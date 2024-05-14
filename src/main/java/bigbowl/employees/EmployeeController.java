package bigbowl.employees;

import bigbowl.employees.Employee;
import bigbowl.employees.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Logger logger = Logger.getLogger(EmployeeController.class.getName());

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Request to fetch all employees");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            logger.warning("Failed to find employee with ID: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        return employee;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createOrUpdateEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        if (employee == null || id == null) {
            logger.warning("Attempt to update employee without valid data");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid employee data");
        }
        employee.setId(id);
        return employeeService.createOrUpdateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        logger.info("Deleted employee with ID: " + id);
    }
}
