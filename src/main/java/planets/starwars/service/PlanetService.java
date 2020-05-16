package planets.starwars.service;

import java.util.Optional;
import planets.starwars.externalservice.*;
import planets.starwars.domain.Planet;
import planets.starwars.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planets.starwars.externalservice.response.*;
import java.util.List;

@Service
public class PlanetService {
    
    @Autowired
    private PlanetRepository _planetRepository;
    @Autowired
    private SwapiRestClient _swapiRestClient;

    public PlanetService (){
        
    }

    public List<Planet> getAllPlanets(){    
        return _planetRepository.findAll();
    }

    public Optional<Planet> getPlanetById(String id){
        return _planetRepository.findById(id);       
    }

    public Optional<Planet> getPlanetByName(String name){
        return _planetRepository.findByName(name);
    }

    public Planet createPlanet(Planet planet){        
        int appearances = searchAppearanceByName(planet.getName());
        Optional<Planet> resultPlanet = _planetRepository.findByName(planet.getName());
        if (resultPlanet.isPresent()) {
           return resultPlanet.get();
        }
        return _planetRepository.save(new Planet(planet.getName(), planet.getClimate(), planet.getGround(), appearances));
    }

    public boolean deletePlanet(String id){
        try{
            _planetRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private int searchAppearanceByName(String name){
        PlanetSwapi resultSwapi = _swapiRestClient.returnPlanets(name).getPlanetSwapi();
        
        if(resultSwapi == null)
            return 0;

        return resultSwapi.getFilms().size();
    }
}