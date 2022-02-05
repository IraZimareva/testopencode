package zimareva.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zimareva.model.Anketa;

@Repository
public interface AnketaRepository  extends CrudRepository <Anketa,Long> {
}
