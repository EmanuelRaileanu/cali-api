package com.strength.caliapi.entities.repositories;

import com.strength.caliapi.entities.db.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {
    Optional<MuscleGroup> findByName(String name);
}
