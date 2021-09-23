package nekrasova.svetlana.conference.restcontroller;

import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.entity.enums.Role;
import nekrasova.svetlana.conference.entityDto.UserDto;
import nekrasova.svetlana.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/admin")
public class RestAdminController {
    @Autowired
    UserService userService;

    /*
     * List of all users
     */
    @GetMapping("")
    public List<UserDto> allUsers() {
        return userService.allUsers();
    }

    /*
     * Get all role types
     */
    @GetMapping("/roles")
    public List<String> getRoles() {
        return Stream.of(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    /*
     * Create a new user under ADMIN, set role
     */
    @PostMapping("")
    public String createUser(@RequestBody UserDto newUserDto) {
        if (newUserDto != null) {
            if (newUserDto.getPassword().equals(newUserDto.getPasswordConfirm())) {
                if (!userService.ifExistsUserByName(newUserDto.getUserName())) {
                    userService.createUser(newUserDto);
                    return "new user successfully created";
                }
                return "User with such name already exists";
            }
            return "Password and password confirm not equals";

        }
        return "no user to save";
    }

    /*
     * Update existing user, need userDto with existing id
     */
    @PutMapping("/updateUser")
    public String updateUser(@RequestBody UserDto userDto) {
        if (userDto != null) {
            if (userDto.getPassword().equals(userDto.getPasswordConfirm())) {
                User userDB = userService.findUserById(userDto.getUserId());
                if (userDB != null) {
                    userService.updateUser(userDB, userDto);
                    return "user successfully updated";
                }
                return "User does not exist";
            }
            return "Password and password confirm not equals";
        }
        return "no user to save";
    }

    /*
     * Set for user by id role of SPEAKER
     */
    @PostMapping("/setSpeaker/{userId}")
    public String setSpeaker(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        if (user != null) {
            userService.setSpeaker(user);
            return "now user is speaker";
        }
        return "user not found";
    }


    /*
     * Delete user by Id
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        if (user != null) {
            userService.deleteUser(user);
            return "user was deleted";
        }
        return "user not found";
    }


}
