package fitnesstracker.repository;

import fitnesstracker.entity.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    boolean existsByName(String name);
    Optional<Application> findByApikey(String apikey);
}
