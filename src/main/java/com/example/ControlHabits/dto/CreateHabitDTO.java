package com.example.ControlHabits.dto;

import com.example.ControlHabits.entity.Habit;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateHabitDTO {

    @NotBlank
    @Size(max = 120)
    private String name;

    @Nullable
    @Size(max = 255)
    private String description;

    public Habit toEntity() {
        Habit habit = new Habit();
        habit.setName(this.name.trim());
        habit.setDescription(this.description);
        return habit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }
}
