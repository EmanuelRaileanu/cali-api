package com.strength.caliapi.services;

import com.strength.caliapi.entities.db.Exercise;
import com.strength.caliapi.entities.db.MuscleGroup;
import com.strength.caliapi.entities.repositories.ExerciseRepository;
import com.strength.caliapi.entities.repositories.MuscleGroupRepository;
import com.strength.caliapi.exceptions.ConflictException;
import com.strength.caliapi.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final MuscleGroupRepository muscleGroupRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository, MuscleGroupRepository muscleGroupRepository) {
        this.exerciseRepository = exerciseRepository;
        this.muscleGroupRepository = muscleGroupRepository;
    }

    public ResponseEntity<List<Exercise>> getAllExercises() {
        return ResponseEntity.ok(this.exerciseRepository.findAll());
    }

    public ResponseEntity<Exercise> getExercise(long id) {
        Optional<Exercise> exercise = this.exerciseRepository.findById(id);
        if (!exercise.isPresent()) {
            throw new NotFoundException("Exercise with id " + id + " doesn't exist");
        }
        return ResponseEntity.ok(exercise.get());
    }

    public ResponseEntity<Exercise> addExercise(Exercise exercise) {
        Optional<Exercise> dbExercise = this.exerciseRepository.findByName(exercise.getName());
        if (dbExercise.isPresent()) {
            throw new ConflictException("An exercise with the name " + exercise.getName() + " already exists");
        }
        for (MuscleGroup muscleGroup : exercise.getMuscleGroups()) {
            Optional<MuscleGroup> dbMuscleGroup = this.muscleGroupRepository.findById(muscleGroup.getId());
            if (!dbMuscleGroup.isPresent()) {
                throw new NotFoundException("Muscle group with id " + muscleGroup.getId() + " doesn't exist");
            }
        }
        return new ResponseEntity<>(this.exerciseRepository.save(exercise), HttpStatus.CREATED);
    }

    public ResponseEntity<Exercise> updateExercise(long id, Exercise exercise) {
        Optional<Exercise> foundExercise = this.exerciseRepository.findById(id);
        if (!foundExercise.isPresent()) {
            throw new NotFoundException("Exercise with id " + id + " doesn't exist");
        }
        Exercise dbExercise = foundExercise.get();
        if (exercise.getName() != null) {
            dbExercise.setName(exercise.getName());
        }
        if (exercise.getDifficulty() != null) {
            dbExercise.setDifficulty(exercise.getDifficulty());
        }
        if (exercise.getMuscleGroups() != null && !exercise.getMuscleGroups().isEmpty()) {
            dbExercise.setMuscleGroups(exercise.getMuscleGroups());
        }
        if (exercise.getDescription() != null) {
            dbExercise.setDescription(exercise.getDescription());
        }
        return ResponseEntity.ok(this.exerciseRepository.save(dbExercise));
    }

    public ResponseEntity<Object> deleteExercise(long id) throws NotFoundException {
        Optional<Exercise> exercise = this.exerciseRepository.findById(id);
        if (!exercise.isPresent()) {
            throw new NotFoundException("Exercise with id " + id + " doesn't exist");
        }
        this.exerciseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
