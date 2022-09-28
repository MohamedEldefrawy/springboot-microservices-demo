package com.graphql.graphqldog.controller;

import com.graphql.graphqldog.entity.Dog;
import com.graphql.graphqldog.exception.BreedNotFoundException;
import com.graphql.graphqldog.service.IDogService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

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


    @MutationMapping
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

    @MutationMapping
    public Dog updateDogName(@Argument String newName, @Argument Integer id) {
        var selectedDog = this.dogService.selectDog(id);
        selectedDog.setName(newName);
        return this.dogService.updateDog(id, selectedDog);

    }
}
