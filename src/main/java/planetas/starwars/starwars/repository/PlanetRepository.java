package planetas.starwars.starwars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import planetas.starwars.starwars.domain.Planet;
import java.util.Optional;

public interface PlanetRepository extends MongoRepository<Planet, String> {
  Optional<Planet> findByName(String title);
}