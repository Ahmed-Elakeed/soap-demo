package com.webservices.soapdemo.endpoint;

import com.webservices.soapdemo.repository.CitizenRepository;
import com.webservices.soapdemo.soap.citizen.GetCitizenRequest;
import com.webservices.soapdemo.soap.citizen.GetCitizenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CitizenEndpoint {

    private static final String NAMESPACE_URI = "http://citizen.soap.soapdemo.webservices.com/";

    private final CitizenRepository citizenRepository;

    @Autowired
    public CitizenEndpoint(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCitizenRequest")
    @ResponsePayload
    public GetCitizenResponse getCitizen(@RequestPayload GetCitizenRequest request) {
        GetCitizenResponse response = new GetCitizenResponse();
        response.setCitizen(citizenRepository.findCitizen(request.getId()));
        return response;
    }
}
