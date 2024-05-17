package bigbowl;

import bigbowl.airhockeytable.AirHockeyTable;
import bigbowl.airhockeytable.AirHockeyTableRepository;
import bigbowl.booking.Booking;
import bigbowl.booking.BookingRepository;
import bigbowl.bookingactivity.BookingActivity;
import bigbowl.bookingactivity.BookingActivityRepository;
import bigbowl.bowlinglane.BowlingLaneRepository;
import bigbowl.bowlinglane.BowlingLane;
import bigbowl.dinnertable.DinnerTable;
import bigbowl.dinnertable.DinnerTableRepository;
import bigbowl.employees.Employee;
import bigbowl.employees.EmployeeRepository;
import bigbowl.employees.EmployeeRole;
import bigbowl.equipment.Equipment;
import bigbowl.product.Product;
import bigbowl.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import bigbowl.equipment.EquipmentRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;

@Component
public class InitData implements CommandLineRunner {
    private final BowlingLaneRepository bowlingLaneRepository;
    private final ProductRepository productRepository;
    private final DinnerTableRepository dinnerTableRepository;
    private final EmployeeRepository employeeRepository;
    private final BookingActivityRepository bookingActivityRepository;
    private final AirHockeyTableRepository airHockeyTableRepository;
    private final BookingRepository bookingRepository;
    private final EquipmentRepository equipmentRepository;

    public InitData(
            BowlingLaneRepository bowlingLaneRepository,
            ProductRepository productRepository,
            DinnerTableRepository dinnerTableRepository,
            BookingActivityRepository bookingActivityRepository,
            AirHockeyTableRepository airHockeyTableRepository,
            BookingRepository bookingRepository,
            EmployeeRepository employeeRepository,
            EquipmentRepository equipmentRepository
    ) {
        this.bowlingLaneRepository = bowlingLaneRepository;
        this.productRepository = productRepository;
        this.dinnerTableRepository = dinnerTableRepository;
        this.employeeRepository = employeeRepository;
        this.bookingActivityRepository = bookingActivityRepository;
        this.airHockeyTableRepository = airHockeyTableRepository;
        this.bookingRepository = bookingRepository;
        this.equipmentRepository = equipmentRepository;
    }

    private void setupShiftTimes(Employee employee, Employee.ShiftType shiftType) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        if (shiftType == Employee.ShiftType.MORNING) {
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, 0);
            employee.setShiftStart(cal.getTime());

            cal.set(Calendar.HOUR_OF_DAY, 15);
            cal.set(Calendar.MINUTE, 59);
            employee.setShiftEnd(cal.getTime());
        } else if (shiftType == Employee.ShiftType.EVENING) {
            cal.set(Calendar.HOUR_OF_DAY, 16);
            cal.set(Calendar.MINUTE, 0);
            employee.setShiftStart(cal.getTime());

            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            employee.setShiftEnd(cal.getTime());
        }
    }
    @Override
    public void run(String... args) throws Exception {

        // Create some equipments
        Equipment equipment1 = new Equipment();
        equipment1.setName("Bowling Sko");
        equipmentRepository.save(equipment1);

        Equipment equipment2 = new Equipment();
        equipment2.setName("Ice Hockey Stave");
        equipmentRepository.save(equipment2);

        // Create some products
        Product product1 = new Product();
        product1.setName("Slik skål");
        product1.setPrice(35.0);
        product1.setImage("https://i.ibb.co/P6F4zdX/DALL-E-2024-05-17-15-30-31-A-colorful-candy-bowl-filled-with-a-variety-of-sweets-including-gummy-bea.webp");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Chips skål");
        product2.setPrice(45.0);
        product2.setImage("https://i.ibb.co/hcT2fBb/DALL-E-2024-05-17-15-33-49-A-vibrant-top-down-view-of-a-glass-bowl-filled-with-assorted-chips-on-a-c.webp");
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Drinks");
        product3.setPrice(100.0);
        product3.setImage("https://i.ibb.co/rFZvwG5/DALL-E-2024-05-17-15-34-33-A-vibrant-display-of-assorted-cocktails-on-a-bar-counter-The-scene-featur.webp");
        productRepository.save(product3);

        Employee employee1 = new Employee();
        employee1.setName("Jude Bellingham");
        employee1.setRole(EmployeeRole.MANAGER);
        employee1.setEmployeeImg("https://pbs.twimg.com/media/FtfoX_8XoAELrbg?format=jpg&name=900x900");
        employee1.setEmail("bellingoal@gmail.com");
        employee1.setPhone("12345678");
        employee1.setShift(Employee.ShiftType.MORNING);
        setupShiftTimes(employee1, Employee.ShiftType.MORNING);
        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Abdi Ox");
        employee2.setRole(EmployeeRole.MANAGER);
        employee2.setEmployeeImg("https://i2-prod.manchestereveningnews.co.uk/sport/football/article22735186.ece/ALTERNATES/s1200e/2_GettyImages-1357851686.jpg");
        employee2.setEmail("Ox@gmail.com");
        employee2.setPhone("22222222");
        employee2.setShift(Employee.ShiftType.EVENING);
        setupShiftTimes(employee2, Employee.ShiftType.EVENING);
        employeeRepository.save(employee2);


        // Ticket Sellers (Total 4)
        Employee employee3 = new Employee();
        employee3.setName("Ferland Fefe Mendy");
        employee3.setRole(EmployeeRole.TICKET_SELLER);
        employee3.setEmail("Fefe@gmail.com");
        employee3.setPhone("33333333");
        employee3.setShift(Employee.ShiftType.MORNING);
        setupShiftTimes(employee3, Employee.ShiftType.MORNING);
        employeeRepository.save(employee3);

        Employee employee4 = new Employee();
        employee4.setName("Cristiano Ronaldo");
        employee4.setRole(EmployeeRole.TICKET_SELLER);
        employee4.setEmail("ronaldo@gmail.com");
        employee4.setPhone("44444444");
        employee4.setShift(Employee.ShiftType.MORNING);
        setupShiftTimes(employee4, Employee.ShiftType.MORNING);
        employeeRepository.save(employee4);

        Employee employee5 = new Employee();
        employee5.setName("Neymar Jr");
        employee5.setRole(EmployeeRole.TICKET_SELLER);
        employee5.setEmail("neymar@gmail.com");
        employee5.setPhone("55555555");
        employee5.setShift(Employee.ShiftType.EVENING);
        setupShiftTimes(employee5, Employee.ShiftType.EVENING);
        employeeRepository.save(employee5);

        Employee employee6 = new Employee();
        employee6.setName("Eden Hazard");
        employee6.setRole(EmployeeRole.TICKET_SELLER);
        employee6.setEmail("hazard@gmail.com");
        employee6.setPhone("66666666");
        employee6.setShift(Employee.ShiftType.EVENING);
        setupShiftTimes(employee5, Employee.ShiftType.EVENING);
        employeeRepository.save(employee6);

// Equipment Operators (Total 2)
        Employee employee7 = new Employee();
        employee7.setName("Kevin De Bruyne");
        employee7.setRole(EmployeeRole.EQUIPMENT_OPERATOR);
        employee7.setEmail("debruyne@gmail.com");
        employee7.setPhone("77777777");
        employee7.setShift(Employee.ShiftType.MORNING);
        setupShiftTimes(employee7, Employee.ShiftType.MORNING);
        employeeRepository.save(employee7);

        Employee employee8 = new Employee();
        employee8.setName("Erling Haaland");
        employee8.setRole(EmployeeRole.EQUIPMENT_OPERATOR);
        employee8.setEmail("haaland@gmail.com");
        employee8.setPhone("88888888");
        employee8.setShift(Employee.ShiftType.EVENING);
        setupShiftTimes(employee8, Employee.ShiftType.EVENING);
        employeeRepository.save(employee8);

// Cleaning Staff (Total 4)
        Employee employee9 = new Employee();
        employee9.setName("Robert Lewandowski");
        employee9.setRole(EmployeeRole.CLEANING_STAFF);
        employee9.setEmail("lewandowski@gmail.com");
        employee9.setPhone("99999999");
        employee9.setShift(Employee.ShiftType.MORNING);
        setupShiftTimes(employee9, Employee.ShiftType.MORNING);
        employeeRepository.save(employee9);

        Employee employee10 = new Employee();
        employee10.setName("Virgil van Dijk");
        employee10.setRole(EmployeeRole.CLEANING_STAFF);
        employee10.setEmail("vandijk@gmail.com");
        employee10.setPhone("10101010");
        employee10.setShift(Employee.ShiftType.MORNING);
        setupShiftTimes(employee10, Employee.ShiftType.MORNING);
        employeeRepository.save(employee10);

        Employee employee11 = new Employee();
        employee11.setName("Mo Salah");
        employee11.setRole(EmployeeRole.CLEANING_STAFF);
        employee11.setEmail("salah@gmail.com");
        employee11.setPhone("11111111");
        employee11.setShift(Employee.ShiftType.EVENING);
        setupShiftTimes(employee11, Employee.ShiftType.EVENING);
        employeeRepository.save(employee11);

        Employee employee12 = new Employee();
        employee12.setName("Sadio Mane");
        employee12.setRole(EmployeeRole.CLEANING_STAFF);
        employee12.setEmail("mane@gmail.com");
        employee12.setPhone("12121212");
        employee12.setShift(Employee.ShiftType.EVENING);
        setupShiftTimes(employee12, Employee.ShiftType.EVENING);
        employeeRepository.save(employee12);

        // Create some Air Hockey Tables
        AirHockeyTable airHockey1 = new AirHockeyTable();
        airHockey1.setTableNumber(1);
        airHockeyTableRepository.save(airHockey1);

        AirHockeyTable airHockey2 = new AirHockeyTable();
        airHockey2.setTableNumber(2);
        airHockeyTableRepository.save(airHockey2);

        AirHockeyTable airHockey3 = new AirHockeyTable();
        airHockey3.setTableNumber(3);
        airHockeyTableRepository.save(airHockey3);

        AirHockeyTable airHockey4 = new AirHockeyTable();
        airHockey4.setTableNumber(4);
        airHockeyTableRepository.save(airHockey4);

        AirHockeyTable airHockey5 = new AirHockeyTable();
        airHockey5.setTableNumber(5);
        airHockeyTableRepository.save(airHockey5);

        AirHockeyTable airHockey6 = new AirHockeyTable();
        airHockey6.setTableNumber(6);
        airHockeyTableRepository.save(airHockey6);

// Create some Bowling Lanes
        BowlingLane lane1 = new BowlingLane();
        lane1.setLaneNumber(1);
        lane1.setIsForKids(false);
        bowlingLaneRepository.save(lane1);

        BowlingLane lane2 = new BowlingLane();
        lane2.setLaneNumber(2);
        lane2.setIsForKids(false);
        bowlingLaneRepository.save(lane2);

        BowlingLane lane3 = new BowlingLane();
        lane3.setLaneNumber(3);
        lane3.setIsForKids(false);
        bowlingLaneRepository.save(lane3);

        BowlingLane lane4 = new BowlingLane();
        lane4.setLaneNumber(4);
        lane4.setIsForKids(false);
        bowlingLaneRepository.save(lane4);

        BowlingLane lane5 = new BowlingLane();
        lane5.setLaneNumber(5);
        lane5.setIsForKids(false);
        bowlingLaneRepository.save(lane5);

        BowlingLane lane6 = new BowlingLane();
        lane6.setLaneNumber(6);
        lane6.setIsForKids(false);
        bowlingLaneRepository.save(lane6);

        BowlingLane lane7 = new BowlingLane();
        lane7.setLaneNumber(7);
        lane7.setIsForKids(false);
        bowlingLaneRepository.save(lane7);

        BowlingLane lane8 = new BowlingLane();
        lane8.setLaneNumber(8);
        lane8.setIsForKids(true);
        bowlingLaneRepository.save(lane8);

        BowlingLane lane9 = new BowlingLane();
        lane9.setLaneNumber(9);
        lane9.setIsForKids(true);
        bowlingLaneRepository.save(lane9);

        BowlingLane lane10 = new BowlingLane();
        lane10.setLaneNumber(10);
        lane10.setIsForKids(true);
        bowlingLaneRepository.save(lane10);


        // Create some dinner tables
        DinnerTable table1 = new DinnerTable();
        table1.setTableNumber(1);
        dinnerTableRepository.save(table1);

        DinnerTable table2 = new DinnerTable();
        table2.setTableNumber(2);
        dinnerTableRepository.save(table2);

        DinnerTable table3 = new DinnerTable();
        table3.setTableNumber(3);
        dinnerTableRepository.save(table3);


        DinnerTable table4 = new DinnerTable();
        table4.setTableNumber(4);
        dinnerTableRepository.save(table4);

        DinnerTable table5 = new DinnerTable();
        table5.setTableNumber(5);
        dinnerTableRepository.save(table5);
/*
        // Create a new booking activity
        BookingActivity activity1 = new BookingActivity();
        activity1.setStartTime(LocalDateTime.now());
        activity1.setEndTime(LocalDateTime.now().plusHours(2));
        activity1.setActivityType("Bowling");
        activity1.setBowlingLanes(Arrays.asList(lane1));
        activity1.setDinnerTables(Arrays.asList(table1));

        // Save the booking activity
        bookingActivityRepository.save(activity1);



        // Create a booking and associate with the activity
        Booking booking1 = new Booking();
        booking1.setActivities(Arrays.asList(activity1));
        booking1.setProducts(Arrays.asList(product1));

        bookingRepository.save(booking1);



 */


    }
}
