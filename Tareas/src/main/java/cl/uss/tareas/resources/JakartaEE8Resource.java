package cl.uss.tareas.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("jakartaee8")
public class JakartaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping Java EE 8")
                .build();
    }
}

