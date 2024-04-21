package com.turkcell.rentacar.business.dtos.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetModelResponse {
    private int id;
    private String name;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
