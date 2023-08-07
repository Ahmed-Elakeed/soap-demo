package com.webservices.soapdemo.repository;

import com.webservices.soapdemo.soap.country.Country;
import com.webservices.soapdemo.soap.country.Currency;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        countries.put("Egypt",this.generateCountry("Egypt",100000000,"Cairo", Currency.EGY));
        countries.put("Italy",this.generateCountry("Italy",30000000,"Milano",Currency.EUR));
        countries.put("Qatar",this.generateCountry("Qatar",2000000,"Doha",Currency.RIYAL));

    }

    public Country findCountry(String name) {
        return countries.get(name);
    }

    private Country generateCountry(String name,int population,String capital,Currency currency){
        Country country=new Country();
        country.setName(name);
        country.setPopulation(population);
        country.setCapital(capital);
        country.setCurrency(currency);
        return country;
    }
}

