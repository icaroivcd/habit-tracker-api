package com.example.ControlHabits.repository;

import com.example.ControlHabits.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HabitRepository extends JpaRepository<Habit, Long> {
    
}
