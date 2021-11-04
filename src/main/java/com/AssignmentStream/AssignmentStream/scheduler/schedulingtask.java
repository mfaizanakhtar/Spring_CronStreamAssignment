package com.AssignmentStream.AssignmentStream.scheduler;

import com.AssignmentStream.AssignmentStream.service.BookIssuedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class schedulingtask {
    private static final Logger log = LoggerFactory.getLogger(schedulingtask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private BookIssuedService bookIssuedService;

    @Scheduled(cron="0 0 0 * * *")
//    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime(){
        log.info("The time is now {}",dateFormat.format(new Date()));
        bookIssuedService.MarkAsLate();
    }
}
