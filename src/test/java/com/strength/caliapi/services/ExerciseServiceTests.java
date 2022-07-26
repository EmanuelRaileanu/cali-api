package com.strength.caliapi.services;

import com.strength.caliapi.entities.db.Exercise;
import com.strength.caliapi.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class ExerciseServiceTests {
    // TODO: Use Mockito to mock the database connection and add mock entries

    @Autowired
    private ExerciseService exerciseService;

    @Test
    void getAllExercises_succeeds_with_status_200() {
        ResponseEntity<List<Exercise>> getAllExercisesResponse = this.exerciseService.getAllExercises();
        Assertions.assertEquals(getAllExercisesResponse.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue(getAllExercisesResponse.hasBody());
    }

    @Test
    void getExercise_fails_with_status_404() {
        Assertions.assertThrows(NotFoundException.class, () -> this.exerciseService.getExercise(323));
    }
}
