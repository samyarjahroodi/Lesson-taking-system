package com.example.isc.mapper;

import com.example.isc.entity.Student_Course;
import com.example.isc.service.dto.request.MarkDtoForTeacher;
import com.example.isc.service.dto.response.MarkDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface Student_CourseMapper {
    Student_CourseMapper INSTANCE = Mappers.getMapper(Student_CourseMapper.class);

    Student_Course requestDtoToModel(MarkDtoForTeacher dto);

    MarkDtoResponse modelToDto(Student_Course student_course);

}
