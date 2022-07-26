package com.strength.caliapi.entities.db;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class Workout extends BaseModel {
    private String name;

    @ManyToMany
    @JoinTable(
            name = "workouts_exercise_configurations",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_configuration_id")
    )
    private Set<ExerciseConfiguration> exerciseConfigurations;

    public Workout (LocalDateTime createdAt, LocalDateTime updatedAt, String name, Set<ExerciseConfiguration> exerciseConfigurations) {
        super(createdAt, updatedAt);
        this.name = name;
        this.exerciseConfigurations = exerciseConfigurations;
    }

    public Workout () {

    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Set<ExerciseConfiguration> getExerciseConfigurations () {
        return exerciseConfigurations;
    }

    public void setExerciseConfigurations (Set<ExerciseConfiguration> exerciseConfigurations) {
        this.exerciseConfigurations = exerciseConfigurations;
    }
}
