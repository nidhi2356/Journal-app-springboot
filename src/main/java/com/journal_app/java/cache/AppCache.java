package com.journal_app.java.cache;


import com.journal_app.java.entity.ConfigJournalAppEntity;
import com.journal_app.java.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> appChache ;

    @PostConstruct
    public void init(){
        appChache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appChache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }

    }

}
