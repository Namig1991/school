package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
	@LocalServerPort
	private int port;
	@Autowired
	private StudentController studentController;

	@MockBean
	private StudentRepository studentRepository;

	//@Autowired (Если я мокую репозиторий то тест на удаление проходит, а тест на создание валится((( )
	//private StudentRepository studentRepository; (А если я автовайрю репозиторий, то наоборот)


	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private TestRestTemplate restTemplate;

	private static final ObjectMapper om = new ObjectMapper();

	@Test
	void contextLoads() throws Exception {
		Assertions.assertThat(studentController).isNotNull();
	}
	@Test
	public void getStudentInfoTest() throws Exception {
		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class))
				.isNotNull();
	}
	@Test
	public void searchAgeStudentTest() throws Exception {
		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/filtr/1,2", String.class))
				.isNotNull();
	}
	@Test
	public void getFacultyStudentTest() throws Exception {
		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty/5", String.class))
				.isNotNull();
	}
	@Test
	public void createStudentTest() throws Exception {
		Student student = new Student();
		student.setId(1L);
		student.setName("Tonny Mor");
		student.setAge(10);
		when(studentRepository.save(any(Student.class))).thenReturn(student);
		Assertions
				.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
				.isNotNull();
	}

	@Test
	public void deleteStudentTest() throws Exception {
		doNothing().when(studentRepository).deleteById(1L);
		HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
		ResponseEntity<String> response = restTemplate.exchange("/student/1", HttpMethod.DELETE, entity, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(studentRepository, times(1)).deleteById(1L);
	}

	@Test
	public void updateStudentTest() throws Exception {

		Student updateStudent = new Student();
		when(studentRepository.save(any(Student.class))).thenReturn(updateStudent);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(updateStudent), headers);

		ResponseEntity<String> response = restTemplate.exchange("/student", HttpMethod.PUT, entity, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(om.writeValueAsString(updateStudent), response.getBody(), false);

		verify(studentRepository, times(1)).save(any(Student.class));
	}

}