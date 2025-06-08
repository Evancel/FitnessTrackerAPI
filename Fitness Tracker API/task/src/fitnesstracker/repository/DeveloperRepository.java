package fitnesstracker.repository;

import fitnesstracker.entity.Developer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long> {
    Optional<Developer> findByEmail(String email);
}
