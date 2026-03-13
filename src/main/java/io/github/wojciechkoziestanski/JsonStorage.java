package io.github.wojciechkoziestanski;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;

public class JsonStorage {
    private final ObjectMapper mapper;
    File file = new File("data.json");

    public JsonStorage() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        this.mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void save (TaskPlanner planner){
        try {
            mapper.writeValue(file, planner);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public TaskPlanner load(){
        if(!file.exists()){
            return null;
        }
        try{
            TaskPlanner loadedPlanner = mapper.readValue(file, TaskPlanner.class);
            return loadedPlanner;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
