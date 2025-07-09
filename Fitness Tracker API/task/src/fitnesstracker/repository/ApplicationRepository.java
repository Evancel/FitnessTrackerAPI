package fitnesstracker.repository;

import fitnesstracker.model.entity.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    boolean existsByNameIgnoreCase(String name);

    Optional<Application> findByApikey(String apikey);
}
