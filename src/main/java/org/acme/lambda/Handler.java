package org.acme.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public class Handler implements RequestHandler<Object, String> {

    @ConfigProperty(name = "app.env.s3.bucket")
    String bucket;

    @Inject
    S3Client s3Client;

    @Inject
    @RestClient
    LoremPicsumService loremPicsumService;

    @Override
    public String handleRequest(Object input, Context context) {
        byte[] image = loremPicsumService.getImageById(57, 2448L, 3264L);
        PutObjectResponse putObjectResponse = s3Client.putObject(PutObjectRequest
                        .builder()
                        .bucket(bucket)
                        .key("test.jpg")
                        .build(),
                RequestBody.fromBytes(image)
        );
        return putObjectResponse.eTag();
    }
}
