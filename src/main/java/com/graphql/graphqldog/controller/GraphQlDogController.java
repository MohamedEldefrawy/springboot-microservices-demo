package com.graphql.graphqldog.controller;

import com.graphql.graphqldog.entity.Dog;
import com.graphql.graphqldog.exception.BreedNotFoundException;
import com.graphql.graphqldog.service.IDogService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphQlDogController {
    private final IDogService dogService;

    public GraphQlDogController(IDogService dogService) {
        this.dogService = dogService;
    }

    @QueryMapping
    public List<Dog> findAllDogs() {
        return this.dogService.selectDogs();
    }

    @QueryMapping
    public Dog findDogById(Integer id) {
        return this.dogService.selectDog(id);
    }


    @QueryMapping
    public Boolean deleteDogBreed(String breed) {
        var dogs = this.dogService.selectDogs();
        boolean result = false;
        for (var dog : dogs
        ) {
            if (dog.getBreed().equals(breed)) {
                this.dogService.deleteDog(dog.getId());
                result = true;
            }
        }
        if (!result)
            throw new BreedNotFoundException("BreedNotFound", breed);
        return true;
    }

    @QueryMapping
    public Dog updateDogName(String name, Integer dogID) {
        Optional<Dog> optionalDog = Optional.of(this.dogService.selectDog(dogID));
        var selectedDog = optionalDog.get();
        selectedDog.setName(name);
        return this.dogService.updateDog(dogID, selectedDog);
    }
}
