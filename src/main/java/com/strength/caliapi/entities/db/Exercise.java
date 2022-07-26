package com.strength.caliapi.entities.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exercises")
@JsonIgnoreProperties("exerciseConfigurations")
public class Exercise extends BaseModel {
    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('beginner', 'intermediate', 'advanced', 'insane') DEFAULT 'beginner'")
    private ExerciseDifficulty difficulty;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "exercises_muscle_groups",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_group_id"))
    @JsonIgnoreProperties("exercises")
    private Set<MuscleGroup> muscleGroups = new HashSet<>();

    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseConfiguration> exerciseConfigurations;

    public Exercise(LocalDateTime createdAt, LocalDateTime updatedAt, String name, String description, ExerciseDifficulty difficulty, Set<MuscleGroup> muscleGroups, Set<ExerciseConfiguration> exerciseConfigurations) {
        super(createdAt, updatedAt);
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.muscleGroups = muscleGroups;
        this.exerciseConfigurations = exerciseConfigurations;
    }

    public Exercise(LocalDateTime createdAt, LocalDateTime updatedAt, String name, ExerciseDifficulty difficulty, Set<MuscleGroup> muscleGroups) {
        super(createdAt, updatedAt);
        this.name = name;
        this.difficulty = difficulty;
        this.muscleGroups = muscleGroups;
    }

    public Exercise(String name, ExerciseDifficulty difficulty, Set<MuscleGroup> muscleGroups) {
        this.name = name;
        this.difficulty = difficulty;
        this.muscleGroups = muscleGroups;
    }

    public Exercise() {

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

    public ExerciseDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(ExerciseDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(Set<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public Set<ExerciseConfiguration> getExerciseConfigurations() {
        return exerciseConfigurations;
    }

    public void setExerciseConfigurations(Set<ExerciseConfiguration> exerciseConfigurations) {
        this.exerciseConfigurations = exerciseConfigurations;
    }
}
