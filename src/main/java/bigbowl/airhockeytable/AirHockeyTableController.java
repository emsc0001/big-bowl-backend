package bigbowl.airhockeytable;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/AirHockeyTables")
public class AirHockeyTableController {
    private final AirHockeyTableService airHockeyTableService;

    public AirHockeyTableController(AirHockeyTableService airHockeyTableService) {
        this.airHockeyTableService = airHockeyTableService;
    }

    @GetMapping("")
    public Iterable<AirHockeyTable> getAllAirHockeyTables() {
        return airHockeyTableService.findAll();
    }

    @GetMapping("/{id}")
    public AirHockeyTable getAirHockeyTableById(@PathVariable Long id) {
        return airHockeyTableService.findById(id);
    }

    @PostMapping("")
    public AirHockeyTable createAirHockeyTable(@RequestBody AirHockeyTable airHockeyTable) {
        return airHockeyTableService.saveOrUpdate(airHockeyTable);
    }

    @PutMapping("/{id}")
    public AirHockeyTable updateAirHockeyTable(@PathVariable Long id, @RequestBody AirHockeyTable airHockeyTable) {
        airHockeyTable.setId(id);
        return airHockeyTableService.saveOrUpdate(airHockeyTable);
    }

    @DeleteMapping("/{id}")
    public void deleteAirHockeyTable(@PathVariable Long id) {
        airHockeyTableService.deleteById(id);
    }
}