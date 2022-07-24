package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.schoolInterface.StudentService;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final HashMap<Long, Student> studentHashMap = new HashMap<>();
    private long counterStudent = 0;

    @Override //Создание студента
    public Student creatStudent(Student student) {
        student.setId(++counterStudent);
        studentHashMap.put(counterStudent, student);
        return student;
    }

    @Override //Поиск студента
    public Student findStudent(long id) {
        if (id > 0 && studentHashMap.containsKey(id)) {
            return studentHashMap.get(id);
        }
        return null;
    }

    @Override //Редактирование студента
    public Student editStudent(Student student) {
        if (student != null) {
            studentHashMap.put(student.getId(), student);
            return student;
        }
        return null;
    }

    @Override //Удаление студента
    public Student deleteStudent(long id) {
        if (id > 0 && studentHashMap.containsKey(id)) {
            return studentHashMap.remove(id);
        }
        return null;
    }

    @Override //Получение списка всех студентов
    public Collection<Student> getAllStudents() {
        return studentHashMap.values();
    }

    @Override
    public Collection<Student> filterStudentsAge(int age){
        return studentHashMap.values().stream().filter(x ->x.getAge() == age).
                collect(Collectors.toList());
    }
}
