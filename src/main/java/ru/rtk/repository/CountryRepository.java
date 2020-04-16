package ru.rtk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.rtk.model.Country;

import java.util.List;

public interface CountryRepository extends MongoRepository<Country, String> {
    @Override
    List<Country> findAll();
    @Override
    <S extends Country> List<S> saveAll(Iterable<S> iterable);
}
