package com.nangman.redis4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DefaultDataPopulator implements ApplicationRunner {

    @Autowired
    MeetingRepository meetingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Meeting meeting = new Meeting();
        meeting.setTitle("New meeting");
        meeting.setStartAt(new Date());
        meetingRepository.save(meeting);

        meetingRepository.findAll().forEach(m -> {
            System.out.println("============");
            System.out.println(m.getTitle() + " " + m.getStartAt());
        });
    }
}
