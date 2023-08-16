package com.develop.adservingservice.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class AzureStorageService {

    @Value("${azure.storage.sas.token}")
    private String sasToken;

    public BlobServiceClient createBlobServiceClient() {
        return new BlobServiceClientBuilder()
                .endpoint("https://sspreportingtest.blob.core.windows.net/")
                .sasToken(sasToken)
                .buildClient();
    }

    public void saveObjectNodeToBlobContainer() {
        BlobServiceClient blobServiceClient = createBlobServiceClient();

        try {
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("adservingservice");
            String local_file_path = "/Users/aman.agarwal/IdeaProjects/ad-serving-service/src/main/java/com/develop/adservingservice/adResponse.json";
            //System.out.println("\nUploading to Blob storage as blob:\n\t");
            BlobClient blobClient = containerClient.getBlobClient("adResponse.json");

            if (blobClient.exists()) {
                System.out.println("Blob already exists. Deleting...");
                blobClient.deleteIfExists();
            }
            // Upload the local file as a new blob since it doesn't exist
            // + blobClient.getBlobUrl()
            System.out.println("\nUploading to Blob storage as blob:\n\t");
            blobClient.uploadFromFile(local_file_path);

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    public void addObjectNodeToBlobContainer(ObjectNode newNode) {
        BlobServiceClient blobServiceClient = createBlobServiceClient();

        try {
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("adservingservice");

            String blobName = "adResponse.json";
            BlobClient blobClient = containerClient.getBlobClient(blobName);

            if (blobClient.exists()) {


                // Downloading from blob
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                blobClient.downloadStream(outputStream);
                String jsonContent = outputStream.toString("UTF-8");

                // Use Jackson ObjectMapper to parse the JSON content
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonContent);

                // Add the new node to the root node
                ArrayNode arrayNode;

                if (rootNode.isArray()) {
                arrayNode = (ArrayNode) rootNode;
                } else {
                arrayNode = JsonNodeFactory.instance.arrayNode();
                }

                // Add object to existing
                arrayNode.add(newNode);

                // Convert the modified JSON back to a string
                String modifiedJsonContent = objectMapper.writeValueAsString(arrayNode);

                // Convert the modified JSON back to a byte array
                byte[] modifiedJsonBytes = modifiedJsonContent.getBytes();

                // Upload the modified content to overwrite the existing blob
                blobClient.upload(new ByteArrayInputStream(modifiedJsonBytes),
                modifiedJsonBytes.length);
            } else {
                saveObjectNodeToBlobContainer();
            }

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}