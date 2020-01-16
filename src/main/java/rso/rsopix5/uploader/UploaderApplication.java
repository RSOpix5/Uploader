package rso.rsopix5.uploader;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;

@OpenAPIDefinition(info = @Info(title = "Rest API",
        version = "v1",
        contact = @Contact(),
        license = @License(),
        description = "Application for retrieving image uploads"),
        servers = @Server(url ="http://localhost:8080/v1"))
@ApplicationPath("/v1")
public class UploaderApplication extends javax.ws.rs.core.Application {
    public UploaderApplication() {
        System.out.println("Application instantiated");
    }
}
