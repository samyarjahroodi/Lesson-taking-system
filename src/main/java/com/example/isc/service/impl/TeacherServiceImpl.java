package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.entity.Teacher;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.TeacherRepository;
import com.example.isc.service.Student_CourseService;
import com.example.isc.service.TeacherService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@Service
public class TeacherServiceImpl
        extends UserServiceImpl<Teacher, TeacherRepository>
        implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final Student_CourseServiceImpl student_courseService;


    public TeacherServiceImpl(TeacherRepository repository, BCryptPasswordEncoder passwordEncoder, TeacherRepository teacherRepository, Student_CourseServiceImpl student_courseService) {
        super(repository, passwordEncoder);
        this.teacherRepository = teacherRepository;
        this.student_courseService = student_courseService;
    }


    public void giveMarkToStudent(Student student, Course course, int mark, int term) {
        Student_Course studentCourse = student_courseService.findByStudentAndCourse(student, course);
        if (studentCourse != null) {
            studentCourse.setMark(mark);
            studentCourse.setTerm(term);
            studentCourse.setPass(mark >= 10);
            student_courseService.save(studentCourse);
        } else {
            throw new IllegalStateException("Student is not enrolled in the course");
        }
    }


    @Override
    public <S extends Teacher> S save(S entity) {
        return teacherRepository.save(entity);
    }

    @Override
    public Optional<Teacher> findById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> findAllById(Iterable<Integer> id) {
        return teacherRepository.findAllById(id);
    }

    @Override
    public void deleteById(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void delete(Teacher entity) {
        teacherRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        teacherRepository.deleteAll();
    }

    @Override
    public Teacher getReferenceById(Integer id) {
        return teacherRepository.findById(id).orElseThrow(() -> new NullInputException("null"));
    }

    @Override
    public List<Course> getCourseList(String teacherId) {
        return repository.getCourseList(teacherId);
    }
}
