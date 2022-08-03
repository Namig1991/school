SELECT student.name, student.age, faculty.nsme
FROM student
INNER JOIN faculty ON student.id = faculty.id;

SELECT student.name, student.age
FROM student
INNER JOIN  avatar ON student.id = avatar.id AND avatar.id NOT NULL;