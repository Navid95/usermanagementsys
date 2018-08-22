package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Gym;

public interface GymService extends CRUDService<Gym> {
    Gym findByName(String name);
}
