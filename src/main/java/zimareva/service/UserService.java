package zimareva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zimareva.exception.UserNotFoundException;
import zimareva.model.Answer;
import zimareva.model.User;
import zimareva.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AnswerService answerService;

    @Autowired
    public UserService(UserRepository userRepository, AnswerService answerService) {
        this.userRepository = userRepository;
        this.answerService = answerService;
    }

    public User addUser (User user){
        return userRepository.save(user);
    }

    public List<User> getUsers (){
        return StreamSupport
                .stream(userRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public User getUser (Long id){
        return userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException(id));
    }

    public User deleteUser (Long id){
        User user = getUser(id);
        userRepository.delete(user);
        return user;
    }

    @Transactional
    public User editUser (Long id, User user){
        User userToEdit = getUser(id);
        userToEdit.setFirstName(user.getFirstName());
        userToEdit.setSecondName(user.getSecondName());
        //userToEdit.setAnswers(user.getAnswers());
//Надо ли?
        return userToEdit;
    }

    @Transactional
    public User addAnswerToUser (Long userId, Long answerId){
        User user = getUser(userId);
        Answer answer = answerService.getAnswer(answerId);
        //user.addAnswer(answer);
//answer.setUser(user);
        return user;
    }

    @Transactional
    public User removeAnswerFromUser (Long userId, Long answerId){
        User user = getUser(userId);
        Answer answer = answerService.getAnswer(answerId);
        //user.removeAnswer(answer);
        return user;
    }


}
