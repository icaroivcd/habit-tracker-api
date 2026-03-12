package com.example.ControlHabits.controller;

import com.example.ControlHabits.entity.Habit;
import com.example.ControlHabits.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @GetMapping("")
    public List<Habit> getHabits() {
        return habitService.getAllHabits();
    }

    @PostMapping("/create-habit")
    public Habit createHabits(@RequestBody Habit habit) {
        return habitService.createHabits(habit);
    }



}
