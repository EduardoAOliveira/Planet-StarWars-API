package planetas.starwars.starwars.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestParam;
import planetas.starwars.starwars.repository.PlanetRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import planetas.starwars.starwars.model.Planet;

@RequestMapping("/api")
@RestController
public class PlanetController {

    @Autowired
    PlanetRepository planetRepository;
  
    @GetMapping("/planetas/all")
    public ResponseEntity<List<Planet>> getAllPlanetas(@RequestParam(required = false) String name) {
      try {
        List<Planet> _planetas = new ArrayList<Planet>();      
        planetRepository.findAll().forEach(_planetas::add);

        if (_planetas.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(_planetas, HttpStatus.OK);

      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
  
    @GetMapping("/planeta/{id}")
    public ResponseEntity<Planet> getPlanetasById(@PathVariable("id") String id) {
      Optional<Planet> _planeta = planetRepository.findById(id);

      if (_planeta.isPresent()) {
        return new ResponseEntity<>(_planeta.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/planetas/{name}")
    public ResponseEntity<Planet> getPlanetasByName(@PathVariable("name") String name) {
      Optional<Planet> _planeta = planetRepository.findByName(name);

      if (_planeta.isPresent()) {
        return new ResponseEntity<>(_planeta.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
  
    @PostMapping("/planetas/create")
    public ResponseEntity<Planet> createPlaneta(@RequestBody Planet planeta) {
      try {
        Planet _planeta = planetRepository.save(new Planet(planeta.getName(), planeta.getClimate(), planeta.getGround()));
        return new ResponseEntity<>(_planeta, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
      }
    }

    @DeleteMapping("/planetas/delete/{id}")
    public ResponseEntity<HttpStatus> deletePlaneta(@PathVariable("id") String id) {
      try {
        planetRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
      }
    }
  }