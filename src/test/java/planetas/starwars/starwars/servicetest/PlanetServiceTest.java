package planetas.starwars.starwars.servicetest;

import planetas.starwars.starwars.service.PlanetService;
import planetas.starwars.starwars.repository.PlanetRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import planetas.starwars.starwars.domain.Planet;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlanetServiceTest {
    
    @Mock
    private static PlanetRepository _planetrepository;

    @Autowired
    private PlanetService _planetService;
    
    @BeforeAll
	public static void setUp() {
		_planetrepository = Mockito.mock(PlanetRepository.class);
    }
    
    @Test
    public void given_createplanets_should_return_ok() {

        Planet planetMock = new Planet("Alderaan", "temperate", "terreno 4", 2);

        when( _planetrepository.save(planetMock)).thenReturn(planetMock);

        Planet resultPlanet = _planetService.createPlanet(planetMock);

        assertEquals(planetMock.getName(), resultPlanet.getName());    
        assertEquals(planetMock.getClimate(), resultPlanet.getClimate());       
        assertEquals(planetMock.getGround(), resultPlanet.getGround());   
     }

     @Test
    public void given_getallplanets_should_return_ok() {

        Planet planetMock1 = new Planet("Tatooine", "teste1", "terreno 1", 1);
        Planet planetMock2 = new Planet("Alderaan", "teste2", "terreno 4", 0);
        List<Planet>  planets = new ArrayList<Planet>();
        planets.add(planetMock1);
        planets.add(planetMock2);
        when( _planetrepository.findAll()).thenReturn(planets);

        List<Planet> resultPlanets = _planetService.getAllPlanets();

        assertNotNull(resultPlanets);
     }

     @Test
     public void given_getplanetbyid_should_return_ok() {

         Planet planetMock1 = new Planet("Tatooine", "teste1", "terreno 1", 1);
         Optional<Planet> planetOptional = Optional.of(planetMock1);

         when( _planetrepository.findById("1")).thenReturn(planetOptional);
 
         Optional<Planet> resultPlanet = _planetService.getPlanetById("1");
 
         assertNotNull(resultPlanet);
      }

      
     @Test
     public void given_getplanetbyname_should_return_ok() {

         Planet planetMock1 = new Planet("Tatooine", "teste1", "terreno 1", 1);
         Optional<Planet> planetOptional = Optional.of(planetMock1);

         when( _planetrepository.findByName("Tatooine")).thenReturn(planetOptional);
 
         Optional<Planet> resultPlanet = _planetService.getPlanetByName("Tatooine");
 
         assertNotNull(resultPlanet);
      }
}