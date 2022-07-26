package com.strength.caliapi.controllers;

import com.strength.caliapi.entities.db.MuscleGroup;
import com.strength.caliapi.exceptions.NotFoundException;
import com.strength.caliapi.services.MuscleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/muscle-groups")
public class MuscleGroupController {
    private final MuscleGroupService muscleGroupService;

    @Autowired
    public MuscleGroupController(MuscleGroupService muscleGroupService) {
        this.muscleGroupService = muscleGroupService;
    }

    @GetMapping
    public ResponseEntity<List<MuscleGroup>> getMuscleGroups() {
        return this.muscleGroupService.getAllMuscleGroups();
    }

    @GetMapping("{id}")
    ResponseEntity<MuscleGroup> getMuscleGroup(@PathVariable("id") long id) throws NotFoundException {
        return this.muscleGroupService.getMuscleGroup(id);
    }

    @PostMapping
    public ResponseEntity<MuscleGroup> createMuscleGroup(@RequestBody MuscleGroup exercise) {
        return this.muscleGroupService.addMuscleGroup(exercise);
    }

    @PatchMapping("{id}")
    public ResponseEntity<MuscleGroup> updateMuscleGroup(@PathVariable("id") long id, @RequestBody MuscleGroup exercise) {
        return this.muscleGroupService.updateMuscleGroup(id, exercise);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteMuscleGroup(@PathVariable("id") long id) throws NotFoundException {
        return this.muscleGroupService.deleteMuscleGroup(id);
    }
}
