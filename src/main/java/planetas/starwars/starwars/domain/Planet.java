package planetas.starwars.starwars.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planetas_starwars")
public class Planet {
    @Id
    private String id;
  
    private String name;
    private String climate;
    private String ground;
  
    private int appearances;

    public Planet(String name, String climate, String ground, int appearances) {
      this.name = name;
      this.climate = climate;
      this.ground = ground;
      this.appearances = appearances;
      validate();
    }
  
    private void validate(){
      if(name.isEmpty()){
        throw new Error("Nome inválido.");
      }
      if(climate.isEmpty()){
        throw new Error("Clima inválido.");
      }
      if(ground.isEmpty()){
        throw new Error("Terreno inválido.");
      }
    }

    public String getId() {
      return id;
    }
    
    public String getName() {
      return name;
    }
    
    public String getClimate() {
      return climate;
    }
  
    public String getGround() {
      return ground;
    }

    public int getAppearance() {
      return appearances;
    }
  }