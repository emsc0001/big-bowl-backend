package bigbowl.bookingactivity;

import bigbowl.airhockeytable.AirHockeyTable;
import bigbowl.booking.Booking;
import bigbowl.bowlinglane.BowlingLane;
import bigbowl.dinnertable.DinnerTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class BookingActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tidspunkt for aktivitetens start og slut
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Type af aktivitet (f.eks. bowling, air-hockey, spisebord osv.)
    private String activityType;

    // Liste over bowlingbaner tilknyttet aktiviteten
    @ManyToMany
    private List<BowlingLane> bowlingLanes;

    // Liste over air-hockey borde tilknyttet aktiviteten
    @ManyToMany
    private List<AirHockeyTable> airHockeyTables;

    // Liste over spiseborde tilknyttet aktiviteten
    @ManyToMany
    private List<DinnerTable>  dinnerTables;

    // Reference til den booking, aktiviteten tilhører
    @JsonIgnore
    @ManyToOne
    private Booking booking;
}


