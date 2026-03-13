package com.example.ControlHabits.service;

import com.example.ControlHabits.entity.Habit;
import com.example.ControlHabits.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    private HabitRepository habitRepository;

    @Autowired
    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Habit createHabits(Habit habit) {
        return habitRepository.save(habit);
    }
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();

    }

    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }

    public Habit getHabitById(Long id) {
        if(habitRepository.findById(id).isPresent()){
            return habitRepository.findById(id).orElse(null);
        } else {
            return null;
        }
    }

    public Habit updateHabit(Long id, Habit habit) {
        Habit existingHabit = getHabitById(id);
        if (habit.getName() != null) {
            existingHabit.setName(habit.getName());
        }
        if (habit.getDescription() != null) {
            existingHabit.setDescription(habit.getDescription());
        }
        return habitRepository.save(existingHabit);

    }
}
