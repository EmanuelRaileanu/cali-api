package com.strength.caliapi.entities.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "muscle_groups")
public class MuscleGroup extends BaseModel {
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToMany(mappedBy = "muscleGroups", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("muscleGroups")
    private Set<Exercise> exercises;

    public MuscleGroup(LocalDateTime createdAt, LocalDateTime updatedAt, String name, String description, Set<Exercise> exercises) {
        super(createdAt, updatedAt);
        this.name = name;
        this.description = description;
        this.exercises = exercises;
    }

    public MuscleGroup(String name, String description, Set<Exercise> exercises) {
        this.name = name;
        this.description = description;
        this.exercises = exercises;
    }

    public MuscleGroup() {
        super();
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

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}
