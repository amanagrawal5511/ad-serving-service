package com.develop.adservingservice.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class LogDataService {

    public String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a DateTimeFormatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time as a string
        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;
    }

    public void logData(ObjectNode adResponse) {

        String filePath = "/Users/aman.agarwal/IdeaProjects/ad-serving-service/src/main/java/com/develop/adservingservice/adResponse.json";

        try {
            // Loading the existing JSON Object
            File jsonFile = ResourceUtils.getFile(filePath);
            byte[] jsonData = Files.readAllBytes(Paths.get(jsonFile.getPath()));

            // Parse the existing JSON content
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);

            ArrayNode arrayNode;

            if (rootNode.isArray()) {
                arrayNode = (ArrayNode) rootNode;
            } else {
                arrayNode = JsonNodeFactory.instance.arrayNode();
            }

            adResponse.put("dateTimeCreation", getCurrentDateTime());
            // Add object to existing
            arrayNode.add(adResponse);

            // Write updated JSON to the file
            objectMapper.writeValue(jsonFile, arrayNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
