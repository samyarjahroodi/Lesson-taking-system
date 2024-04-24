package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.StudentRepository;
import com.example.isc.service.StudentService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class StudentServiceImpl
        extends UserServiceImpl<Student, StudentRepository>
        implements StudentService {
    private final StudentRepository studentRepository;
    private final Student_CourseServiceImpl student_courseService;


    public StudentServiceImpl(StudentRepository repository, BCryptPasswordEncoder passwordEncoder, StudentRepository studentRepository, Student_CourseServiceImpl student_courseService) {
        super(repository, passwordEncoder);
        this.studentRepository = studentRepository;
        this.student_courseService = student_courseService;
    }

    @Override
    public boolean checkIfStudentIsPassedACourse(Student student, Course course) {
        List<Student_Course> studentCourses = student_courseService.findAllByStudent(student);
        for (Student_Course studentCourse : studentCourses) {
            if (studentCourse.getCourse().equals(course) && studentCourse.isPass()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <S extends Student> S save(S entity) {
        return studentRepository.save(entity);
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllById(Iterable<Integer> id) {
        return studentRepository.findAllById(id);
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void delete(Student entity) {
        studentRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public Student getReferenceById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new NullInputException("null"));
    }
}
