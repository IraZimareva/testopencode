package zimareva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import zimareva.model.Answer;
import zimareva.model.User;
import zimareva.repository.AnswerRepository;
import zimareva.repository.UserRepository;


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
	}

}
