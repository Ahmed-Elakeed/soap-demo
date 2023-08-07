package com.webservices.soapdemo.repository;


import com.webservices.soapdemo.soap.citizen.Citizen;
import com.webservices.soapdemo.soap.citizen.Gender;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CitizenRepository {

    private static final Map<String, Citizen> citizens=new HashMap<>();

    @PostConstruct
    public void initData(){
        citizens.put("29908171401918",this.generateCitizen("Ahmed Zakaria Elakeed","29908171401918","Egypt, Benha",24,Gender.MALE));
        citizens.put("39908171401423",this.generateCitizen("Yahia Mohamed Elakeed","39908171401423","Egypt, Cairo",24,Gender.MALE));
        citizens.put("39908521401423",this.generateCitizen("Hagar Mohamed Youssef","39908521401423","Egypt, Cairo",24,Gender.FEMALE));
    }

    public Citizen findCitizen(String id){
        return citizens.get(id);
    }

    private Citizen generateCitizen(String fullName,String id, String address, int age, Gender gender){
        Citizen citizen=new Citizen();
        citizen.setFullName(fullName);
        citizen.setAddress(address);
        citizen.setAge(age);
        citizen.setGender(gender);
        citizen.setId(id);
        return citizen;
    }
}
