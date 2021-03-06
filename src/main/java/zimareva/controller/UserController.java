package zimareva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zimareva.model.User;
import zimareva.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers (Model model){
        List <User> users = userService.getUsers();
        model.addAttribute("users",users);
        return "users/index";
    }

    @GetMapping(value = "{id}")
    public String getUser (@PathVariable (value = "id") Long userId, Model model){
        User user = userService.getUser(userId);
        model.addAttribute("user",user);
        return "users/show";
    }

    @GetMapping(value = "/new")
    public String newUser (Model model){
        model.addAttribute("user",new User());
        return "users/new";
    }

    @PostMapping
    public String createUser (@ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "users/new";
        userService.addUser(user);
        return "redirect:/users";
    }

/*    @PostMapping
    public ResponseEntity <User> createUser (@Valid @RequestBody User user){
        User user1 = userService.addUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }*/

    @GetMapping(value="/{id}/edit")
    public String getUpdateUser (Model model, @PathVariable (value = "id") Long userId) {
        model.addAttribute("user",userService.getUser(userId));
        return "users/edit";
    }

    @PutMapping(value = "{id}")
    public String updateUser (@ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult,
                              @PathVariable (value = "id") Long userId){
        if(bindingResult.hasErrors())
            return "users/edit";
        userService.editUser(userId,user);
        return "redirect:/users";
    }

//    @PutMapping(value = "{id}")
//    public ResponseEntity <User> updateUser (@PathVariable (value = "id") Long userId,
//                                             @Valid @RequestBody User userDetails){
//        User user = userService.editUser(userId,userDetails);
//        return new ResponseEntity<>(user,HttpStatus.OK);
//    }

    @DeleteMapping(value = "{id}")
    public String deleteUser (@PathVariable (value = "id") Long userId){
        userService.deleteUser(userId);
        return "redirect:/users";
    }

    /*@DeleteMapping(value = "{id}")
    public ResponseEntity <User> deleteUser (@PathVariable (value = "id") Long userId){
        User user = userService.deleteUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }*/

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
