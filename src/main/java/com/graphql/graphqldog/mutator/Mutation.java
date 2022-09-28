package com.graphql.graphqldog.mutator;

import com.graphql.graphqldog.entity.Dog;
import com.graphql.graphqldog.exception.BreedNotFoundException;
import com.graphql.graphqldog.exception.DogNotFoundException;
import com.graphql.graphqldog.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class Mutation {
    private final DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Boolean deleteDogBreed(String breed) {
        var dogs = this.dogRepository.getAllDogs();
        boolean result = false;
        for (var dog : dogs
        ) {
            if (dog.getBreed().equals(breed)) {
                this.dogRepository.delete(dog);
                result = true;
            }
        }
        if (!result)
            throw new BreedNotFoundException("BreedNotFound", breed);
        return true;
    }

    public Dog updateDogName(String name, Integer dogID) {
        Optional<Dog> optionalDog = Optional.of(this.dogRepository.findById(dogID).get());
        var selectedDog = optionalDog.get();
        selectedDog.setName(name);
        this.dogRepository.save(selectedDog);
        return optionalDog.orElseThrow(() -> new DogNotFoundException("dog not found", dogID));
    }
}
