package nekrasova.svetlana.conference.restcontroller;

import nekrasova.svetlana.conference.entity.Talk;
import nekrasova.svetlana.conference.repository.TalkRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestTalkController {
    private final TalkRepository talkRepository;

    RestTalkController(TalkRepository talkRepository){
        this.talkRepository = talkRepository;
    }

    @GetMapping("/talks")
    List<Talk> allTalks(){
        return talkRepository.findAll();
    }
}
