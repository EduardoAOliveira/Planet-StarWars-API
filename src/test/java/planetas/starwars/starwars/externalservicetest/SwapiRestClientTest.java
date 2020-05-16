package planetas.starwars.starwars.externalservicetest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import planetas.starwars.starwars.externalservice.*;
import planetas.starwars.starwars.externalservice.response.*;


@SpringBootTest
public class SwapiRestClientTest {
    
    @Autowired
    static SwapiRestClient rest;

    @BeforeAll
	public static void setUp() {
		rest = new SwapiRestClient();
	}
    
	@Test
    public void given_returnPlanets_shoud_return_ok() {

        String nameMock = "Alderaan";
		ResultSwapi result = rest.returnPlanets(nameMock);
    	assertNotNull(result);
     }

}
