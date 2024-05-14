package bigbowl.employees;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    private String email;
    private String phone;

    public enum ShiftType {
        MORNING, EVENING
    }

    @Enumerated(EnumType.STRING)
    private ShiftType shift;


    @Temporal(TemporalType.TIME)
    private Date shiftStart;

    @Temporal(TemporalType.TIME)
    private Date shiftEnd;

    public ShiftType getShift() {
        return shift;
    }

    public void setShift(ShiftType shift) {
        this.shift = shift;
    }

    public Date getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(Date shiftStart) {
        this.shiftStart = shiftStart;
    }

    public Date getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(Date shiftEnd) {
        this.shiftEnd = shiftEnd;
    }
}
