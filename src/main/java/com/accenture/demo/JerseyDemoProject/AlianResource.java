package com.accenture.demo.JerseyDemoProject;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/alian")
public class AlianResource {
	AlianRepository alianaRepository = new AlianRepository();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Alian> getAlians() {

		return alianaRepository.getAlians();
	}

	// We used path parameter which means we can give the values to path of the URL
	// Ex: http://localhost:8080/JerseyDemoProject/webresources/alian/getalian/1
	@GET
	@Path("getalianparam/{name}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // Produces is use to send the data from server
																			// to client in a required formate
	public Alian getAlianWithPathValue(@PathParam("name") String name) {

		return alianaRepository.getAlian(name);
	}

	// We used Query parameter which means we can give the values in the form of
	// query.
	// Ex:http://localhost:8080/JerseyDemoProject/webresources/alian/getalian?name=Jon
	@GET
	@Path("getalianquery")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // Produces is use to send the data from server
																			// to client in a required formate
	public Alian getAlianWithQueryValue(@QueryParam("name") String name) {

		return alianaRepository.getAlian(name);
	}

	// We used Matrix parameter which means we can give the values in the below form
	// Ex:
	// http://localhost:8080/JerseyDemoProject/webresources/alian/getalian:name=Jon
	@GET
	@Path("getalianparam")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // Produces is use to send the data from server
																			// to client in a required formate
	public Alian getAlianWithMatrixValue(@MatrixParam("name") String name) {

		return alianaRepository.getAlian(name);
	}

	@GET
	@Path("setheader")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // Produces is use to send the data from server
																			// to client in a required formate
	public String setHeaderValue(@HeaderParam("Content-Type") String content_Type,
			@CookieParam("Cookie") String cookie) {

		return content_Type + cookie;
	}

	@POST
	@Path("/createalian")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // Consume is use to send the data from client
																			// to server in required formate
	public Response createAlian(Alian alian) {
		alianaRepository.createAlian(alian);
		return Response.status(Status.CREATED).entity(alian).build();
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

	/**
	 * Method to set the header value.
	 * 
	 * @return response
	 */
	@GET
	@Path("test10/{name}")
	public Response responseHeaderTest1(@PathParam("name") String name, @Context UriInfo uriInfo,
			@Context HttpHeaders httpHeader) {
		URI uri = uriInfo.getBaseUriBuilder().path("alian").build();
		System.out.println(uri);
		Response.ResponseBuilder rb = Response.status(Status.OK).entity(alianaRepository.getAlian(name));
		Response response = rb.header("header1", "value1").header("header2", "value2").build();
		return response;
	}

}
