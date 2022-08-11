package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT count (distinct (name)) from Student", nativeQuery = true)
    int getStudentBySchool();

    @Query(value = "SELECT AVG(age) from Student", nativeQuery = true)
    int getStudentAge_AVG_BySchool();

    @Query(value = "Select * from student ORDER BY id DESC  LIMIT 5 ", nativeQuery = true)
    List<Student> lastFiveStudents();

    @Query(value = "SELECT * from student order by name", nativeQuery = true)
    List<Student> getAllStudentsByName();
}
