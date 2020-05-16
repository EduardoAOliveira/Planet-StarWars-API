package planets.starwars.externalservice.response;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetSwapi {    

	private String name;

	private List<String> films;

	public PlanetSwapi(String name, List<String> films) {
		this.name = name;
		this.films = films;
    }
    
    public String getName(){
        return name;
    }
    public List<String> getFilms(){
        return films;
    }
}