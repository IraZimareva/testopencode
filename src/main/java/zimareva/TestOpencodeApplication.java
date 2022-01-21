package zimareva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import zimareva.model.Answer;
import zimareva.model.User;
import zimareva.repository.AnswerRepository;
import zimareva.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TestOpencodeApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
		    SpringApplication.run(TestOpencodeApplication.class, args);
        UserRepository userRepository =
                configurableApplicationContext.getBean(UserRepository.class);
        User myUser = new User("Ira","Zimareva");
        Answer answer1 = new Answer("Yes",myUser);
        Answer answer2 = new Answer("No", myUser);
        //List <Answer> answers = Arrays.asList(answer1,answer2);
        //myUser.setAnswers(answers);
        userRepository.save(myUser);
        AnswerRepository answerRepository = configurableApplicationContext.getBean(AnswerRepository.class);
        answerRepository.save(answer1);
        answerRepository.save(answer2);
	}

}
