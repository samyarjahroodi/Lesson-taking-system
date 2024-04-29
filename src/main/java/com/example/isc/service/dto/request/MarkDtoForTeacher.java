package com.example.isc.service.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarkDtoForTeacher {
    private double mark;

    private int term;

    private boolean isPass;
}
