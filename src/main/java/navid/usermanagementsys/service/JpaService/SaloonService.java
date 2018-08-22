package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Gym;
import navid.usermanagementsys.domain.Saloon;
import java.util.List;

public interface SaloonService extends CRUDService<Saloon> {

    List<Saloon> findAllByGym_Id(Integer gym_id);
    Saloon findBySaloonName(String s);
    Saloon findBySaloonNameAndGym(String s , Gym gym);
}
