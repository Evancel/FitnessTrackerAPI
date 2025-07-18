package fitnesstracker.repository;

import fitnesstracker.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByNameIgnoreCase(String name);

    Optional<Application> findByApikey(String apikey);
}
