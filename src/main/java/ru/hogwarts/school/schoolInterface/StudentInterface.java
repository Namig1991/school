package ru.hogwarts.school.schoolInterface;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentInterface {
    //Создание студента
    Student creatStudent(Student student);

    //Поиск студента
    Student findStudent(long id);

    //Редактирование студента
    Student editStudent(Student student);

    //Удаление студента
    void deleteStudent(long id);

    //Получение списка всех студентов
    Collection<Student> getAllStudents();

    //Получение списка студентов по возрасту
    Collection<Student> filterStudentsAge(int age);
}