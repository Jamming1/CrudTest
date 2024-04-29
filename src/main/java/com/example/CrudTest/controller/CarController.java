package com.example.CrudTest.controller;
import com.example.CrudTest.DTOs.CreateCarRequest;
import com.example.CrudTest.entities.CarEntity;
import com.example.CrudTest.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

   //crea una nuova car nella tabella
    @PostMapping()
    public ResponseEntity<CarEntity> create(@RequestBody CreateCarRequest carRequest) {
        CarEntity newCarEntity = carService.saveCar(carRequest);
        return ResponseEntity.ok().body(newCarEntity);
    }
    //una lista pagable di tutte le cars
    @GetMapping("/")
    public ResponseEntity<List<CarEntity>> getAll() {
        List<CarEntity> carEntities = carService.getCars();
        return ResponseEntity.ok().body(carEntities);
    }
    //trova una car by id
    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> getById(@PathVariable Long id) {

        //ritorna null se non trova corrispondenza
        return ResponseEntity.of(carService.getById(id));
    }

    @PutMapping("/{id}")

    public ResponseEntity<CarEntity> updateCarType(@PathVariable Long id, @RequestParam String type) { //aggiorna tipo auto con id
        Optional<CarEntity> optionalCar = carService.updateType(id,type);
        return optionalCar.map(carEntity -> new ResponseEntity<>(carEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
            if(carService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    @DeleteMapping("")
    public ResponseEntity<?> deleteAll(){
        carService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

