package planets.starwars.externalservice.response;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultSwapi {
    
    private List<PlanetSwapi> results;

    public ResultSwapi(List<PlanetSwapi> results)
    {
        this.results = results;
    }

    public PlanetSwapi getPlanetSwapi(){
        return results.get(0);
    }
}