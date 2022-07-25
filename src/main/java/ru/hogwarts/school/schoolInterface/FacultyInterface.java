package ru.hogwarts.school.schoolInterface;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyInterface {
    //Создание факультета
    Faculty createFaculty(Faculty faculty);

    //Поиск факультета
    Faculty findFaculty(long id);

    //Редактирование факультета
    Faculty editFaculty(Faculty faculty);

    //Удаление факультета
    void deleteFaculty(long id);

    //Получение списка всех факультетов
    Collection<Faculty> getAllFaculty();

    //Получение списка факультетов по цвету
    Collection<Faculty> filterFacultyColor(String color);
}
