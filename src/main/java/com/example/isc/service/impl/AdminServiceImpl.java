package com.example.isc.service.impl;

import com.example.isc.entity.Admin;
import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Teacher;
import com.example.isc.entity.enumeration.Role;
import com.example.isc.repository.AdminRepository;
import com.example.isc.service.AdminService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class AdminServiceImpl
        extends BaseUserServiceImpl<Admin, AdminRepository>
        implements AdminService {
    private final AdminRepository adminRepository;
    private final StudentServiceImpl studentService;
    private final TeacherServiceImpl teacherService;
    private final CourseServiceImpl courseService;

    public AdminServiceImpl(AdminRepository repository, BCryptPasswordEncoder passwordEncoder, AdminRepository adminRepository, StudentServiceImpl studentService, TeacherServiceImpl teacherService, CourseServiceImpl courseService) {
        super(repository, passwordEncoder);
        this.adminRepository = adminRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @Override
    @Transactional
    public void enableStudent(String studentId) {
        Student student = studentService.findByStudentId(studentId);
        student.setEnabled(true);
        studentService.save(student);

    }

    @Override
    @Transactional
    public void enableTeacher(String teacherId) {
        Teacher teacher = teacherService.findByTeacherId(teacherId);
        teacher.setEnabled(true);
        teacherService.save(teacher);
    }

    @Override
    public void addCourse(Course course) {
        courseService.courseRegistration(course);
    }


    @PostConstruct
    public void createAdminWhenApplicationRuns() {
        if (!adminRepository.existsByUsername("admin")) {
            Admin adminUser = Admin.builder()
                    .username("admin")
                    .role(Role.ROLE_ADMIN)
                    .isEnabled(true)
                    .password("adminpassword")
                    .build();

            adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));

            adminRepository.save(adminUser);

            System.out.println("Admin user created successfully.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }

}
