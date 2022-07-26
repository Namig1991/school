package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.schoolInterface.FacultyInterface;


import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyInterface facultyInterface;

    public FacultyController(FacultyInterface facultyInterface) {
        this.facultyInterface = facultyInterface;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable long id) {
        Faculty faculty = facultyInterface.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyInterface.getAllFaculty());
    }

    @GetMapping("/info/color")
    public ResponseEntity<Collection<Faculty>> getFilterListFacultyColor(String color) {
        return ResponseEntity.ok(facultyInterface.filterFacultyColor(color));
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyInterface.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty facultyEdit = facultyInterface.editFaculty(faculty);
        if (facultyEdit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(facultyInterface.editFaculty(faculty));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyInterface.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
