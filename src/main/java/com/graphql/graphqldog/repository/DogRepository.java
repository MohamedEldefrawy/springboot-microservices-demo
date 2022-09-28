package com.graphql.graphqldog.repository;

import com.graphql.graphqldog.entity.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends CrudRepository<Dog, Integer> {
    @Query("select  d.breed from Dog d")
    public List<String> getDogBreeds();

    @Query("select  d.breed from Dog d where d.id=:dogId")

    public String getDogBreed(Integer dogId);

    @Query("select  d.name from Dog d")

    public List<String> getDogNames();

    @Query("from Dog")
    public List<Dog> getAllDogs();

    @Query("from Dog where id=:id")
    public Dog getDog(Integer id);
}
