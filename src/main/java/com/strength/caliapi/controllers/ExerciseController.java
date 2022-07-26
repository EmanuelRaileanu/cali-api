package com.strength.caliapi.controllers;

import com.strength.caliapi.entities.db.Exercise;
import com.strength.caliapi.exceptions.NotFoundException;
import com.strength.caliapi.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getExercises() {
        return this.exerciseService.getAllExercises();
    }

    @GetMapping("{id}")
    ResponseEntity<Exercise> getExercise(@PathVariable("id") long id) throws NotFoundException {
        return this.exerciseService.getExercise(id);
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        return this.exerciseService.addExercise(exercise);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable("id") long id, @RequestBody Exercise exercise) {
        return this.exerciseService.updateExercise(id, exercise);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteExercise(@PathVariable("id") long id) throws NotFoundException {
        return this.exerciseService.deleteExercise(id);
    }
}
