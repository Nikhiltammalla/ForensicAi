package com.mycompany.forensicai_facematch;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;

/**
 * Create Rekognition Collection
 */
public class collection_create {

    public static void main(String[] args) {

        String collectionId = "Records";   // Collection Name

        // Using default client (region from aws configure)
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        System.out.println("Creating collection: " + collectionId);

        CreateCollectionRequest request = new CreateCollectionRequest()
                .withCollectionId(collectionId);

        try {
            CreateCollectionResult result = rekognitionClient.createCollection(request);

            System.out.println("✅ Collection Created Successfully!");
            System.out.println("Collection ARN : " + result.getCollectionArn());
            System.out.println("Status Code    : " + result.getStatusCode());

        } 
        catch (AmazonRekognitionException e) {
            if (e.getErrorCode().equals("CollectionIdAlreadyExistsException")) {
                System.out.println("⚠️ Collection '" + collectionId + "' already exists.");
                System.out.println("You can proceed to add images.");
            } else {
                System.out.println("❌ Rekognition Error: " + e.getErrorMessage());
            }
        } 
        catch (Exception e) {
            System.out.println("❌ Unexpected Error: " + e.getMessage());
        }
    }
}