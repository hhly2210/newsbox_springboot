package com.tintucspringboot.tintuc.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.respository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    @Autowired
    private PostRepository postRepository;

    public long getPostCountInDay(Date date) {
        LocalDateTime startOfDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).with(LocalTime.MAX);

        return postRepository.countByPulishDateBetween(
                Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public long getPostCountInMonth(Date month) {
        LocalDateTime startOfMonth = month.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().withDayOfMonth(1)
                .atStartOfDay();
        LocalDateTime endOfMonth = LocalDateTime.ofInstant(month.toInstant(), ZoneId.systemDefault())
                .with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);

        return postRepository.countByPulishDateBetween(
                Date.from(startOfMonth.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(endOfMonth.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public long getPostCountInYear(Date year) {
        LocalDateTime startOfYear = year.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().withDayOfYear(1)
                .atStartOfDay();
        LocalDateTime endOfYear = LocalDateTime.ofInstant(year.toInstant(), ZoneId.systemDefault())
                .with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);

        return postRepository.countByPulishDateBetween(
                Date.from(startOfYear.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(endOfYear.atZone(ZoneId.systemDefault()).toInstant()));
    }
}
