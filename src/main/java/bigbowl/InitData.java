package bigbowl;

import bigbowl.airhockeytable.AirHockeyTable;
import bigbowl.airhockeytable.AirHockeyTableRepository;
import bigbowl.booking.Booking;
import bigbowl.booking.BookingRepository;
import bigbowl.bookingactivity.BookingActivity;
import bigbowl.bookingactivity.BookingActivityRepository;
import bigbowl.bowlinglane.BowlinLaneRepository;
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
    private final BowlinLaneRepository bowlingLaneRepository;
    private final ProductRepository productRepository;
    private final DinnerTableRepository dinnerTableRepository;
    private final BookingActivityRepository bookingActivityRepository;
    private final AirHockeyTableRepository airHockeyTableRepository;
    private final BookingRepository bookingRepository;

    public InitData(
            BowlinLaneRepository bowlingLaneRepository,
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



        // Create some bowling lanes
        BowlingLane lane1 = new BowlingLane();
        lane1.setLaneNumber(1);
        lane1.setIsForKids(true);
        bowlingLaneRepository.save(lane1);

        // Create some dinner tables
        DinnerTable table1 = new DinnerTable();
        table1.setTableNumber(1);
        dinnerTableRepository.save(table1);
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
