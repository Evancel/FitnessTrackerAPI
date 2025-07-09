package fitnesstracker.repository;

import fitnesstracker.model.entity.FitnessActivity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessActivityRepository extends CrudRepository<FitnessActivity, Long>,
        PagingAndSortingRepository<FitnessActivity, Long> {
    Iterable<FitnessActivity> findAll(Sort sort);
}
