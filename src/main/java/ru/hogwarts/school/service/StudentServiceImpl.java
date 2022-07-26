package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.schoolInterface.StudentInterface;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentInterface {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override//Создание студента
    public Student creatStudent(Student student) {
        logger.info("Был вызван метод creatStudent");
        return studentRepository.save(student);
    }

    @Override//Поиск студента
    public Student findStudent(long id) {
        logger.info("Был вызван метод findStudent");
        return studentRepository.findById(id).get();
    }

    @Override//Редактирование студента
    public Student editStudent(Student student) {
        logger.info("Был вызван метод editStudent");
        return studentRepository.save(student);
    }

    @Override//Удаление студента
    public void deleteStudent(long id) {
        logger.info("Был вызван метод deleteStudent");
        studentRepository.deleteById(id);
    }

    @Override//Получение списка всех студентов
    public Collection<Student> getAllStudents() {
        logger.info("Был вызван метод getAllStudents");
        return studentRepository.findAll();
    }

    @Override//Получение списка студентов по возрасту
    public Collection<Student> filterStudentsAge(int age) {
        logger.info("Был вызван метод filterStudentsAge");
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> filterListStudentAge(int min, int max) {
        logger.info("Был вызван метод filterListStudentAge");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFacultyByStudent(Long facultyId) {
        logger.info("Был вызван метод getFacultyByStudent");
        return findStudent(facultyId).getFaculty();
    }

    @Override
    public int getStudentsBySchool() {
        logger.info("Был вызван метод getStudentsBySchool");
        return studentRepository.getStudentBySchool();
    }

    @Override
    public int getStudentAge_AVG_BySchool() {
        logger.info("Был вызван метод getStudentAge_AVG_BySchool");
        return studentRepository.getStudentAge_AVG_BySchool();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        logger.info("Был вызван метод getLastFiveStudents");
        return studentRepository.lastFiveStudents();
    }

    @Override
    public List<String> findStudentBySortName(String sortName) {
        logger.info("Был вызван метод findStudentBySortName");
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .filter(name -> name.contains(sortName))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageAgeByAllStudents() {
        logger.info("Был вызван метод getAverageAgeByAllStudents");
        return studentRepository.findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .average()
                .getAsDouble();
    }

    private List<String> getAllStudentsByNameParallel() {
        return studentRepository.getAllStudentsByName().stream().map(Student::getName).toList();
    }

    @Override
    public void parallelThread() {

        Thread thread2 = new Thread(() -> {
            System.out.println(getAllStudentsByNameParallel().get(3));
            System.out.println(getAllStudentsByNameParallel().get(4));
            System.out.println(getAllStudentsByNameParallel().get(5));
        });

        Thread thread3 = new Thread(() -> {
            System.out.println(getAllStudentsByNameParallel().get(6));
            System.out.println(getAllStudentsByNameParallel().get(7));
            System.out.println(getAllStudentsByNameParallel().get(8));
        });

        thread2.start();
        thread3.start();

        System.out.println(getAllStudentsByNameParallel().get(0));
        System.out.println(getAllStudentsByNameParallel().get(1));
        System.out.println(getAllStudentsByNameParallel().get(2));
    }

    private final Object flag = new Object();

    private void printNameForSynchronizedThread(String name) {
        System.out.println(name);
    }

    @Override
    public void synchronizedPrintStudentsName() {
        printNameForSynchronizedThread(getAllStudentsByNameParallel().get(0));
        printNameForSynchronizedThread(getAllStudentsByNameParallel().get(1));
        printNameForSynchronizedThread(getAllStudentsByNameParallel().get(2));

        new Thread(() -> {
            synchronized (flag) {
                printNameForSynchronizedThread(getAllStudentsByNameParallel().get(3));
                printNameForSynchronizedThread(getAllStudentsByNameParallel().get(4));
                printNameForSynchronizedThread(getAllStudentsByNameParallel().get(5));
            }
        }
        ).start();

        new Thread(() -> {
            synchronized (flag) {
                printNameForSynchronizedThread(getAllStudentsByNameParallel().get(6));
                printNameForSynchronizedThread(getAllStudentsByNameParallel().get(7));
                printNameForSynchronizedThread(getAllStudentsByNameParallel().get(8));
            }
        }
        ).start();
    }
}
