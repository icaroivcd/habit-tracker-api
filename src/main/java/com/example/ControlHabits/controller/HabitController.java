package com.example.ControlHabits.controller;

import com.example.ControlHabits.dto.CreateHabitDTO;
import com.example.ControlHabits.dto.UpdateHabitDTO;
import com.example.ControlHabits.entity.Habit;
import com.example.ControlHabits.service.HabitService;
import jakarta.validation.Valid;
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public List<Habit> getHabits() {
        return habitService.getAllHabits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long id) {
        Habit habitId = habitService.getHabitById(id);
        if (habitId == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(habitId, HttpStatus.OK);
    }

    @PostMapping("/create-habit")
    public ResponseEntity<Habit> createHabits(@Valid @RequestBody CreateHabitDTO habit) {
        Habit created = habitService.createHabits(habit);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody UpdateHabitDTO habit) {
        Habit updatedHabit = habitService.updateHabit(id, habit);

        if(updatedHabit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedHabit);
    }
}
