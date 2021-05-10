package com.school.api.repository;

import com.school.api.model.Room;
import com.school.api.model.Fee;
import com.school.api.model.FeeStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeeRepository extends JpaRepository<Fee,Long>{
    Optional<Fee> findByRoomAndStatus(Room room, FeeStatus status);
}
