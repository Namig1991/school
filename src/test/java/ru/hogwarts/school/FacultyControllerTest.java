package ru.hogwarts.school;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private FacultyController facultyController;

    private final String testName = "testFaculty";
    private final String testColor = "testColor";

    private final Long testId = 1L;

    @Test
    public void mocMvcFacultyTest() throws Exception {
        final String name = "BlackGryffindor";
        final String color = "Black";
        final Long id = 1L;

        Faculty faculty = new Faculty(id,name,color);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("Id", 1L);
        facultyObject.put("name", "BlackGryffindor");
        facultyObject.put("color", "Black");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        when(facultyRepository.findByColor(eq(faculty.getColor()))).thenReturn(List.of(faculty));
        doNothing().when(facultyRepository).deleteById(1L);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("BlackGryffindor"))
                .andExpect(jsonPath("$.color").value("Black"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("BlackGryffindor"))
                .andExpect(jsonPath("$.color").value("Black"));


        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculty/filter?color=" + color)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("BlackGryffindor"))
                .andExpect(jsonPath("$[0].color").value("Black"));

        mockMvc.perform(delete("/faculty/1"))
                .andExpect(status().isOk());
        verify(facultyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void mocMvcGetAllFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Black Dog");
        faculty.setColor("White");
        faculty.setId(1L);

        Faculty faculty2 = new Faculty();
        faculty2.setName("White Dog");
        faculty2.setColor("Black");
        faculty2.setId(2L);

        Collection<Faculty> forTest = new ArrayList<>(List.of(faculty, faculty2));

        when(facultyRepository.findAll()).thenReturn(List.of(faculty, faculty2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .equals(forTest);
    }

    @Test
    public void mocMvcGetStudentsOfFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Pink Sky");
        faculty.setColor("Pink");
        faculty.setId(1L);

        Student student = new Student();
        student.setName("Stalin");
        student.setAge(18);
        student.setFaculty(faculty);

        Student student2 = new Student();
        student2.setName("Lenin");
        student2.setAge(19);
        student.setFaculty(faculty);

        Collection<Student> forTest = new ArrayList<>(List.of(student, student2));

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/get/students?facultyId=" + faculty.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .equals(forTest);

    }

    @Test
    public void mocMvcPutFacultyTest() throws Exception {

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", testName);
        facultyObject.put("color", testColor);

        Faculty faculty = new Faculty();
        faculty.setName(testName);
        faculty.setColor(testColor);
        faculty.setId(1L);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(testName))
                .andExpect(jsonPath("$.color").value(testColor));
    }

    @Test//Не понимаю что с моим URL не так(( Пытался разные варинаты прописать, но тест падает((((
    public void mocMvcFindFacultyByNameOrColorTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName(testName);
        faculty.setColor(testColor);
        faculty.setId(1L);

        Faculty faculty2 = new Faculty();
        faculty2.setName(testName + "newTest");
        faculty2.setColor(testColor + "newTest");
        faculty2.setId(2L);

        Collection<Faculty> forTest = new ArrayList<>(List.of(faculty, faculty2));

        when(facultyRepository.findFacultyByNameContainingIgnoreCaseOrColorContainingIgnoreCase(any(String.class), any(String.class)))
                .thenReturn(List.of(faculty, faculty2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findFacultyByNameOrColor" + any(String.class))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .equals(forTest);

    }

}
