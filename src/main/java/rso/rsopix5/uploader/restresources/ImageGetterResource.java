package rso.rsopix5.uploader.restresources;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import rso.rsopix5.uploader.beans.ImageBean;
import rso.rsopix5.uploader.models.Image;
import rso.rsopix5.uploader.models.ImagePost;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/image")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImageGetterResource {

    @Inject
    private ImageBean imageBean;

    @Operation(description = "Inserts image submission into the database", summary = "Insert image submission", tags = "submissions",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Returned value of inserted image submission",
                            content = @Content(schema = @Schema(implementation = Image.class)))
            }
    )
    @POST
    /*@Metered(name = "post_insert_submission")
    @Timed(name= "post_insert_submission_timed")*/
    public Response insertImage(
            @Parameter(schema = @Schema(implementation = ImagePost.class)) ImagePost image) {
        return imageBean.insertNewImage(image);
    }
}

