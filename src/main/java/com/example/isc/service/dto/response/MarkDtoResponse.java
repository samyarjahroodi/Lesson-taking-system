package com.example.isc.service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarkDtoResponse {
    private double mark;

    private int term;

    private boolean isPass;
}
