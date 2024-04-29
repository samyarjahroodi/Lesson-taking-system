package com.example.isc.service.dto.response;

import com.example.isc.entity.enumeration.FieldOfStudy;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDtoResponse {
    private String name;

    private int unit;

    private FieldOfStudy fieldOfStudy;
}
