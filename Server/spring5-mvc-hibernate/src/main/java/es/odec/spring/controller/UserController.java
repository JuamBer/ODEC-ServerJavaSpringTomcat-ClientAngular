package es.odec.spring.controller;
import es.odec.spring.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.JsonObject;

import es.odec.spring.service.UserService;
 
@Controller
public class UserController {
 
  @Autowired
  private UserService userService;
 
  
  //Cliente JSP
  @CrossOrigin
  @GetMapping("/")
  public String userForm(Locale locale, Model model) {
	  
	System.out.println(userService.list());
    model.addAttribute("users", userService.list());
    return "editUsers";
  }
  
  @ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }
  
  @CrossOrigin
  @PostMapping("/addUser")
  public String saveUser(@ModelAttribute("user") @Valid User user, 
              BindingResult result, Model model) {
 
    if (result.hasErrors()) {
      model.addAttribute("users", userService.list());
      return "editUsers";
    }
   
    userService.save(user);
    return "redirect:/";
  }
  
  
  //Cliente Angular
  @CrossOrigin
  @GetMapping("/usersNg")
  public ResponseEntity<?> getUsers(Locale locale, Model model) {   
    model.addAttribute("users", userService.list());
    
    for(User usuario: userService.list()) {
       System.out.println(usuario.getEmail());
    }

    List<JsonObject> entities = new ArrayList<JsonObject>();
    for (User n : userService.list()) {
            JsonObject entity = new JsonObject();
        entity.addProperty("id", n.getId());
        entity.addProperty("name", n.getName());
        entity.addProperty("email", n.getEmail());
        entities.add(entity);
    }
    
    return ResponseEntity.ok(entities);
  }
  
  @CrossOrigin
  @PostMapping("/addUserNg")
  public ResponseEntity<?> saveUserNg(String name, String email) {
    userService.save(new User(name, email));
    return ResponseEntity.ok(null);
  }
  
  @CrossOrigin
  @PutMapping("updateUserNg/{id}")
  public ResponseEntity<?> updateUserNg(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
	userService.updateUser(id,user.getName(),user.getEmail());
	return ResponseEntity.ok(null);
  }
  
  @CrossOrigin
  @DeleteMapping("/deleteUserNg/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id){
	 userService.deleteUser(id);
     return ResponseEntity.ok(null);
  }
}
