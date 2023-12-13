package com.sidharth.customer.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Getter
@Service
public class LoginService {
    private String authToken;

    public String authenticate(String loginId, String password) {
        String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        String requestBody = "{\"login_id\":\"" + loginId + "\",\"password\":\"" + password + "\"}";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(apiUrl, requestBody, String.class);

        try {
            assert response != null;
            JsonElement jsonElement = JsonParser.parseString(response);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (jsonObject.has("access_token")) {
                authToken = jsonObject.get("access_token").getAsString();
                return authToken;
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }
}

