package zimareva.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zimareva.model.Answer;

@Repository
public interface AnswerRepository  extends CrudRepository <Answer, Long> {
}
