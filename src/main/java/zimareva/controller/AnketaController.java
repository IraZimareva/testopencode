package zimareva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zimareva.model.Anketa;
import zimareva.model.Question;
import zimareva.service.AnketaService;

import javax.validation.Valid;

@Controller
@RequestMapping("/anketas")
public class AnketaController {

    private final AnketaService anketaService;

    @Autowired
    public AnketaController(AnketaService anketaService) {
        this.anketaService = anketaService;
    }

    //todo: лучше переименовать new
    @GetMapping(value = "/new")
    public String newAnketa (Model model){
        model.addAttribute("anketa",new Anketa());
        return "anketas/new";
    }

    @PostMapping
    public String createAnketa (@ModelAttribute("anketa") @Valid Anketa anketa,
                              BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            //return "anketas/new";
            model.addAttribute("errorMessage", "The submitted data has errors.");
        //anketaService.addAnketa(anketa);
        model.addAttribute("anketa", anketaService.addAnketa(anketa));
        model.addAttribute("successMessage", "Anketa saved successfully!");


        //System.out.println(anketa.toString());
        return "anketas/new";
        //return "redirect:/users";
    }

    /*@RequestMapping(value = "/new", params = "addQuestion" )
    public String addQuestion (Anketa anketa, BindingResult bindingResult){
        anketa.addQuestion(new Question());
        return "redirect:/anketas/new";
    }*/


    @PostMapping("/addQuestion")
    public String addQuestion(Anketa anketa){
        anketaService.addQuestion(anketa);
        //return "users/show";
        return "anketas/new :: questions"; // returning the updated section
    }

    @PostMapping("/removeQuestion")
    public String removeQuestion(Anketa anketa, @RequestParam("removeQuestion") Integer questionIndex){
        anketaService.removeQuestion(anketa,questionIndex);
        return "anketas/new :: questions"; // returning the updated section
        //return "index :: contacts"; // returning the updated section
    }




/*    @GetMapping
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
    }*/



    /*@GetMapping(value="/{id}/edit")
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

    @DeleteMapping(value = "{id}")
    public String deleteUser (@PathVariable (value = "id") Long userId){
        userService.deleteUser(userId);
        return "redirect:/users";
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
    }*/

}
