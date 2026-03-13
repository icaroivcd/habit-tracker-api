package com.example.ControlHabits.controller;

import com.example.ControlHabits.entity.Habit;
import com.example.ControlHabits.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @DeleteMapping("delet/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public List<Habit> getHabits() {
        return habitService.getAllHabits();
    }

    @GetMapping("/{id}")
    public Habit getHabitById(@PathVariable Long id) {
        return habitService.getHabitById(id);
    }

    @PostMapping("/create-habit")
    public Habit createHabits(@RequestBody Habit habit) {
        return habitService.createHabits(habit);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody Habit habit){
        habitService.updateHabit(id, habit);
        return new ResponseEntity<>(habit, HttpStatus.OK);
    }

}
