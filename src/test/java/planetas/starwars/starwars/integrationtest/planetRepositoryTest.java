package planetas.starwars.starwars.integrationtest;

import planetas.starwars.starwars.repository.PlanetRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import planetas.starwars.starwars.domain.Planet;
import java.util.List;
import java.util.Optional;

@SpringBootTest

public class planetRepositoryTest {
    
    @Autowired
    private PlanetRepository _planetrepository;
    
	@Test
    public void given_createplanets_should_return_ok() {

        Planet planetMock = new Planet("Alderaan", "temperate", "terreno 4", 0);
		Planet resultPlanet = _planetrepository.save(planetMock);
        assertEquals(planetMock, resultPlanet);
        
        _planetrepository.delete(planetMock);
     }

    @Test
    public void given_findallplanets_should_return_ok(){
        Planet planetMock1 = new Planet("Tatooine", "teste1", "terreno 1", 1);
        Planet planetMock2 = new Planet("Alderaan", "teste2", "terreno 4", 0);
        _planetrepository.save(planetMock1);
        _planetrepository.save(planetMock2);

        List<Planet> planets = _planetrepository.findAll();

        assertNotNull(planets);
        
        _planetrepository.delete(planetMock1);
        _planetrepository.delete(planetMock2);
    }

    @Test
    public void given_findplanetbyid_should_return_ok(){
        Planet planetMock = new Planet("Tatooine", "teste1", "terreno 1", 1);
        String idPlanet = _planetrepository.save(planetMock).getId();

        Optional<Planet> resultPlanet = _planetrepository.findById(idPlanet);

        assertNotNull(resultPlanet);
        assertEquals(idPlanet, resultPlanet.get().getId());
        _planetrepository.deleteById(idPlanet);
    }

    @Test
    public void given_findplanetbyname_should_return_ok(){
        Planet planetMock = new Planet("Tatooine", "teste1", "terreno 1", 1);
        String namePlanet = _planetrepository.save(planetMock).getName();

        Optional<Planet> resultPlanet = _planetrepository.findByName(namePlanet);

        assertNotNull(resultPlanet);
        assertEquals(namePlanet, resultPlanet.get().getName());
        _planetrepository.deleteById(resultPlanet.get().getId());
    }
}