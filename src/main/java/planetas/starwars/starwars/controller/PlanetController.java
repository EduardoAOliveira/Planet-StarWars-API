package planetas.starwars.starwars.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import planetas.starwars.starwars.service.PlanetService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import planetas.starwars.starwars.domain.Planet;

@RequestMapping("/api")
@RestController
public class PlanetController {

    @Autowired
    PlanetService _planetService;
  
    @GetMapping("/planetas/all")
    public ResponseEntity<List<Planet>> getAllPlanets() {
      try {
        List<Planet> _planetas = _planetService.getAllPlanets(); 

        if (_planetas.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(_planetas, HttpStatus.OK);

      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
  
    @GetMapping("/planeta/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable("id") String id) {
      Optional<Planet> _planeta = _planetService.getPlanetById(id);

      if (_planeta.isPresent()) {
        return new ResponseEntity<>(_planeta.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/planetas/{name}")
    public ResponseEntity<Planet> getPlanetByName(@PathVariable("name") String name) {
      Optional<Planet> _planeta = _planetService.getPlanetByName(name);

      if (_planeta.isPresent()) {
        return new ResponseEntity<>(_planeta.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
  
    @Async
    @PostMapping("/planetas/create")
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet planeta) {
      try {
        Planet _planeta = _planetService.createPlanet(planeta);
        return new ResponseEntity<>(_planeta, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
      }
    }

    @DeleteMapping("/planetas/delete/{id}")
    public ResponseEntity<HttpStatus> deletePlanet(@PathVariable("id") String id) {
      try {
        _planetService.deletePlanet(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
      }
    }
  }