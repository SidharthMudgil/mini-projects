package com.sidharth.customer.service;

import com.sidharth.customer.model.Customer;
import lombok.extern.java.Log;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Log
public class CustomerService {

    private final String API_BASE_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
    private final RestTemplate restTemplate;

    public CustomerService() {
        this.restTemplate = new RestTemplate();
    }

    public Boolean createCustomer(
            String firstName, String lastName,
            String street, String address, String city, String state,
            String email, String phone, String token
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{" +
                "\"first_name\":\"" + firstName + "\"," +
                "\"last_name\":\"" + lastName + "\"," +
                "\"street\":\"" + street + "\"," +
                "\"address\":\"" + address + "\"," +
                "\"city\":\"" + city + "\"," +
                "\"state\":\"" + state + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"phone\":\"" + phone + "\"" +
                "}";

        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    API_BASE_URL + "?cmd=create",
                    HttpMethod.POST,
                    new HttpEntity<>(requestBody, headers),
                    Void.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Customer> getCustomerList(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(
                    API_BASE_URL + "?cmd=get_customer_list",
                    HttpMethod.GET,
                    requestEntity,
                    Customer[].class
            );
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteCustomer(String uuid, String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    API_BASE_URL + "?cmd=delete&uuid=" + uuid,
                    HttpMethod.POST,
                    requestEntity,
                    Void.class
            );
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(
            String firstName, String lastName,
            String street, String address, String city, String state,
            String email, String phone,
            String uuid, String authToken
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{" +
                "\"first_name\":\"" + firstName + "\"," +
                "\"last_name\":\"" + lastName + "\"," +
                "\"street\":\"" + street + "\"," +
                "\"address\":\"" + address + "\"," +
                "\"city\":\"" + city + "\"," +
                "\"state\":\"" + state + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"phone\":\"" + phone + "\"" +
                "}";

        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    API_BASE_URL + "?cmd=update&uuid=" + uuid,
                    HttpMethod.POST,
                    new HttpEntity<>(requestBody, headers),
                    Void.class
            );
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }
}
