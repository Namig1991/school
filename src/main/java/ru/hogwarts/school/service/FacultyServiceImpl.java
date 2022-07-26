package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.schoolInterface.FacultyInterface;


import java.util.Collection;


@Service
public class FacultyServiceImpl implements FacultyInterface {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override//Создание факультета
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override//Поиск факультета
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    @Override//Редактирование факультета
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override//Удаление факультета
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    @Override//Получение списка всех факультетов
    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override//Получение списка факультетов по цвету
    public Collection<Faculty> filterFacultyColor(String color) {
        return facultyRepository.findByColor(color);
    }

    @Override
    public Faculty findByName(String name) {
        return facultyRepository.findFacultiesByNameIgnoreCase(name);
    }

    @Override
    public Faculty findByColor(String color) {
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

    @Override
    public Collection<Student> getStudentsByFaculty(Long facultyId){
        return findFaculty(facultyId).getStudents();
    }

}
