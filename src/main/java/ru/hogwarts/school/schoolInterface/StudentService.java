package ru.hogwarts.school.schoolInterface;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student creatStudent(Student student);

    Student findStudent(long id);

    Student editStudent(Student student);

    Student deleteStudent(long id);

    Collection<Student> getAllStudents();

    Collection<Student> filterStudentsAge(int age);
}
