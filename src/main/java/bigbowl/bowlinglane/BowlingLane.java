package bigbowl.bowlinglane;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class BowlingLane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int laneNumber;

    private boolean isForKids;

    private boolean underMaintenance;

    public void setIsForKids(boolean b) {
        this.isForKids = b;
    }
}

