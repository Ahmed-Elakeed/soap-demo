package com.webservices.soapdemo.endpoint;

import com.webservices.soapdemo.repository.CountryRepository;
import com.webservices.soapdemo.soap.country.GetCountryRequest;
import com.webservices.soapdemo.soap.country.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    private static final Logger LOGGER= LoggerFactory.getLogger(CountryEndpoint.class);

    private static final String NAMESPACE_URI = "http://country.soap.soapdemo.webservices.com/";

    private final CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        LOGGER.info("Getting data for country with name : {}",request.getName());
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        LOGGER.info("Data for country with name : {} = {}",request.getName(),response);
        return response;
    }
}

