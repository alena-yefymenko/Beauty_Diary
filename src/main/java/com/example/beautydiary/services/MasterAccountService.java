package com.example.beautydiary.services;

import com.example.beautydiary.entities.Category;
import com.example.beautydiary.entities.PriceListItem;
import com.example.beautydiary.repositories.MasterAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterAccountService {
    private MasterAccountRepository masterAccountRepository;
@Autowired
    public MasterAccountService(MasterAccountRepository masterAccountRepository) {
        this.masterAccountRepository = masterAccountRepository;
    }

    public PriceListItem addPriceListItem(PriceListItem priceListItem){
    return masterAccountRepository.save(priceListItem);
    }

    public List<PriceListItem> getAll(){
     return masterAccountRepository.findAll();
    }


}
