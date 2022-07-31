package com.strength.caliapi.entities.db;

import javax.persistence.*;
import java.sql.Timestamp;
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
