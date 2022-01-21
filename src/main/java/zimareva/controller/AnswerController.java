package zimareva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zimareva.model.Answer;
import zimareva.service.AnswerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity <Answer> createAnswer (@Valid @RequestBody Answer answer){
        Answer answer1 = answerService.addAnswer(answer);
        return new ResponseEntity<>(answer1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity <List<Answer>> getAnswers (){
        List <Answer> answers = answerService.getAnswers();
        return new ResponseEntity<>(answers,HttpStatus.OK);
//        return answerService.getAnswers();
    }

    @GetMapping (value = "{id}")
    public ResponseEntity <Answer> getAnswer (@PathVariable (value = "id") Long answerId){
        Answer answer = answerService.getAnswer(answerId);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping (value = "{id}")
    public ResponseEntity <Answer> deleteAnswer (@PathVariable (value = "id") Long answerId){
        Answer answer = answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PutMapping (value = "{id}")
    public ResponseEntity <Answer> updateAnswer (@PathVariable (value = "id") Long answerId,
                                                 @Valid @RequestBody Answer answerDetails){
        Answer editedAnswer = answerService.editAnswer(answerId,answerDetails);
        return new ResponseEntity<>(editedAnswer, HttpStatus.OK);
    }
}
