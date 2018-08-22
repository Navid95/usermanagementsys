package navid.usermanagementsys.repository;

import navid.usermanagementsys.domain.Gym;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GymRepository extends PagingAndSortingRepository<Gym , Integer> {
    Gym findByName(String name);
}
