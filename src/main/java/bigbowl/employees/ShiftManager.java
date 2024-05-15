package bigbowl.employees;

import bigbowl.employees.Employee;
import bigbowl.employees.EmployeeRole;
import bigbowl.employees.ShiftType;
import java.util.*;
import java.util.stream.Collectors;

public class ShiftManager {

    /**
     * Selects on-duty employees based on their shift type and required roles.
     *
     * @param allEmployees List of all employees.
     * @param roleRequirements Map of roles to the number of required employees per role.
     * @return Map of ShiftType to List of Employees who are on duty.
     */
    public Map<Employee.ShiftType, List<Employee>> selectOnDutyEmployees(List<Employee> allEmployees, Map<EmployeeRole, Integer> roleRequirements) {
        Map<Employee.ShiftType, List<Employee>> onDutyMap = new HashMap<>();

        for (Employee.ShiftType shift : Employee.ShiftType.values()) {
            List<Employee> shiftEmployees = allEmployees.stream()
                    .filter(emp -> emp.getShift() == shift)
                    .collect(Collectors.toList());

            List<Employee> onDuty = new ArrayList<>();
            roleRequirements.forEach((role, count) -> {
                onDuty.addAll(selectRandom(filterByRole(shiftEmployees, role), count));
            });

            onDutyMap.put(shift, onDuty);
        }

        return onDutyMap;
    }

    /**
     * Filters employees by their role.
     *
     * @param employees List of employees to filter.
     * @param role Role to filter by.
     * @return List of employees who have the specified role.
     */
    private List<Employee> filterByRole(List<Employee> employees, EmployeeRole role) {
        return employees.stream().filter(emp -> emp.getRole() == role).collect(Collectors.toList());
    }

    /**
     * Selects a random subset of employees.
     *
     * @param employees List of employees to select from.
     * @param count Number of employees to select.
     * @return Randomly selected list of employees.
     */
    private List<Employee> selectRandom(List<Employee> employees, int count) {
        Collections.shuffle(employees);
        return employees.stream().limit(count).collect(Collectors.toList());
    }
}
