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
import bigbowl.product.Product;
import bigbowl.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class InitData implements CommandLineRunner {
    private final BowlingLaneRepository bowlingLaneRepository;
    private final ProductRepository productRepository;
    private final DinnerTableRepository dinnerTableRepository;
    private final BookingActivityRepository bookingActivityRepository;
    private final AirHockeyTableRepository airHockeyTableRepository;
    private final BookingRepository bookingRepository;

    public InitData(
            BowlingLaneRepository bowlingLaneRepository,
            ProductRepository productRepository,
            DinnerTableRepository dinnerTableRepository,
            BookingActivityRepository bookingActivityRepository,
            AirHockeyTableRepository airHockeyTableRepository,
            BookingRepository bookingRepository
    ) {
        this.bowlingLaneRepository = bowlingLaneRepository;
        this.productRepository = productRepository;
        this.dinnerTableRepository = dinnerTableRepository;
        this.bookingActivityRepository = bookingActivityRepository;
        this.airHockeyTableRepository = airHockeyTableRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create some products
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(100.0);
        productRepository.save(product1);


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
