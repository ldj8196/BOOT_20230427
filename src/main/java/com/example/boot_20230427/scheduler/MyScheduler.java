package com.example.boot_20230427.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.boot_20230427.entity.Board1;
import com.example.boot_20230427.repository.Board1Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyScheduler {
    
    final Board1Repository board1Repository;

    // 초 분 시간 일 월 요일
    // */5 * * * * * 5초 간격으로 동작
    @Scheduled(cron = "*/5 * * * * *")
    public void printDate() {
        log.info("{}", new Date().toString());

        List<Board1> list = board1Repository.findAll();
        log.info("{}", list.toString());
    }
}
