package bigbowl.bowlinglane;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/BowlingLanes")
public class BowlingLaneController {
    private final BowlingLaneService bowlingLaneService;

    public BowlingLaneController(BowlingLaneService bowlingLaneService) {
        this.bowlingLaneService = bowlingLaneService;
    }

    @GetMapping("")
    public Iterable<BowlingLane> getAllBowlingLanes() {
        return bowlingLaneService.findAll();
    }

    @GetMapping("/{id}")
    public BowlingLane getBowlingLaneById(@PathVariable Long id) {
        return bowlingLaneService.findById(id);
    }

    @PostMapping("")
    public BowlingLane createBowlingLane(@RequestBody BowlingLane bowlingLane) {
        return bowlingLaneService.saveOrUpdate(bowlingLane);
    }

    @PutMapping("/{id}")
    public BowlingLane updateBowlingLane(@PathVariable Long id, @RequestBody BowlingLane bowlingLane) {
        bowlingLane.setId(id);
        return bowlingLaneService.saveOrUpdate(bowlingLane);
    }

    @DeleteMapping("/{id}")
    public void deleteBowlingLane(@PathVariable Long id) {
        bowlingLaneService.deleteById(id);
    }


    @GetMapping("/available")
    public List<BowlingLane> getAvailableBowlingLanes(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return bowlingLaneService.findAvailableBowlingLanes(startTime, endTime);
    }
}
