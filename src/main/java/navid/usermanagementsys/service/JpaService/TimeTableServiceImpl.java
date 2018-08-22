package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Gender;
import navid.usermanagementsys.domain.Saloon;
import navid.usermanagementsys.domain.TimeTable;
import navid.usermanagementsys.repository.TimeTableRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.OffsetTime;
import java.util.List;

@Service
@Profile("springdatajpa")
public class TimeTableServiceImpl implements TimeTableService {

    private TimeTableRepository timeTableRepository;

    private SaloonService saloonService;

    private Logger logger = LogManager.getLogger(TimeTableServiceImpl.class);

    @Autowired
    public void setSaloonService(SaloonService saloonService) {
        this.saloonService = saloonService;
    }

    @Autowired
    public void setTimeTableRepository(TimeTableRepository timeTableRepository) {
        this.timeTableRepository = timeTableRepository;
    }

    @Override
    public List<TimeTable> findAllBySaloon_Id(Integer saloon_Id) {
        return timeTableRepository.findAllBySaloon_Id(saloon_Id);
    }

    @Override
    public List<TimeTable> findAllByField_FieldName(String fieldName) {
        return timeTableRepository.findAllByField_FieldName(fieldName);
    }

    @Override
    public List<TimeTable> findAllByDayOfWeek(DayOfWeek dayOfWeek) {
        return timeTableRepository.findAllByDayOfWeek(dayOfWeek);
    }

    @Override
    public Page<TimeTable> listAllByPage(Pageable pageable) {
        return timeTableRepository.findAll(pageable);
    }

    @Override
    public TimeTable findByStartTimeEqualsAndEndTimeEqualsAndDayOfWeekEqualsAndSaloon(OffsetTime startTime , OffsetTime endTime , DayOfWeek dayOfWeek , Saloon saloon) {
        return timeTableRepository.findByStartTimeEqualsAndEndTimeEqualsAndDayOfWeekEqualsAndSaloon(startTime , endTime , dayOfWeek , saloon);
    }

    @Override
    public List<TimeTable> findAllByGender(Gender gender) {
        return timeTableRepository.findAllByGender(gender);
    }

    @Override
    public List<?> listAll() {
        return (List<?>) timeTableRepository.findAll();
    }

    @Override
    public TimeTable getById(Integer id) {
        if (id != null)
            return timeTableRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public TimeTable saveOrUpdate(TimeTable domainObject) {

        if (domainObject.getId() != null){
            logger.info("Updating time_table with Id : " + domainObject.getId());
        }
        else {
            logger.info("Saving new time_table");
        }
        return timeTableRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        timeTableRepository.deleteById(id);
    }


}
