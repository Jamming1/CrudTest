package com.example.CrudTest.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CarResponse {
    private Long id;
    private String modelName;
    private String type;
}
