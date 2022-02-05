package zimareva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zimareva.exception.AnketaNotFoundException;
import zimareva.model.Anketa;
import zimareva.model.Option;
import zimareva.model.Question;
import zimareva.repository.AnketaRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AnketaService {

    private final AnketaRepository anketaRepository;
    private final QuestionService questionService;

    @Autowired
    public AnketaService(AnketaRepository anketaRepository, QuestionService questionService) {
        this.anketaRepository = anketaRepository;
        this.questionService = questionService;
    }

//todo: Зачем методы возвращают анкету?
    public Anketa addAnketa (Anketa anketa){
        anketa.setCreatedAt(new Date());
        List<Question> questions = anketa.getQuestions();
        for (Question item: questions){
            item.setAnketa(anketa);
            List<Option> options = item.getOptions();
            for (Option option: options){
                option.setQuestion(item);
            }
        }


        //addQuestionToAnketa(anketa.getId(),anketa.getQuestions());
        return anketaRepository.save(anketa);
    }

    public List<Anketa> getAnketas (){
        return StreamSupport
                .stream(anketaRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public Anketa getAnketa (Long id){
        return anketaRepository.findById(id).orElseThrow(()->
                new AnketaNotFoundException(id));
    }

    //todo: загрузка всей анкеты для удаления
    public Anketa deleteAnketa (Long id){
        Anketa anketa = getAnketa(id);
        anketaRepository.delete(anketa);
        return anketa;
    }

    //todo: добавить метод редактирования

    //todo: прочитать про транзакции



   /* @Transactional
    public Anketa addQuestionToAnketa (Long anketaId, List <Question> question){
        Anketa anketa = getAnketa(anketaId);
        anketa.setQuestions(question);
        return anketa;
    }*/



    /*//todo: переделать сигнатуру метода
    @Transactional
    public Anketa addQuestionToAnketa (Long anketaId, Question question){
        Anketa anketa = getAnketa(anketaId);
        anketa.addQuestion(question);
        question.setAnketa(anketa);
        return anketa;
    }*/

    //todo: зачем транзакшинал? Связать вопрос с анкетой
    //
    @Transactional
    public Anketa addQuestion(Anketa anketa) {
        anketa.addQuestion(new Question());
        return anketa;
    }

    @Transactional
    public void removeQuestion(Anketa anketa, Integer questionIndex) {
        Question questionToRemove = anketa.getQuestions().get(questionIndex.intValue());
        anketa.removeQuestion(questionToRemove);
    }



    //todo: Long anketaId - удалить
   /* @Transactional
    public Anketa removeQuestionFromAnketa (Long anketaId, Long questionId){
        Anketa anketa = getAnketa(anketaId);
        Question question = questionService.getQuestion(questionId);
        //todo: вызывать removequestion?
        anketa.removeQuestion(question);
        return anketa;
    }*/




}
