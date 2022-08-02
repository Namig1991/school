package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import ru.hogwarts.school.schoolInterface.StudentInterface;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentInterface studentInterface;

    public StudentController(StudentInterface studentInterface) {
        this.studentInterface = studentInterface;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentInterface.findStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentInterface.getAllStudents());
    }

    @GetMapping("/info/{age}")
    public ResponseEntity<Collection<Student>> getFilterAgeList(int age) {
        return ResponseEntity.ok(studentInterface.filterStudentsAge(age));
    }

    @GetMapping("/age/info")
    public ResponseEntity<Collection<Student>> getFilterAgeListFromParam(@RequestParam(required = false) Integer min,
                                                                         @RequestParam(required = false) Integer max) {
        if (min != null && max != null) {
            return ResponseEntity.ok(studentInterface.filterListStudentAge(min, max));
        }
        return null;
    }

    @GetMapping("/get/faculty/student")
    public ResponseEntity<Faculty> getFacultyByStudent(@RequestParam long facultyId){
        return ResponseEntity.ok(studentInterface.getFacultyByStudent(facultyId));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentInterface.creatStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student studentEdit = studentInterface.editStudent(student);
        if (studentEdit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(studentEdit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        studentInterface.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/students-by-school")
    public int getStudentsBySchool(){
        return studentInterface.getStudentsBySchool();
    }

    @GetMapping("/students-age-avg-school")
    public int getStudentAge_AVG_BySchool(){
        return studentInterface.getStudentAge_AVG_BySchool();
    }

    @GetMapping("/last-five-students")
    public List<Student> getLastFiveStudents(){
        return studentInterface.getLastFiveStudents();
    }
}
