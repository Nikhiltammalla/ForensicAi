package com.mycompany.forensicai_facematch;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

public class collection_add_image {

    public static final String COLLECTION_ID = "Records";
    public static final String BUCKET_NAME   = "forensicai6969";

    public static final String[] IMAGE_NAMES = {
        "a-sharukh.jpg","f-005-01.jpg","f-006-01.jpg","f-007-01.jpg","f-008-01.jpg","f-009-01.jpg",
        "f-010-01.jpg","f-011-01.jpg","f-012-01.jpg","f-013-01.jpg","f-014-01.jpg","f-015-01.jpg",
        "f-016-01.jpg","f-017-01.jpg","f-018-01.jpg","f-019-01.jpg","f-020-01.jpg","f-021-01.jpg",
        "f-022-01.jpg","f-023-01.jpg","f-024-01.jpg","f-025-01.jpg","f-026-01.jpg","f-027-01.jpg",
        "f-028-01.jpg","f-029-01.jpg","f-030-01.jpg","f-031-01.jpg","f-032-01.jpg","f-033-01.jpg",
        "f-034-01.jpg","f-035-01.jpg","f-036-01.jpg","f-037-01.jpg","f-038-01.jpg","f-039-01.jpg",
        "f-040-01.jpg","f-041-01.jpg","f-042-01.jpg","f-043-01.jpg",
        "f1-001-01.jpg","f1-002-01.jpg","f1-003-01.jpg","f1-004-01.jpg","f1-005-01.jpg",
        "f1-006-01.jpg","f1-007-01.jpg","f1-008-01.jpg","f1-009-01.jpg","f1-010-01.jpg",
        "f1-011-01.jpg","f1-012-01.jpg","f1-013-01.jpg","f1-014-01.jpg","f1-015-01.jpg",
        "m-008-01.jpg","m-009-01.jpg","m-010-01.jpg","m-011-01.jpg","m-012-01.jpg",
        "m-013-01.jpg","m-014-01.jpg","m-015-01.jpg","m-016-01.jpg","m-017-01.jpg",
        "m-018-01.jpg","m-019-01.jpg","m-021-01.jpg","m-022-01.jpg","m-023-01.jpg",
        "m-024-01.jpg","m-025-01.jpg","m-026-01.jpg","m-027-01.jpg","m-028-01.jpg",
        "m-029-01.jpg","m-030-01.jpg","m-031-01.jpg","m-032-01.jpg","m-033-01.jpg",
        "m-034-01.jpg","m-035-01.jpg","m-036-01.jpg","m-037-01.jpg","m-038-01.jpg",
        "m-039-01.jpg","m-040-01.jpg","m-041-01.jpg","m-042-01.jpg","m-043-01.jpg",
        "m-044-01.jpg","m-045-01.jpg","m-046-01.jpg","m-047-01.jpg","m-048-01.jpg",
        "m-049-01.jpg","m-050-01.jpg","m-051-01.jpg","m-052-01.jpg","m-053-01.jpg",
        "m-054-01.jpg","m-055-01.jpg","m-056-01.jpg","m-057-01.jpg","m-058-01.jpg",
        "m-059-01.jpg","m-060-01.jpg","m-061-01.jpg","m-062-01.jpg","m-063-01.jpg",
        "m-064-01.jpg","m-065-01.jpg","m-066-01.jpg","m-067-01.jpg","m-068-01.jpg",
        "m-069-01.jpg","m-070-01.jpg","m-071-01.jpg","m-072-01.jpg","m-073-01.jpg",
        "m-074-01.jpg","m-075-01.jpg","m-076-01.jpg","m-077-01.jpg","m-078-01.jpg",
        "m-079-01.jpg","m-080-01.jpg","m-081-01.jpg","m-082-01.jpg","m-083-01.jpg",
        "m-084-01.jpg","m-085-01.jpg","m-086-01.jpg","m-087-01.jpg","m-088-01.jpg",
        "m-089-01.jpg","m-090-01.jpg","m-091-01.jpg","m-092-01.jpg","m-093-01.jpg",
        "m-094-01.jpg","m-095-01.jpg","m-096-01.jpg","m-097-01.jpg","m-098-01.jpg",
        "m-099-01.jpg","m-100-01.jpg","m-101-01.jpg",
        "m1-001-01.jpg","m1-002-01.jpg","m1-003-01.jpg","m1-004-01.jpg","m1-005-01.jpg",
        "m1-006-01.jpg","m1-007-01.jpg","m1-008-01.jpg","m1-009-01.jpg","m1-010-01.jpg",
        "m1-011-01.jpg","m1-012-01.jpg","m1-013-01.jpg","m1-014-01.jpg","m1-015-01.jpg",
        "m1-016-01.jpg","m1-017-01.jpg","m1-018-01.jpg","m1-019-01.jpg","m1-020-01.jpg",
        "m1-021-01.jpg","m1-022-01.jpg","m1-023-01.jpg","m1-024-01.jpg","m1-025-01.jpg",
        "m1-026-01.jpg","m1-027-01.jpg","m1-028-01.jpg","m1-029-01.jpg","m1-030-01.jpg",
        "m1-031-01.jpg","m1-032-01.jpg","m1-033-01.jpg","m1-034-01.jpg","m1-035-01.jpg",
        "m1-036-01.jpg","m1-037-01.jpg","m1-038-01.jpg","m1-039-01.jpg","m1-040-01.jpg",
        "m1-041-01.jpg",
        "m-56-164.jpeg", "m-76-176.jpeg", "f-23-345.jpeg", "m-64-164.jpeg"
    };

    public static void main(String[] args) {

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        System.out.println("🚀 Starting Batch Indexing of " + IMAGE_NAMES.length + " images...\n");

        int success = 0;

        for (String imageName : IMAGE_NAMES) {
            try {
                System.out.print("Indexing: " + imageName + " ... ");

                Image image = new Image()
                        .withS3Object(new S3Object()
                                .withBucket(BUCKET_NAME)
                                .withName(imageName));

                IndexFacesRequest request = new IndexFacesRequest()
                        .withCollectionId(COLLECTION_ID)
                        .withImage(image)
                        .withExternalImageId(imageName)
                        .withDetectionAttributes("ALL")
                        .withMaxFaces(1);

                rekognitionClient.indexFaces(request);

                System.out.println("✅ Success");
                success++;

            } catch (Exception e) {
                System.out.println("❌ Failed - " + e.getMessage());
            }
        }

        System.out.println("\n🎉 FINISHED!");
        System.out.println("Successfully Indexed: " + success + " images");
    }
}