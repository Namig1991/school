package ru.hogwarts.school.schoolInterface;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    Faculty deleteFaculty(long id);

    Collection<Faculty> getAllFaculty();

    Collection<Faculty> filterFacultyColor(String color);
}
