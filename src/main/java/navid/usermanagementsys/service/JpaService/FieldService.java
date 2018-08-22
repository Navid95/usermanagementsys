package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Field;

public interface FieldService extends CRUDService<Field> {

    Field findByFieldName(String fieldName);

}
