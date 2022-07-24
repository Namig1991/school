package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.schoolInterface.FacultyService;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final HashMap<Long, Faculty> facultyHashMap = new HashMap<>();
    private long counterFaculty = 0;

    @Override //Создание факультета
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++counterFaculty);
        facultyHashMap.put(counterFaculty, faculty);
        return faculty;
    }

    @Override //Поиск факультета
    public Faculty findFaculty(long id) {
        if(id > 0 && facultyHashMap.containsKey(id)) {
            return facultyHashMap.get(id);
        }
        return null;
    }

    @Override //Редактирование факультета
    public Faculty editFaculty(Faculty faculty) {
        if(faculty != null) {
            facultyHashMap.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    @Override //Удаление факультета
    public Faculty deleteFaculty(long id) {
        if (id > 0 && facultyHashMap.containsKey(id)) {
            return facultyHashMap.remove(id);
        }
        return null;
    }

    @Override //Получение списка всех факультетов
    public Collection<Faculty> getAllFaculty(){
        return facultyHashMap.values();
    }

    @Override
    public Collection<Faculty> filterFacultyColor(String color){
        return facultyHashMap.values().stream().filter(c -> c.getColor().equals(color)).
                collect(Collectors.toList());
    }
}
