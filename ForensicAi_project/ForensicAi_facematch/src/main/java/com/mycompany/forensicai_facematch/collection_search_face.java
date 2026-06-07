package com.mycompany.forensicai_facematch;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;

public class collection_search_face {

    public static String SKETCH_LOCAL_PATH = "";

    public static void main(String[] args) {

        String imagePath = SKETCH_LOCAL_PATH;
        if (args.length > 0) {
            imagePath = args[0];
        }

        if (imagePath.isEmpty()) {
            System.out.println("❌ No image path provided!");
            return;
        }

        System.out.println("🔍 Testing search with: " + imagePath);

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        try {
            File file = new File(imagePath);
            byte[] imageBytes = Files.readAllBytes(file.toPath());

            Image image = new Image().withBytes(ByteBuffer.wrap(imageBytes));

            SearchFacesByImageRequest request = new SearchFacesByImageRequest()
                    .withCollectionId("Records")
                    .withImage(image)
                    .withFaceMatchThreshold(30F)
                    .withMaxFaces(5);

            SearchFacesByImageResult result = rekognitionClient.searchFacesByImage(request);

            System.out.println("\n✅ Search Completed!");
            System.out.println("Matches Found: " + result.getFaceMatches().size() + "\n");

            for (FaceMatch match : result.getFaceMatches()) {
                System.out.println("Rank " + (result.getFaceMatches().indexOf(match) + 1) + ":");
                System.out.println("   Matched Image : " + match.getFace().getExternalImageId());
                System.out.println("   Similarity    : " + String.format("%.2f", match.getSimilarity()) + "%");
                System.out.println("   -------------------");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}