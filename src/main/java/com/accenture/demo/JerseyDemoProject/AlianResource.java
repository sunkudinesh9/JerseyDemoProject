package com.accenture.demo.JerseyDemoProject;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/alian")
public class AlianResource {
	AlianRepository alianaRepository = new AlianRepository();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Alian> getAlians() {

		return alianaRepository.getAlians();
	}

	@GET
	@Path("getalian/{name}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alian getAlian(@PathParam("name") String name) {

		return alianaRepository.getAlian(name);
	}

	@POST
	@Path("/createalian")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alian createAlian(Alian alian) {
		alianaRepository.createAlian(alian);
		return alian;
	}
}
