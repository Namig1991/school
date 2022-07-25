package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;


import java.util.Collection;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

     //Создание студента
    public Student creatStudent(Student student) {
        return studentRepository.save(student);
    }

    //Поиск студента
    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

     //Редактирование студента
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

     //Удаление студента
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

     //Получение списка всех студентов
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> filterStudentsAge(int age) {
        return studentRepository.findByAge(age);
    }
}
