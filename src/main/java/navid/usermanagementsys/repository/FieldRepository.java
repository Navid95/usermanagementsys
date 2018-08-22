package navid.usermanagementsys.repository;

import navid.usermanagementsys.domain.Field;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FieldRepository extends PagingAndSortingRepository<Field , Integer> {

    Field findByFieldName(String fieldName);
}
