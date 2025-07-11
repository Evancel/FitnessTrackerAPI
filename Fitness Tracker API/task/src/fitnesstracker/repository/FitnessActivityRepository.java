package fitnesstracker.repository;

import fitnesstracker.model.entity.Application;
import fitnesstracker.model.entity.FitnessActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessActivityRepository extends JpaRepository<FitnessActivity, Long> {
    Page<FitnessActivity> findByApplication(Application application, Pageable pageable);
}
