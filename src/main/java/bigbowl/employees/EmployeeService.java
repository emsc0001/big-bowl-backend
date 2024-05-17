package bigbowl.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

@Service
public class EmployeeService {
    private static final Date MORNING_SHIFT_START = new GregorianCalendar(0,0,0, 9, 0).getTime();
    private static final Date MORNING_SHIFT_END = new GregorianCalendar(0,0,0, 16, 0).getTime();
    private static final Date EVENING_SHIFT_START = new GregorianCalendar(0,0,0, 16, 0).getTime();
    private static final Date EVENING_SHIFT_END = new GregorianCalendar(0,0,0, 23, 59).getTime();
    private final EmployeeRepository employeeRepository;
    private final Logger logger = Logger.getLogger(EmployeeService.class.getName());

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
    }

    @Transactional
    public Employee createOrUpdateEmployee(Employee employee) {
        setShiftTimes(employee);
        Employee savedEmployee = employeeRepository.save(employee);
        logger.info("Saved employee: " + savedEmployee.getId());
        return savedEmployee;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        logger.info("Deleted employee: " + id);
    }

    private void setShiftTimes(Employee employee) {
        if (Employee.ShiftType.MORNING.equals(employee.getShift())) {
            employee.setShiftStart(MORNING_SHIFT_START);
            employee.setShiftEnd(MORNING_SHIFT_END);
        } else if (Employee.ShiftType.EVENING.equals(employee.getShift())) {
            employee.setShiftStart(EVENING_SHIFT_START);
            employee.setShiftEnd(EVENING_SHIFT_END);
        } else {
            throw new IllegalArgumentException("Invalid shift: " + employee.getShift());
        }
        logger.fine("Shift times set for employee: " + employee.getId());
    }
}
