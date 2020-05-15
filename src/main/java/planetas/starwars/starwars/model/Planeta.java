package planetas.starwars.starwars.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planetas_starwars")
public class Planeta {
    @Id
    private String id;
  
    private String name;
    private String climate;
    private String ground;
  
    public Planeta() {  
  
    }
  
    public Planeta(String name, String climate, String ground) {
      this.name = name;
      this.climate = climate;
      this.ground = ground;
    }
  
    public String getId() {
      return id;
    }
  
    public String getName() {
      return name;
    }
  
    public void setName(String name) {
      this.name = name;
    }
  
    public String getClimate() {
      return climate;
    }
  
    public void setClimate(String climate) {
      this.climate = climate;
    }

    public String getGround() {
      return ground;
    }
  
    public void setGround(String ground) {
      this.ground = ground;
    }
  }