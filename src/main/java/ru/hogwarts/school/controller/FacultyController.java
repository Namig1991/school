package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.schoolInterface.FacultyInterface;


import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyInterface facultyInterface;

    public FacultyController(FacultyInterface facultyInterface) {
        this.facultyInterface = facultyInterface;
    }

    @GetMapping("{id}")//test+
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable long id) {
        Faculty faculty = facultyInterface.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/all")//test+
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyInterface.getAllFaculty());
    }

    @GetMapping("/filter")//test+
    public ResponseEntity<Collection<Faculty>> getFilterListFacultyColor(String color) {
        return ResponseEntity.ok(facultyInterface.filterFacultyColor(color));
    }

    @GetMapping("/findFacultyByNameOrColor")//test+
    public ResponseEntity<Collection<Faculty>> findFacultyByNameOrColor(@RequestParam(required = false) String name,
                                                                        @RequestParam(required = false) String color){
        if(name != null && !name.isBlank()){
            return ResponseEntity.ok(facultyInterface.findFacultyByNameOrColor(name,color));
        }
        if(color != null && !color.isBlank()){
            return ResponseEntity.ok(facultyInterface.findFacultyByNameOrColor(name,color));
        }
        return null;
    }


    @GetMapping("/get/students")//test+
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@RequestParam long facultyId){
        return ResponseEntity.ok(facultyInterface.getStudentsByFaculty(facultyId));
    }

    @PostMapping//test+
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyInterface.createFaculty(faculty);
    }

    @PutMapping//test+
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty facultyEdit = facultyInterface.editFaculty(faculty);
        if (facultyEdit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(facultyInterface.editFaculty(faculty));
    }

    @DeleteMapping("{id}")//test+
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyInterface.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
