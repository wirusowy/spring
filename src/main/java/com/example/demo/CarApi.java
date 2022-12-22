package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/")
public class CarApi {
    private List<Car> carList;

    public CarApi() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "seat", "ibiza"));
        carList.add(new Car(2L, "Opel", "vectra"));
        carList.add(new Car(3L, "Opel", "astra"));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCar() {
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id){
        Optional<Car> first = carList.stream().filter(dupa -> dupa.getId() == id).findFirst();
        if(first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car){
        boolean add = carList.add(car);
        if(add){
return new ResponseEntity(HttpStatus.CREATED);
        }
        else return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

