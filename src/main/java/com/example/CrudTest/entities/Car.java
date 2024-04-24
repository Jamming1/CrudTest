package com.example.CrudTest.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Setter
@Getter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String modelName;
    private String type;

    //non so perchè non prende i setter di lombok
    public void setType(String type) {
        this.type = type;
    }
}
