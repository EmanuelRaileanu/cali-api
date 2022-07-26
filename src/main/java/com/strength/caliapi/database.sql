DROP TABLE IF EXISTS workouts_exercise_configurations;
DROP TABLE IF EXISTS workouts;
DROP TABLE IF EXISTS exercise_configurations;
DROP TABLE IF EXISTS exercises_muscle_groups;
DROP TABLE IF EXISTS muscle_groups;
DROP TABLE IF EXISTS exercises;

CREATE TABLE IF NOT EXISTS exercises
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(2048),
    difficulty  ENUM ('beginner', 'intermediate', 'advanced', 'insane') DEFAULT 'beginner',
    created_at  TIMESTAMP           NOT NULL                            DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP           NOT NULL                            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS muscle_groups
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(2048),
    created_at  TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS exercises_muscle_groups
(
    exercise_id     BIGINT NOT NULL,
    muscle_group_id BIGINT NOT NULL,
    CONSTRAINT fk_exercise FOREIGN KEY (exercise_id) REFERENCES exercises (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_muscle_group FOREIGN KEY (muscle_group_id) REFERENCES muscle_groups (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS exercise_configurations
(
    id                       BIGINT PRIMARY KEY AUTO_INCREMENT,
    number_of_repetitions    INTEGER            DEFAULT 0,
    number_of_sets           INTEGER            DEFAULT 0,
    hold_duration_in_seconds INTEGER            DEFAULT 0,
    exercise_id              BIGINT    NOT NULL,
    created_at               TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at               TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_exercise_cfg FOREIGN KEY (exercise_id) REFERENCES exercises (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS workouts
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS workouts_exercise_configurations
(
    workout_id                BIGINT NOT NULL,
    exercise_configuration_id BIGINT NOT NULL,
    CONSTRAINT fk_workout FOREIGN KEY (workout_id) REFERENCES workouts (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_exercise_configuration FOREIGN KEY (exercise_configuration_id) REFERENCES exercise_configurations (id) ON DELETE CASCADE ON UPDATE CASCADE
);