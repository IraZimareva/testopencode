package zimareva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zimareva.exception.AnswerNotFoundException;
import zimareva.model.Answer;
import zimareva.repository.AnswerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
//private final UserService userService; //нужен ли?

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer addAnswer (Answer answer){
        return answerRepository.save(answer);
    }

    public List <Answer> getAnswers (){
        return StreamSupport
                .stream(answerRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public Answer getAnswer(Long id){
        return answerRepository.findById(id).orElseThrow(()->
                new AnswerNotFoundException(id));
    }

    public Answer deleteAnswer (Long id){
        Answer answer = getAnswer(id);
        answerRepository.delete(answer);
        return answer;
    }

    @Transactional
    public Answer editAnswer (Long id, Answer answer){
        Answer answerToEdit = getAnswer(id);
        answerToEdit.setTextOfAnswer(answer.getTextOfAnswer());
        answerToEdit.setUser(answer.getUser());
//Надо ли?
        return  answerToEdit;
    }

}
