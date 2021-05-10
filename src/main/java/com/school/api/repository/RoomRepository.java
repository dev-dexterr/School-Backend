package com.school.api.repository;

import com.school.api.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long>{
    Optional<Room> findByName(String name);
}
