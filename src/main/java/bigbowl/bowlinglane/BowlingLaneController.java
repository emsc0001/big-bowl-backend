package bigbowl.bowlinglane;

import org.springframework.web.bind.annotation.*;

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
}
