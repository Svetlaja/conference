package nekrasova.svetlana.conference.service;

import nekrasova.svetlana.conference.entity.Talk;
import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.entityDto.TalkDto;
import nekrasova.svetlana.conference.entityDto.TalkFullDto;
import nekrasova.svetlana.conference.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TalkService {
    @Autowired
    TalkRepository talkRepository;
    @Autowired
    UserService userService;

    public Talk findTalkById(long id) {
        return talkRepository.findById(id).orElse(null);
    }

    public void deleteTalk(Talk talk) {
        for (User user : talk.getSpeakers()) {
            user.removeTalk(talk);
        }
        talkRepository.delete(talk);
    }

    public List<TalkFullDto> findTalkDtoBySpeaker(User speaker) {
        List<TalkFullDto> talkDtos = new ArrayList<>();
        for (Talk talk : talkRepository.findBySpeakers(speaker)) {
            talkDtos.add(TalkFullDto.fromTalk(talk));
        }
        return talkDtos;
    }

    public boolean checkUserAccessToTalk(Talk talk) {
        if (talk.getSpeakers().contains(userService.getAuthorizedUser())) {
            return true;
        }
        return false;
    }

    public boolean isUserSpeakerOfTalk(Talk talk, User user) {
        if (talk.getSpeakers().contains(user)) {
            return true;
        }
        return false;
    }

    public void saveTalk(Talk talk) {
        talkRepository.save(talk);
    }

    public void saveTalkDto(TalkDto talkDto) {
        User currentUser = userService.getAuthorizedUser();
        Talk talk = new Talk();
        talk.setTalkName(talkDto.getTalkName());
        talk.addSpeaker(currentUser);
        talkRepository.save(talk);
    }

}
