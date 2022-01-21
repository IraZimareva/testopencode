package zimareva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zimareva.model.User;
import zimareva.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity <User> createUser (@Valid @RequestBody User user){
        User user1 = userService.addUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity <List<User>> getUsers (){
        List <User> users = userService.getUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity <User> getUser (@PathVariable (value = "id") Long userId){
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity <User> deleteUser (@PathVariable (value = "id") Long userId){
        User user = userService.deleteUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity <User> updateUser (@PathVariable (value = "id") Long userId,
                                             @Valid @RequestBody User userDetails){
        User user = userService.editUser(userId,userDetails);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping(value = "{userId}/answers/{answerId}/create")
    public ResponseEntity <User> createAnswerToUser (@PathVariable (value = "userId") Long userId,
                                                     @PathVariable (value = "answerId") Long answerId){
        User user = userService.addAnswerToUser(userId,answerId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping(value = "{userId}/answers/{answerId}/delete")
    public ResponseEntity <User> deleteAnswerFromUser (@PathVariable (value = "userId") Long userId,
                                                     @PathVariable (value = "answerId") Long answerId){
        User user = userService.removeAnswerFromUser(userId,answerId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
