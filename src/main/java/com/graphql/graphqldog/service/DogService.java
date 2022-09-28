package com.graphql.graphqldog.service;

import com.graphql.graphqldog.entity.Dog;
import com.graphql.graphqldog.exception.DogNotFoundException;
import com.graphql.graphqldog.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService implements IDogService {
    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }


    @Override
    public List<String> selectDogBreeds() {
        return this.dogRepository.getDogBreeds();
    }

    @Override
    public String selectDogBreed(Integer dogId) {
        Optional<String> optional = Optional.ofNullable(this.dogRepository.getDogBreed(dogId));
        return optional.orElseThrow(() -> new DogNotFoundException("Dog not found", dogId));
    }

    @Override
    public List<String> selectDogNames() {
        return this.dogRepository.getDogNames();
    }

    @Override
    public List<Dog> selectDogs() {
        return this.dogRepository.getAllDogs();
    }

    @Override
    public Dog selectDog(Integer id) {
        Optional<Dog> optional = Optional.ofNullable(this.dogRepository.getDog(id));
        return optional.orElseThrow(() -> new DogNotFoundException("Dog not found", id));
    }

    @Override
    public void deleteDog(Integer id) {
        this.dogRepository.deleteById(id);
    }

    @Override
    public Dog updateDog(Integer id, Dog newDog) {
        var optionalDog = Optional.ofNullable(this.dogRepository.getDog(id));
        var selectedDog = optionalDog.get();
        selectedDog.setName(newDog.getName());
        selectedDog.setBreed(newDog.getName());
        selectedDog.setOrigin(newDog.getOrigin());
        this.dogRepository.save(selectedDog);
        return optionalDog.orElseThrow(() -> new DogNotFoundException("Dog not found", id));
    }
}
