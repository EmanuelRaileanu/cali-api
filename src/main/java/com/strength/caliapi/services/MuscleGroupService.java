package com.strength.caliapi.services;

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
public class MuscleGroupService {
    private final MuscleGroupRepository muscleGroupRepository;

    @Autowired
    public MuscleGroupService(MuscleGroupRepository muscleGroupRepository) {
        this.muscleGroupRepository = muscleGroupRepository;
    }

    public ResponseEntity<List<MuscleGroup>> getAllMuscleGroups() {
        return ResponseEntity.ok(this.muscleGroupRepository.findAll());
    }

    public ResponseEntity<MuscleGroup> getMuscleGroup(long id) {
        Optional<MuscleGroup> muscleGroup = this.muscleGroupRepository.findById(id);
        if (!muscleGroup.isPresent()) {
            throw new NotFoundException("Muscle group with id " + id + " doesn't exist");
        }
        return ResponseEntity.ok(muscleGroup.get());
    }

    public ResponseEntity<MuscleGroup> addMuscleGroup(MuscleGroup muscleGroup) {
        Optional<MuscleGroup> dbMuscleGroup = this.muscleGroupRepository.findByName(muscleGroup.getName());
        if (dbMuscleGroup.isPresent()) {
            throw new ConflictException("A muscle group with the name " + muscleGroup.getName() + " already exists");
        }
        return new ResponseEntity<>(this.muscleGroupRepository.save(muscleGroup), HttpStatus.CREATED);
    }

    public ResponseEntity<MuscleGroup> updateMuscleGroup(long id, MuscleGroup muscleGroup) {
        Optional<MuscleGroup> foundExercise = this.muscleGroupRepository.findById(id);
        if (!foundExercise.isPresent()) {
            throw new NotFoundException("Exercise with id " + id + " doesn't exist");
        }
        MuscleGroup dbMuscleGroup = foundExercise.get();
        if (muscleGroup.getName() != null) {
            dbMuscleGroup.setName(muscleGroup.getName());
        }
        if (muscleGroup.getDescription() != null) {
            dbMuscleGroup.setDescription(muscleGroup.getDescription());
        }
        return ResponseEntity.ok(this.muscleGroupRepository.save(dbMuscleGroup));
    }

    public ResponseEntity<Object> deleteMuscleGroup(long id) {
        Optional<MuscleGroup> muscleGroup = this.muscleGroupRepository.findById(id);
        if (!muscleGroup.isPresent()) {
            throw new NotFoundException("Muscle group with id " + id + " doesn't exist");
        }
        this.muscleGroupRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
