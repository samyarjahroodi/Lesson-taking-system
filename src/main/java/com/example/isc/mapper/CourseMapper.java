package com.example.isc.mapper;

import com.example.isc.entity.Course;
import com.example.isc.service.dto.request.CourseDtoRequest;
import com.example.isc.service.dto.response.CourseDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course requestDtoToModel(CourseDtoRequest courseDtoRequest);

    CourseDtoResponse modelToDto(Course course);

}
