package com.graphql.graphqldog.controller;

import com.graphql.graphqldog.entity.Dog;
import com.graphql.graphqldog.exception.DogNotFoundException;
import com.graphql.graphqldog.service.IDogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DogController {
    private final IDogService dogService;

    public DogController(IDogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<Dog>> getAllDogs() {
        return new ResponseEntity<>(this.dogService.selectDogs(), HttpStatus.OK);
    }

    @GetMapping("/dogs/breeds")
    public ResponseEntity<List<String>> getAllBreeds() {
        return new ResponseEntity<>(this.dogService.selectDogBreeds(), HttpStatus.OK);
    }

    @GetMapping("/dogs/names")
    public ResponseEntity<List<String>> getAllNames() {
        return new ResponseEntity<>(this.dogService.selectDogNames(), HttpStatus.OK);

    }

    @GetMapping("/dogs/breeds/{id}")
    public ResponseEntity<String> getBreed(@PathVariable Integer id) {

        try {
            return new ResponseEntity<>(this.dogService.selectDogBreed(id), HttpStatus.OK);
        } catch (
                DogNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}