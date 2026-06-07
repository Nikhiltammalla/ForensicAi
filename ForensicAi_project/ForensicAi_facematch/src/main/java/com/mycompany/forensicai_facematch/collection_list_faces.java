package com.mycompany.forensicai_facematch;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

public class collection_list_faces {

    public static final String COLLECTION_ID = "Records";

    public static void main(String[] args) {

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        System.out.println("📋 Listing all faces in Collection: " + COLLECTION_ID);
        System.out.println("============================================================\n");

        try {
            ListFacesRequest request = new ListFacesRequest()
                    .withCollectionId(COLLECTION_ID)
                    .withMaxResults(1000);   // Increase if you have more than 1000 faces

            ListFacesResult result = rekognitionClient.listFaces(request);

            System.out.println("Total Faces Indexed: " + result.getFaces().size() + "\n");

            int count = 1;
            for (Face face : result.getFaces()) {
                System.out.println(count++ + ". Face ID     : " + face.getFaceId());
                System.out.println("   External ID  : " + (face.getExternalImageId() != null ? face.getExternalImageId() : "N/A"));
                System.out.println("   Confidence   : " + String.format("%.2f", face.getConfidence()) + "%");
                System.out.println("   -----------------------------");
            }

            if (result.getFaces().isEmpty()) {
                System.out.println("No faces found in the collection.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}