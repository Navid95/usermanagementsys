package navid.usermanagementsys.repository;

import navid.usermanagementsys.domain.Gym;
import navid.usermanagementsys.domain.Saloon;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SaloonRepository extends PagingAndSortingRepository<Saloon , Integer> {

    Saloon findBySaloonName(String s);
    Saloon findBySaloonNameAndGym(String s , Gym gym);
    List<Saloon> findAllByGym_Id(Integer gym_id);
}
