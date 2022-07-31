package com.strength.caliapi.services;

import com.strength.caliapi.entities.db.Exercise;
import com.strength.caliapi.entities.db.ExerciseDifficulty;
import com.strength.caliapi.entities.db.MuscleGroup;
import com.strength.caliapi.entities.repositories.ExerciseRepository;
import com.strength.caliapi.entities.repositories.MuscleGroupRepository;
import com.strength.caliapi.exceptions.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ExerciseServiceTests {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;
    @Autowired
    private ExerciseService exerciseService;

    private final Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    private final List<Exercise> exercises = Arrays.asList(
            new Exercise(
                    1,
                    timestamp,
                    timestamp,
                    "Push Up",
                    "Best chest exercise",
                    ExerciseDifficulty.valueOf("beginner"),
                    new HashSet<MuscleGroup>() {{
                        add(new MuscleGroup(2, timestamp, timestamp, "Chest", "Pectoralis major and minor"));
                        add(new MuscleGroup(3, timestamp, timestamp, "Triceps", "Triceps muscle"));
                    }}
            ),
            new Exercise(
                    4,
                    timestamp,
                    timestamp,
                    "Pull Up",
                    "Best back exercise",
                    ExerciseDifficulty.valueOf("intermediate"),
                    new HashSet<MuscleGroup>() {{
                        add(new MuscleGroup(5, timestamp, timestamp, "Lats", "Latissimus dorsi"));
                        add(new MuscleGroup(6, timestamp, timestamp, "Traps", "Lower and middle trapezoid muscles"));
                    }}
            )
    );

    @BeforeEach
    void setUp() {
        this.exerciseRepository.saveAll(this.exercises);
    }

    @AfterEach
    void tearDown() {
        this.exerciseRepository.deleteAll();
        this.muscleGroupRepository.deleteAll();
    }

    @Test
    void getAllExercises_succeeds_with_status_200() {
        ResponseEntity<List<Exercise>> getAllExercisesResponse = this.exerciseService.getAllExercises();
        Assertions.assertEquals(getAllExercisesResponse.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue(getAllExercisesResponse.hasBody());
        System.out.println(getAllExercisesResponse.getBody());
        System.out.println(LocalDateTime.now());
        Assertions.assertEquals(
                this.exercises.stream().map(Exercise::getName).collect(Collectors.toList()),
                Objects.requireNonNull(getAllExercisesResponse.getBody()).stream().map(Exercise::getName).collect(Collectors.toList())
        );
    }

    @Test
    void getExercise_succeeds_with_status_200() {
        ResponseEntity<Exercise> getExerciseResponse = this.exerciseService.getExercise(1);
        Assertions.assertEquals(getExerciseResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getExercise_fails_with_status_404() {
        Assertions.assertThrows(NotFoundException.class, () -> this.exerciseService.getExercise(323));
    }
}
