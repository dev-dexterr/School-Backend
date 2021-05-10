package com.school.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.school.api.model.Student;

public interface StudentRepository extends JpaRepository<Student , Long> {
}
