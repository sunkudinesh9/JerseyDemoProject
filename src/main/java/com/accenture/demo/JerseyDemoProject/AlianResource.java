package com.accenture.demo.JerseyDemoProject;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // Produces is use to send the data from server
																			// to client in a required formate
	public Alian getAlian(@PathParam("name") String name) {

		return alianaRepository.getAlian(name);
	}

	@POST
	@Path("/createalian")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // Consume is use to send the data from client
																			// to server in required formate
	public Alian createAlian(Alian alian) {
		alianaRepository.createAlian(alian);
		return alian;
	}

	@PUT
	@Path("/updatealian")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alian updateAlian(Alian alian) {
		if (alianaRepository.getAlian(alian.getName()).getPoints() == 0) {
			alianaRepository.createAlian(alian);
		} else {
			alianaRepository.updateAlian(alian);
		}
		alianaRepository.updateAlian(alian);
		return alian;
	}

	@DELETE
	@Path("/deletealian/{name}")
	public Alian deleteAlian(@PathParam("name") String name) {
		Alian a = alianaRepository.getAlian(name);
		alianaRepository.deleteAlian(name);
		return a;
	}
}
