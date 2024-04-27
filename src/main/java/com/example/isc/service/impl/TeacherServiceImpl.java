package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.entity.Teacher;
import com.example.isc.entity.enumeration.Role;
import com.example.isc.exception.ExpireDateException;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.TeacherRepository;
import com.example.isc.service.TeacherService;
import com.example.isc.service.dto.request.TeacherDtoRequestForRegistration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;


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

    private String createRandomTeacherId() {
        String teacherId;
        do {
            UUID uuid = UUID.randomUUID();
            String randomId = uuid.toString().replace("-", "");
            teacherId = "00" + randomId.substring(0, 6); // Adjust the substring length as needed
        } while (!Pattern.matches("00[0-9]{6}", teacherId));
        return teacherId;
    }

    public void giveMarkToStudent(Student student, Course course, int mark, int term) {
        Student_Course studentCourse = student_courseService.findByStudentAndCourse(student, course);
        checkTeacherStatus(studentCourse.getTeacher());
        if (studentCourse != null) {
            studentCourse.setMark(mark);
            studentCourse.setTerm(term);
            studentCourse.setPass(mark >= 10);
            student_courseService.save(studentCourse);
        } else {
            throw new IllegalStateException("Student is not enrolled in the course");
        }
    }


    public boolean checkTeacherStatus(Teacher teacher) {
        return teacher.isEnabled() && !teacher.isBlocked() && !teacher.isExpired();
    }


    @Override
    @Transactional
    public void teacherRegistration(TeacherDtoRequestForRegistration dto) {
        Teacher teacher = Teacher.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .role(Role.ROLE_TEACHER)
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .isEnabled(false)
                .isBlocked(false)
                .isExpired(false)
                .teacherId(createRandomTeacherId())
                .build();


        teacherRepository.save(teacher);
    }

    @Override
    public Teacher findByTeacherId(String teacherId) {
        return repository.findByTeacherId(teacherId);
    }

    public boolean checkIfTeacherIsNotExpired(Teacher teacher) {
        if (LocalDate.now().isAfter(teacher.getCreationDate().plusYears(4))) {
            throw new ExpireDateException("Teacher's creation date is expired");
        }
        return true;
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
