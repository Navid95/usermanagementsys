package navid.usermanagementsys.repository;

import navid.usermanagementsys.domain.Gender;
import navid.usermanagementsys.domain.Saloon;
import navid.usermanagementsys.domain.TimeTable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.Nullable;

import java.time.DayOfWeek;
import java.time.OffsetTime;
import java.util.List;

public interface TimeTableRepository extends PagingAndSortingRepository<TimeTable , Integer> {

    List<TimeTable> findAllBySaloon_Id(Integer saloon_Id);
    List<TimeTable> findAllByField_FieldName(String fieldName);
    List<TimeTable> findAllByDayOfWeek(DayOfWeek dayOfWeek);

    @Nullable
    TimeTable findByStartTimeEqualsAndEndTimeEqualsAndDayOfWeekEqualsAndSaloon
            (OffsetTime startTime , OffsetTime endTime , DayOfWeek dayOfWeek , Saloon saloon);
    List<TimeTable> findAllByGender(Gender gender);

}
