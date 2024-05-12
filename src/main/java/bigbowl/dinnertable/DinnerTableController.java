package bigbowl.dinnertable;


import org.springframework.web.bind.annotation.*;

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
}
