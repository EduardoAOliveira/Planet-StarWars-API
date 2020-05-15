package planetas.starwars.starwars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import planetas.starwars.starwars.model.Planeta;
import java.util.Optional;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {
  Optional<Planeta> findByName(String title);
}