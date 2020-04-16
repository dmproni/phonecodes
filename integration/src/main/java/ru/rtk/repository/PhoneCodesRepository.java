package ru.rtk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.rtk.model.PhoneCode;

import java.util.List;

public interface PhoneCodesRepository extends MongoRepository<PhoneCode, String> {

    @Override
    <S extends PhoneCode> List<S> saveAll(Iterable<S> iterable);
}
