package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Gym;
import navid.usermanagementsys.domain.Saloon;
import navid.usermanagementsys.domain.TimeTable;
import navid.usermanagementsys.repository.SaloonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class SaloonServiceImpl implements SaloonService {

    private SaloonRepository saloonRepository;

    private TimeTableService timeTableService;

    @Autowired
    public void setTimeTableService(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @Autowired
    public void setSaloonRepository(SaloonRepository saloonRepository) {
        this.saloonRepository = saloonRepository;
    }

    @Override
    public List<Saloon> findAllByGym_Id(Integer gym_id) {
        return saloonRepository.findAllByGym_Id(gym_id);
    }

    @Override
    public Page<Saloon> listAllByPage(Pageable pageable) {
        return saloonRepository.findAll(pageable);
    }

    @Override
    public Saloon findBySaloonName(String s) {
        return saloonRepository.findBySaloonName(s);
    }

    @Override
    public Saloon findBySaloonNameAndGym(String s, Gym gym) {
        return saloonRepository.findBySaloonNameAndGym(s , gym);
    }

    @Override
    public List<?> listAll() {
        return (List<?>) saloonRepository.findAll();
    }

    @Override
    public Saloon getById(Integer id) {
        return saloonRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Saloon saveOrUpdate(Saloon domainObject) {

        List<TimeTable> timeTables = new ArrayList<>();

        if (domainObject.getTimeTables() != null)
        domainObject.getTimeTables().forEach(timeTable -> {
            TimeTable timeTable1 = timeTableService.
                    findByStartTimeEqualsAndEndTimeEqualsAndDayOfWeekEqualsAndSaloon
                            (timeTable.getStartTime() , timeTable.getEndTime() , timeTable.getDayOfWeek() , timeTable.getSaloon());
            if (timeTable1 == null){
                timeTable1 = new TimeTable();
                System.err.println("TimeTable not persisted yet !!!");
                timeTable1.setSaloon(domainObject);
                timeTable1.setField(timeTable.getField());
                timeTable1.setDayOfWeek(timeTable.getDayOfWeek());
                timeTable1.setStartTime(timeTable.getStartTime());
                timeTable1.setEndTime(timeTable.getEndTime());
            }
            System.err.println("Time table is persisted before;");
            timeTables.add(timeTable1);
        });
        domainObject.setTimeTables(timeTables);
        return saloonRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        saloonRepository.deleteById(id);
    }
}
