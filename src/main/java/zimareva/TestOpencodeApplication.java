package zimareva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import zimareva.model.*;
import zimareva.model.enums.QuestionEnum;
import zimareva.repository.*;

import java.util.Date;


@SpringBootApplication
public class TestOpencodeApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
		    SpringApplication.run(TestOpencodeApplication.class, args);
        UserRepository userRepository =
                configurableApplicationContext.getBean(UserRepository.class);
        User user1 = new User("Ira","Zimareva");
        User user2 = new User ("Petya","Petrov");
        userRepository.save(user1);
        userRepository.save(user2);

        AnswerRepository answerRepository =
                configurableApplicationContext.getBean(AnswerRepository.class);
        Answer answer1 = new Answer("Yes",user1);
        Answer answer2 = new Answer("No", user1);
        Answer answer3 = new Answer("Red", null);
        answerRepository.save(answer1);
        answerRepository.save(answer2);
        answerRepository.save(answer3);

        AnketaRepository anketaRepository =
                configurableApplicationContext.getBean(AnketaRepository.class);
            //Anketa anketa1 = new Anketa("Form to autorisation", new Date());
            Anketa anketa1 = new Anketa("Form to autorisation");
            anketaRepository.save(anketa1);

        QuestionRepository questionRepository =
                configurableApplicationContext.getBean(QuestionRepository.class);
            Question question1 = new Question("What is your gender?"
                    , QuestionEnum.ONEOPTION
                    ,anketa1
                    ,null);
            questionRepository.save(question1);
            Question question2 = new Question("What is your favourite mounth?"
                    , QuestionEnum.FEWOPTIONS
                    ,anketa1
                    ,null);
            questionRepository.save(question2);

        OptionRepository optionRepository =
                configurableApplicationContext.getBean(OptionRepository.class);
        Option option1 = new Option("Male",question1);
        Option option2 = new Option("Female",question1);
        optionRepository.save(option1);
        optionRepository.save(option2);
	}

}
