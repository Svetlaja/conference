package nekrasova.svetlana.conference.restcontroller;

import nekrasova.svetlana.conference.entity.Room;
import nekrasova.svetlana.conference.entity.Schedule;
import nekrasova.svetlana.conference.entity.Talk;
import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.entityDto.*;
import nekrasova.svetlana.conference.service.RoomService;
import nekrasova.svetlana.conference.service.TalkService;
import nekrasova.svetlana.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talk")
public class RestSpeakerController {
    @Autowired
    TalkService talkService;
    @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;

    /*
     * Getting all talks for authorized user
     */
    @GetMapping("")
    public List<TalkFullDto> getUserTalks() {
        User currentUser = userService.getAuthorizedUser();
        return talkService.findTalkDtoBySpeaker(currentUser);
    }

    /*
     * Delete talk by id
     */
    @DeleteMapping("/{talkId}")
    public String deleteTalk(@PathVariable Long talkId) {
        Talk talk = talkService.findTalkById(talkId);
        if (talk != null) {
            if (talkService.checkUserAccessToTalk(talk)) {
                talkService.deleteTalk(talk);
                return "Talk by id = " + talkId + " successfully deleted";
            }
            return "No access to talk by id " + talkId + ", you are not in it's speakers";
        }
        return "Talk by id = " + talkId + " not found";
    }

    /*
     * Create new talk (by name)
     */
    @PostMapping("")
    public String createTalk(@RequestBody TalkDto talkDto) {
        if (talkDto != null && !talkDto.getTalkName().equals("")) {
            talkService.saveTalkDto(talkDto);
            return "talk successfully created";
        }
        return "no talk to save";

    }

    /*
     * Update talkName for existing talk, need talkDto with existing id
     */
    @PutMapping("")
    public String updateTalk(@RequestBody TalkDto talkDto) {
        Talk talk = talkService.findTalkById(talkDto.getTalkId());
        if (talk != null) {
            if (talkService.checkUserAccessToTalk(talk)) {
                if (!talkDto.getTalkName().trim().equals("")) {
                    System.out.println("check talkName" + talkDto.getTalkName());
                    talk.setTalkName(talkDto.getTalkName());
                    talkService.saveTalk(talk);
                    return "talk successfully updated";
                }
                return "empty talk name is not allowed";
            }
            return "No access to talk by id " + talkDto.getTalkId() + ", you are not in it's speakers";
        }
        return "no such talk";
    }

    /*
     * Update schedule for existing talk
     */
    @PostMapping("/addSchedule")
    public String addSchedule(@RequestParam Long talkId, @RequestBody ScheduleDto scheduleDto) {
        Talk talk = talkService.findTalkById(talkId);
        if (talk != null) {
            if (talkService.checkUserAccessToTalk(talk)) {
                if (scheduleDto != null) {
                    Room room = roomService.findByRoomName(scheduleDto.getScheduleRoomName());
                    if (room != null && roomService.roomAvailability(room, scheduleDto.getDateStart(), scheduleDto.getDateEnd())) {
                        talk.setSchedule(new Schedule(scheduleDto.getDateStart(), scheduleDto.getDateEnd(), roomService.findByRoomName(scheduleDto.getScheduleRoomName()), talk));
                        talkService.saveTalk(talk);
                        return "schedule was added to talk";
                    }
                    return "room is not available";
                }
                return "no schedule to add";
            }
            return "No access to talk by id " + talkId + ", you are not in it's speakers";
        }
        return "no such talk";
    }

    /*
     * Add new speaker for existing talk
     */
    @PostMapping("/addSpeaker")
    public String addSpeaker(@RequestParam Long talkId, @RequestBody SpeakerDto speakerDto) {
        Talk talk = talkService.findTalkById(talkId);
        User newSpeaker = (User) userService.loadUserByUsername(speakerDto.getSpeakerName());
        if (talk != null) {
            if (talkService.checkUserAccessToTalk(talk)) {
                if (newSpeaker != null) {
                    talk.addSpeaker(newSpeaker);
                    talkService.saveTalk(talk);
                    return "speaker added to talk";
                }
                return "no speaker to add";
            }
            return "No access to talk by id " + talkId + ", you are not in it's speakers";

        }
        return "no such talk";
    }

    /*
     * Remove speaker from existing talk
     */
    @PostMapping("/removeSpeaker")
    public String removeSpeaker(@RequestParam Long talkId, @RequestBody SpeakerDto speakerDto) {
        Talk talk = talkService.findTalkById(talkId);
        User speaker = (User) userService.loadUserByUsername(speakerDto.getSpeakerName());
        if (talk != null) {
            if (talkService.checkUserAccessToTalk(talk)) {
                if (speaker != null && talkService.isUserSpeakerOfTalk(talk, speaker)) {
                    talk.removeSpeaker(speaker);
                    talkService.saveTalk(talk);
                    return "speaker deleted from talk";
                }
                return "no speaker to remove";
            }
            return "No access to talk by id " + talkId + ", you are not in it's speakers";

        }
        return "no such talk";
    }
}
