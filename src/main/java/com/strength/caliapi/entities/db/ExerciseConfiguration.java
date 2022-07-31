package com.strength.caliapi.entities.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "exercise_configurations")
@JsonIgnoreProperties("workout")
public class ExerciseConfiguration extends BaseModel {
    @ColumnDefault("0")
    private int numberOfRepetitions = 0;
    @ColumnDefault("0")
    private int numberOfSets = 0;
    @ColumnDefault("0")
    private int holdDurationInSeconds = 0;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @ManyToMany(mappedBy = "exerciseConfigurations")
    private Set<Workout> workout;

    public ExerciseConfiguration(Timestamp createdAt, Timestamp updatedAt, int numberOfRepetitions, int numberOfSets, int holdDurationInSeconds, Exercise exercise, Set<Workout> workout) {
        super(createdAt, updatedAt);
        this.numberOfRepetitions = numberOfRepetitions;
        this.numberOfSets = numberOfSets;
        this.holdDurationInSeconds = holdDurationInSeconds;
        this.exercise = exercise;
        this.workout = workout;
    }

    public ExerciseConfiguration() {

    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public void setNumberOfRepetitions(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public void setNumberOfSets(int numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public int getHoldDurationInSeconds() {
        return holdDurationInSeconds;
    }

    public void setHoldDurationInSeconds(int holdDurationInSeconds) {
        this.holdDurationInSeconds = holdDurationInSeconds;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Set<Workout> getWorkout() {
        return workout;
    }

    public void setWorkout(Set<Workout> workout) {
        this.workout = workout;
    }
}
