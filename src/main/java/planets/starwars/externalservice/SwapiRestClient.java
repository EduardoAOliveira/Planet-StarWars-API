package planets.starwars.externalservice;

import java.util.Arrays;
import planets.starwars.externalservice.response.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class SwapiRestClient {

    final static String url = "https://swapi.dev/api/planets/?search=";
	   
    RestTemplate restTemplate = new RestTemplate();

	public ResultSwapi returnPlanets(String name) {
		try { 
			return restTemplate.exchange(url.concat(name), HttpMethod.GET,getHeader(),ResultSwapi.class).getBody();
   		}catch(Exception e) {
   			throw e;
   		}
   	}
	
	private HttpEntity<String> getHeader(){
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return entity;
	}
}