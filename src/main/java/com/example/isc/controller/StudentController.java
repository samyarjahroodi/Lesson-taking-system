package com.example.isc.controller;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.mapper.StudentMapper;
import com.example.isc.service.dto.request.StudentDtoRequestForRegistration;
import com.example.isc.service.dto.response.StudentDtoResponseToRegistration;
import com.example.isc.service.impl.StudentServiceImpl;
import com.example.isc.service.impl.Student_CourseServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
@Validated
public class StudentController {
    private final StudentServiceImpl studentService;
    private final Student_CourseServiceImpl student_courseService;


    @PostMapping("/student-registration")
    public ResponseEntity<StudentDtoResponseToRegistration> customerSingUp(@RequestBody @Valid StudentDtoRequestForRegistration dto) {
        Student student = StudentMapper.INSTANCE.requestDtoToModel(dto);
        studentService.studentRegistration(student);
        StudentDtoResponseToRegistration studentDtoResponseToRegistration
                = StudentMapper.INSTANCE.modelToDto(student);
        return new ResponseEntity<>(studentDtoResponseToRegistration, HttpStatus.CREATED);
    }

    @PostMapping("/add-course-to-student")
    public Set<Student_Course> addCourseToStudent(@RequestBody Student student,@RequestBody Course course) {
        return studentService.addCourseToStudent(student, course);
    }

    @GetMapping(" find-all-courses-by-student")
    public List<Student_Course> findAllCoursesByStudent(@RequestBody Student student) {
        return student_courseService.findAllCoursesByStudent(student);
    }

    @GetMapping("find-all-passed-courses-by-student")
    public List<Student_Course> findAllPassedCoursesByStudent(@RequestBody Student student) {
        return student_courseService.findAllPassedCoursesByStudent(student);
    }

    @GetMapping("find-all-not-passed-courses-by-student")
    public List<Student_Course> findAllNotPassedCoursesByStudent(@RequestBody Student student) {
        return student_courseService.findAllNotPassedCoursesByStudent(student);
    }


    @GetMapping("find-average-marks-by-student")
    public double findAverageMarksByStudent(@RequestBody Student student) {
        return studentService.findAverageMarksByStudentId(student);
    }


    @GetMapping("find-by-student-and-course")
    public Student_Course findByStudentAndCourse(Student student, Course course) {
        return student_courseService.findByStudentAndCourse(student, course);
    }

    @DeleteMapping
    public void deleteCurrentCourse(Student student, Student_Course student_course) {
        studentService.deleteCurrentCourse(student, student_course);
    }

}
