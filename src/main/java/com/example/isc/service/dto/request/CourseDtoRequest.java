package com.example.isc.service.dto.request;

import com.example.isc.entity.enumeration.FieldOfStudy;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDtoRequest {
    private String name;

    private int unit;

    private FieldOfStudy fieldOfStudy;
}
