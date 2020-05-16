package planets.starwars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import planets.starwars.domain.Planet;
import java.util.Optional;

public interface PlanetRepository extends MongoRepository<Planet, String> {
  Optional<Planet> findByName(String title);
}