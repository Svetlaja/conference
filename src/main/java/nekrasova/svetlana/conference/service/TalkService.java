package nekrasova.svetlana.conference.service;

import nekrasova.svetlana.conference.entity.Talk;
import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TalkService {
    @Autowired
    private TalkRepository talkRepository;

    public List<Talk> findBySpeakers(User speaker){
        return talkRepository.findBySpeakers(speaker);
    }

    public void saveTalk(Talk talk){

    }

}
