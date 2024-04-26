package com.example.isc.mapper;

import com.example.isc.entity.Teacher;
import com.example.isc.service.dto.request.TeacherDtoRequestForRegistration;
import com.example.isc.service.dto.response.TeacherDtoResponseForRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    Teacher requestDtoToModel(TeacherDtoRequestForRegistration teacherDtoRequestForRegistration);

    TeacherDtoResponseForRegistration modelToDto(Teacher teacher);

}
