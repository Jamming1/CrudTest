package com.example.CrudTest.models;

import com.example.CrudTest.DTOs.CarResponse;
import com.example.CrudTest.DTOs.CreateCarRequest;
import com.example.CrudTest.entities.CarEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarModel {
    private Long id;
    private String modelName;
    private String type;

    public CarModel(Long id, String modelName, String type) {
        this.id = id;
        this.modelName = modelName;
        this.type = type;
    }

    public CarModel(String modelName, String type) {
        this.modelName = modelName;
        this.type = type;
    }

    //metodo che converte da dto a model request dal controller
    public static CarModel dtoToModel(CreateCarRequest carRequest) {
        return new CarModel(carRequest.getModelName(), carRequest.getType());
    }

    //da model a dto response al controller
    public static CarResponse modelToDto(CarModel carModel) {
        return new CarResponse(carModel.getId(), carModel.getModelName(), carModel.getType());
    }

    //model to entity request al db
    public static CarEntity modelToEntity(CarModel carModel) {
        return new CarEntity(carModel.getId(), carModel.getModelName(), carModel.getType());
    }

    //entity to model response dal db
    public static CarModel entityToModel(CarEntity carEntity) {
        return new CarModel(carEntity.getId(), carEntity.getModelName(), carEntity.getType());
    }
}
