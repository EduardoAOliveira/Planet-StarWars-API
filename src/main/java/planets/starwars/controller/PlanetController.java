package planets.starwars.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import planets.starwars.service.PlanetService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import planets.starwars.domain.Planet;

@RequestMapping("/api/v1")
@RestController
public class PlanetController {

    @Autowired
    PlanetService _planetService;
  
    @GetMapping("/planets")
    public ResponseEntity<List<Planet>> getAllPlanets(@RequestParam(required = false) String name) {
      try {
        List<Planet> planets = new ArrayList<Planet>();
          
        if (name != null){
          Optional<Planet> _planet = _planetService.getPlanetByName(name);          
          if (_planet.isPresent()) {
            planets.add(_planet.get());
            return new ResponseEntity<>(planets, HttpStatus.OK);
          } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
        }

        planets = _planetService.getAllPlanets(); 

        if (planets.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(planets, HttpStatus.OK);

      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
  
    @GetMapping("/planets/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable("id") String id) {
      try{
        Optional<Planet> _planet = _planetService.getPlanetById(id);
        if (_planet.isPresent()) {
          return new ResponseEntity<>(_planet.get(), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      }catch(Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/planets")
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet planeta) {
      try {
        Planet _planeta = _planetService.createPlanet(planeta);
        return new ResponseEntity<>(_planeta, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
      }
    }

    @DeleteMapping("/planets/{id}")
    public ResponseEntity<HttpStatus> deletePlanet(@PathVariable("id") String id) {
      try {
        _planetService.deletePlanet(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
      }
    }
  }