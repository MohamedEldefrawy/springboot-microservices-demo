package com.graphql.graphqldog.service;

import com.graphql.graphqldog.entity.Dog;

import java.util.List;

public interface IDogService {
    public List<String> selectDogBreeds();


    public String selectDogBreed(Integer dogId);


    public List<String> selectDogNames();

    public List<Dog> selectDogs();

    public Dog selectDog(Integer id);

    public void deleteDog(Integer id);

    public Dog updateDog(Integer id , Dog newDog);

}
