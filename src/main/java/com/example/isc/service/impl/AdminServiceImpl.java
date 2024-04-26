package com.example.isc.service.impl;

import com.example.isc.entity.Admin;
import com.example.isc.entity.Student;
import com.example.isc.entity.Teacher;
import com.example.isc.entity.enumeration.Role;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.AdminRepository;
import com.example.isc.service.AdminService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class AdminServiceImpl
        extends UserServiceImpl<Admin, AdminRepository>
        implements AdminService {
    private final AdminRepository adminRepository;
    private final StudentServiceImpl studentService;
    private final TeacherServiceImpl teacherService;

    public AdminServiceImpl(AdminRepository repository, BCryptPasswordEncoder passwordEncoder, AdminRepository adminRepository, StudentServiceImpl studentService, TeacherServiceImpl teacherService) {
        super(repository, passwordEncoder);
        this.adminRepository = adminRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public boolean signIn(String username, String password) {
        return adminRepository.signIn(username, password);
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


    @PostConstruct
    public void createAdminWhenApplicationRuns() {
        if (!adminRepository.existsByUsername("admin")) {
            Admin adminUser = Admin.builder()
                    .username("admin")
                    .role(Role.ROLE_ADMIN)
                    .isEnabled(true)
                    .password("adminpassword") // Set the password through User builder
                    .build();

            adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword())); // Encode the password

            adminRepository.save(adminUser);

            System.out.println("Admin user created successfully.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }


    @Override
    public <S extends Admin> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Admin> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Admin> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Admin> findAllById(Iterable<Integer> id) {
        return repository.findAllById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Admin entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Admin getReferenceById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NullInputException("null"));
    }
}
