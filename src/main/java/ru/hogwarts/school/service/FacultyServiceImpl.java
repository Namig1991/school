package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.schoolInterface.FacultyInterface;


import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;


@Service
public class FacultyServiceImpl implements FacultyInterface {

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override//Создание факультета
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Был вызван метод createFaculty");
        return facultyRepository.save(faculty);
    }

    @Override//Поиск факультета
    public Faculty findFaculty(long id) {
        logger.info("Был вызван метод findFaculty");
        return facultyRepository.findById(id).get();
    }

    @Override//Редактирование факультета
    public Faculty editFaculty(Faculty faculty) {
        logger.info("Был вызван метод editFaculty");
        return facultyRepository.save(faculty);
    }

    @Override//Удаление факультета
    public void deleteFaculty(long id) {
        logger.info("Был вызван метод deleteFaculty");
        facultyRepository.deleteById(id);
    }

    @Override//Получение списка всех факультетов
    public Collection<Faculty> getAllFaculty() {
        logger.info("Был вызван метод getAllFaculty");
        return facultyRepository.findAll();
    }

    @Override//Получение списка факультетов по цвету
    public Collection<Faculty> filterFacultyColor(String color) {
        logger.info("Был вызван метод filterFacultyColor");
        return facultyRepository.findByColor(color);
    }

    @Override
    public Collection<Faculty> findFacultyByNameOrColor(String name, String color) {
        logger.info("Был вызван метод findFacultyByNameOrColor");
        return facultyRepository.findFacultyByNameContainingIgnoreCaseOrColorContainingIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getStudentsByFaculty(Long facultyId) {
        logger.info("Был вызван метод getStudentsByFaculty");
        return findFaculty(facultyId).getStudents();
    }

    @Override
    public String getLongNameForFaculty() {
        logger.info("Был вызван метод getLongNameForFaculty");
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(s -> s.toCharArray().length))
                .get();
    }

    @Override
    public int getIntegerSum() {
        logger.debug("был вызван метод createFaculty");
        int sum = Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, Integer::sum);
        return sum;
    }
}
