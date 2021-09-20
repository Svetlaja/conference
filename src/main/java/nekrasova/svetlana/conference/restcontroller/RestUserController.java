package nekrasova.svetlana.conference.restcontroller;

import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.repository.UserRepository;
import nekrasova.svetlana.conference.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestUserController {
    private final UserService userService;

    RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> allUsers() {
        return userService.allUsers();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}
