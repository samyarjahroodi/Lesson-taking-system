package com.example.isc.controller;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Teacher;
import com.example.isc.mapper.TeacherMapper;
import com.example.isc.service.dto.request.TeacherDtoRequestForRegistration;
import com.example.isc.service.dto.response.TeacherDtoResponseForRegistration;
import com.example.isc.service.impl.TeacherServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
@Validated
public class TeacherController {
    private final TeacherServiceImpl teacherService;

    @GetMapping("/get-course-list")
    public List<Course> getCourseList(String teacherId) {
        return teacherService.getCourseList(teacherId);
    }

    @PostMapping("/teacher-registration")
    public ResponseEntity<TeacherDtoResponseForRegistration> teacherRegistration(@RequestBody @Valid TeacherDtoRequestForRegistration dto) {
        Teacher teacher = TeacherMapper.INSTANCE.requestDtoToModel(dto);
        teacherService.teacherRegistration(teacher);
        TeacherDtoResponseForRegistration teacherDtoResponseForRegistration
                = TeacherMapper.INSTANCE.modelToDto(teacher);
        return new ResponseEntity<>(teacherDtoResponseForRegistration, HttpStatus.CREATED);
    }

    @GetMapping("/find-by-teacher-id")
    public Teacher findByTeacherId(@RequestParam String teacherId) {
        return teacherService.findByTeacherId(teacherId);
    }

    @PostMapping("/give-mark-to-student")
    public void giveMarkToStudent(@RequestBody Student student, @RequestBody Course course
            , @RequestParam double mark, @RequestParam int term) {
        teacherService.giveMarkToStudent(student, course, mark, term);
    }

    @PostMapping("/check-teacher-status")
    public boolean checkTeacherStatus(@RequestBody Teacher teacher) {
        return teacherService.checkTeacherStatus(teacher);
    }

}
