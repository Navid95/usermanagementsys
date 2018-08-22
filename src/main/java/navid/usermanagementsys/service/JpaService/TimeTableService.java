package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Gender;
import navid.usermanagementsys.domain.Saloon;
import navid.usermanagementsys.domain.TimeTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.DayOfWeek;
import java.time.OffsetTime;
import java.util.List;

public interface TimeTableService extends CRUDService<TimeTable> {

    List<TimeTable> findAllBySaloon_Id(Integer saloon_Id);
    List<TimeTable> findAllByField_FieldName(String fieldName);
    List<TimeTable> findAllByDayOfWeek(DayOfWeek dayOfWeek);
    TimeTable findByStartTimeEqualsAndEndTimeEqualsAndDayOfWeekEqualsAndSaloon
            (OffsetTime startTime , OffsetTime endTime , DayOfWeek dayOfWeek , Saloon saloon);
    List<TimeTable> findAllByGender(Gender gender);
}
