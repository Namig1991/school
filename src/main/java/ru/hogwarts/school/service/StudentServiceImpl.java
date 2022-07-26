package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.schoolInterface.StudentInterface;


import java.util.Collection;


@Service
public class StudentServiceImpl implements StudentInterface {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override//Создание студента
    public Student creatStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override//Поиск студента
    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    @Override//Редактирование студента
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override//Удаление студента
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override//Получение списка всех студентов
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override//Получение списка студентов по возрасту
    public Collection<Student> filterStudentsAge(int age) {
        return studentRepository.findByAge(age);
    }
}
