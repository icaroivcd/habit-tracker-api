package com.example.ControlHabits.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateHabitDTO {

    private String name;
    private String description;

    public UpdateHabitDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
