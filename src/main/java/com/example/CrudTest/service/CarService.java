package com.example.CrudTest.service;

import com.example.CrudTest.DTOs.CarResponse;
import com.example.CrudTest.DTOs.CreateCarRequest;
import com.example.CrudTest.entities.CarEntity;
import com.example.CrudTest.models.CarModel;
import com.example.CrudTest.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public CarResponse saveCar(CreateCarRequest carRequest) {
        CarModel model = CarModel.dtoToModel(carRequest);
        CarEntity entity = CarModel.modelToEntity(model);
        CarEntity savedEntity = carRepository.save(entity);
        CarModel savedModel = CarModel.entityToModel(savedEntity);
        return CarModel.modelToDto(savedModel);
    }

    public List<CarEntity> getCars() {
        return carRepository.findAll();
    }

    public Optional<CarEntity> getById(Long id) {
        Optional<CarEntity> optionalCar = carRepository.findById(id);
        return optionalCar;
    }

    public Optional<CarEntity> updateType(Long id, String type) {
        Optional<CarEntity> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            CarEntity carEntity = optionalCar.get();
            carEntity.setType(type);
            carRepository.save(carEntity);
            return Optional.of(carEntity);
        } else {
            return Optional.empty();
        }
    }

    public Boolean delete(Long id) {
        Optional<CarEntity> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            carRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Boolean deleteAll(){
        carRepository.deleteAll();
        return true;
    }
}
