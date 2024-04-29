package com.example.CrudTest.DTOs;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCarRequest {
    private String modelName;
    private String type;
}
