package navid.usermanagementsys.service.JpaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CRUDService<T> {

    List<?> listAll();

    Page<T> listAllByPage(Pageable pageable);

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
