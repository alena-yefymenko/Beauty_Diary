package com.example.beautydiary.repositories;

import com.example.beautydiary.entities.Beautician;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BeauticianRepository extends CrudRepository< Beautician, Long > {

}
