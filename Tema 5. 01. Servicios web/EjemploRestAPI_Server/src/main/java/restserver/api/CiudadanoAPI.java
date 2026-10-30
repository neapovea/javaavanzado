package restserver.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import restserver.dao.CiudadanosDAO;
import restserver.data.Ciudadano;

@Path("ciudadano")
public class CiudadanoAPI {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Ciudadano ciudadano = CiudadanosDAO.getInstance().getCiudadanoById(id);
        return Response.ok().entity(ciudadano).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.ok().entity(CiudadanosDAO.getInstance().getCiudadanos()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(Ciudadano ciudadano) {
        CiudadanosDAO.getInstance().updateCiudadano(ciudadano);
        return Response.ok().build();
    }
}
