package com.example.ControlHabits;

import com.example.ControlHabits.entity.Habit;
import com.example.ControlHabits.repository.HabitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HabitControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HabitRepository habitRepository;

    @BeforeEach
    void setup() {
        habitRepository.deleteAll();
    }

    @Test
    void createHabitShouldReturn201WithLocation() throws Exception {
        String payload = """
                {
                  "name": "Leitura diaria",
                  "description": "30 minutos"
                }
                """;

        mockMvc.perform(post("/habits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Leitura diaria"))
                .andExpect(jsonPath("$.description").value("30 minutos"));
    }

    @Test
    void createHabitWithBlankNameShouldReturn400() throws Exception {
        String payload = """
                {
                  "name": "  ",
                  "description": "desc"
                }
                """;

        mockMvc.perform(post("/habits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    @Test
    void getHabitByIdNotFoundShouldReturn404() throws Exception {
        mockMvc.perform(get("/habits/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("HABIT_NOT_FOUND"));
    }

    @Test
    void patchWithoutFieldsShouldReturn400() throws Exception {
        Habit habit = new Habit();
        habit.setName("Exercicio");
        habit.setDescription("Academia");
        Habit saved = habitRepository.save(habit);

        String payload = "{}";

        mockMvc.perform(patch("/habits/{id}", saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    @Test
    void deleteHabitShouldReturn204WhenFound() throws Exception {
        Habit habit = new Habit();
        habit.setName("Agua");
        habit.setDescription("2 litros");
        Habit saved = habitRepository.save(habit);

        mockMvc.perform(delete("/habits/{id}", saved.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteHabitShouldReturn404WhenNotFound() throws Exception {
        mockMvc.perform(delete("/habits/12345"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("HABIT_NOT_FOUND"));
    }
}
