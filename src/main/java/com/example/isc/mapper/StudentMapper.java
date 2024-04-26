package com.example.isc.mapper;

import com.example.isc.entity.Student;
import com.example.isc.service.dto.request.StudentDtoRequestForRegistration;
import com.example.isc.service.dto.response.StudentDtoResponseToRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student requestDtoToModel(StudentDtoRequestForRegistration studentDtoRequestForRegistration);

    StudentDtoResponseToRegistration modelToDto(Student student);

}
