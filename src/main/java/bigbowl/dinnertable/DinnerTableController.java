package bigbowl.dinnertable;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/DinnerTable")
public class DinnerTableController {
    private final DinnerTableService dinnerTableService;

    public DinnerTableController(DinnerTableService dinnerTableService) {
        this.dinnerTableService = dinnerTableService;
    }

    @GetMapping("")
    public Iterable<DinnerTable> getAllDinnerTables() {
        return dinnerTableService.findAll();
    }

    @GetMapping("/{id}")
    public DinnerTable getDinnerTable(@PathVariable Long id) {
        return dinnerTableService.getDinnerTable(id);
    }

    @PostMapping
    public DinnerTable addDinnerTable(DinnerTable dinnerTable) {
        return dinnerTableService.addDinnerTable(dinnerTable);
    }

    @PutMapping("/{id}")
    public DinnerTable updateDinnerTable(@PathVariable Long id, DinnerTable dinnerTable) {
        dinnerTable.setId(id);
        return dinnerTableService.addDinnerTable(dinnerTable);
    }

    @DeleteMapping("/{id}")
    public void deleteDinnerTable(@PathVariable Long id) {
        dinnerTableService.deleteDinnerTable(id);
    }

    @GetMapping("/available")
    public List<DinnerTable> getAvailableDinnerTables(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return dinnerTableService.findAvailableDinnerTables(startTime, endTime);
    }
}
