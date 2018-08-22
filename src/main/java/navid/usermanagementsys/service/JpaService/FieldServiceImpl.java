package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Field;
import navid.usermanagementsys.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdatajpa")
public class FieldServiceImpl implements FieldService {

    private FieldRepository fieldRepository;

    @Autowired
    public void setFieldRepository(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Field findByFieldName(String fieldName) {
        return fieldRepository.findByFieldName(fieldName);
    }

    @Override
    public List<?> listAll() {
        return (List<?>) fieldRepository.findAll();
    }

    @Override
    public Field getById(Integer id) {
        return fieldRepository.findById(id).get();
    }

    @Override
    public Field saveOrUpdate(Field domainObject) {
        return fieldRepository.save(domainObject);
    }

    @Override
    public Page<Field> listAllByPage(Pageable pageable) {
        return fieldRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        fieldRepository.deleteById(id);
    }
}
