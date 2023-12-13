package org.acme.lambda;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "lorem-picsum-api")
public interface LoremPicsumService {

    @GET
    @Path("/id/{id}/{height}/{width}")
    public byte[] getImageById(@PathParam("id") Integer id, @PathParam("height") Long height, @PathParam("width") Long width);
}
