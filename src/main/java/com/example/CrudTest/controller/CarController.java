package com.example.CrudTest.controller;

import com.example.CrudTest.entities.Car;
import com.example.CrudTest.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    //meglio private o default?
    private CarRepository carRepository;

   //crea una nuova car nella tabella
    @PostMapping()
    public Car create(@RequestBody Car car) {
        Car carSaved = carRepository.saveAndFlush(car);
        return carSaved;

    }
    //una lista pagable di tutte le cars
    @GetMapping
    public Page<Car> getAll(@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) Optional<Integer> size) {
        if (page.isPresent() && size.isPresent()) {
            Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "modelName"));
            Pageable pageable = PageRequest.of(page.get(), size.get(), sort);
            Page<Car> cars = carRepository.findAll(pageable);
            return cars;
        } else {
            Page<Car> carPage = Page.empty();
            return carPage;

        }
    }
    //trova una car by id
    @GetMapping("/{id}")
    //rappresenta un'intera httpResponse
    public ResponseEntity<Car> getById(@PathVariable Long id) {

        //ritorna null se non trova corrispondenza
        Optional<Car> optionalCar = carRepository.findById(id);
        return ResponseEntity.of(optionalCar);
    }

    @PutMapping("/{id}")

    public ResponseEntity<Car> updateCarType(@PathVariable Long id, @RequestParam String type) { //aggiorna tipo auto con id
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setType(type);
            carRepository.save(car);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);

            return ResponseEntity.ok().build();
        } else {
           //da come risposta un conflitto se la macchina non è presente
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("")
    public void deleteAll(@RequestParam List<Long> ids){
        carRepository.deleteAllById(ids);
    }

}
