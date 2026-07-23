package com.journal_app.java.service;


import com.journal_app.java.api.response.WeatherResponse;
import com.journal_app.java.cache.AppCache;
import com.journal_app.java.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.appChache.get(AppCache.keys.WEATHER_API.toString())
                .replace(Placeholders.CITY,city)
                .replace(Placeholders.API_KEY,apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate
                .exchange(finalAPI,
                        HttpMethod.GET,
                        null,
                        WeatherResponse.class);
        return response.getBody();
    }
}

