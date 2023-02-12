package com.example.beautydiary.repositories;

import com.example.beautydiary.entities.PriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterAccountRepository extends JpaRepository<PriceListItem,Long> {
}
