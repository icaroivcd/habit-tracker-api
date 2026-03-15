package com.example.ControlHabits.service;

import com.example.ControlHabits.dto.CreateHabitDTO;
import com.example.ControlHabits.dto.UpdateHabitDTO;
import com.example.ControlHabits.entity.Habit;
import com.example.ControlHabits.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Habit createHabits(CreateHabitDTO habitDTO) {
        Habit habit = habitDTO.toEntity();
        return habitRepository.save(habit);
    }
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();

    }

    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }

    public Habit getHabitById(Long id) {
        return habitRepository.findById(id).orElse(null);
    }

    public Habit updateHabit(Long id, UpdateHabitDTO updateHabitDTO) {

        Habit habit = habitRepository.findById(id).orElse(null);

        if (habit != null) {
            if (updateHabitDTO.getName() != null) {
                habit.setName(updateHabitDTO.getName());
            }
            if (updateHabitDTO.getDescription() != null) {
                habit.setDescription(updateHabitDTO.getDescription());
            }
            return habitRepository.save(habit);
        } else {
            return null;
        }
    }
}
