package exam.management.be.controller.admin;

import exam.management.be.model.Modules;
import exam.management.be.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/modules")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @GetMapping
    public ResponseEntity<Iterable<Modules>> getAllModules() {
        return new ResponseEntity<>(moduleService.findAll(), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<Map<String, Object>>> getAllModules() {
//        return new ResponseEntity<>(moduleService.listModules(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Modules> getModule(@PathVariable Integer id) {
        Optional<Modules> moduleOptional = moduleService.findById(id);
        return moduleOptional.map(module -> new ResponseEntity<>(module, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping
    public ResponseEntity<Modules> createNewModule(@RequestBody Modules module) {
        return new ResponseEntity<>(moduleService.save(module), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modules> updateModule(@PathVariable Integer id, @RequestBody Modules modules) {
        Optional<Modules> moduleOptional = moduleService.findById(id);
        return moduleOptional.map(module -> {
            modules.setId(module.getId());
            return new ResponseEntity<>(moduleService.save(modules), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Modules> deleteModule(@PathVariable Integer id) {
        Optional<Modules> moduleOptional = moduleService.findById(id);
        return moduleOptional.map(module -> {
            moduleService.remove(id);
            return new ResponseEntity<>(module, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
