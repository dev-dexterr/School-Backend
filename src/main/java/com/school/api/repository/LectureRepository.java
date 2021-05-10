package com.school.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.api.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long>{

}
