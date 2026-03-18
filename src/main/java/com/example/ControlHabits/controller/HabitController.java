package com.example.ControlHabits.controller;

import com.example.ControlHabits.dto.CreateHabitDTO;
import com.example.ControlHabits.dto.UpdateHabitDTO;
import com.example.ControlHabits.entity.Habit;
import com.example.ControlHabits.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Habit> getHabits() {
        return habitService.getAllHabits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long id) {
        Habit habit = habitService.getHabitById(id);
        return new ResponseEntity<>(habit, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Habit> createHabit(@Valid @RequestBody CreateHabitDTO habit) {
        Habit created = habitService.createHabit(habit);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @Valid @RequestBody UpdateHabitDTO habit) {
        Habit updatedHabit = habitService.updateHabit(id, habit);
        return ResponseEntity.ok(updatedHabit);
    }
}
