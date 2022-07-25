package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;


import java.util.Collection;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    //Создание факультета
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    //Поиск факультета
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    //Редактирование факультета
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    //Удаление факультета
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    //Получение списка всех факультетов
    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> filterFacultyColor(String color) {
        return facultyRepository.findByColor(color);
    }
}
