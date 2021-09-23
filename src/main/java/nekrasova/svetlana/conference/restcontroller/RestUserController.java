package nekrasova.svetlana.conference.restcontroller;


import nekrasova.svetlana.conference.entityDto.SpeakerDto;
import nekrasova.svetlana.conference.entityDto.UserDto;
import nekrasova.svetlana.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {
    @Autowired
    UserService userService;

    @GetMapping("/talk/speakers")
    public List<SpeakerDto> getSpeakers() {
        return userService.getSpeakers();
    }

    @PostMapping("/registration")
    public String registerUser(@RequestBody UserDto newUserDto) {
        if (newUserDto != null) {
            if (newUserDto.getPassword().equals(newUserDto.getPasswordConfirm())) {
                if (!userService.ifExistsUserByName(newUserDto.getUserName())) {
                    userService.registerUser(newUserDto);
                    return "registration is successful";
                }
                return "User with such name already exists";
            }
            return "Password and password confirm not equals";

        }
        return "no user to save";
    }
}
