package com.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

@Path("/ma")
public class Service 
{
	Process process = new Process();
	
	
	@POST
    @Produces("application/json")
    @Path("/set")
    public Response setUser(@FormParam("name") String name, @FormParam("age") String age, @FormParam("book") String book) throws Exception {
        
		JsonObject jsonObject = new JsonObject();
		
		
		jsonObject.addProperty("name", name);
		jsonObject.addProperty("age", age);
		jsonObject.addProperty("book", book);
		
		String returndata = process.insert(jsonObject);
        
        return  Response.ok(returndata).build();
    }
	
	@POST
	@Produces("application/json")
	@Path("/get")
	public Response getUser() throws Exception
	{
		       
		return Response.ok(process.select()).build();
	}
	
	@POST
	@Produces("application/json")
	@Path("/delete")
	public Response deleteUser() throws Exception
	{
		return Response.ok(process.delete()).build();
	}
	
	/*@POST
	@Produces("/application/json")
	@Path("/update")
	public Response updateUser(@FormParam("name") String name,@FormParam("age") String age, @FormParam("book") String book) throws Exception
	{
		JsonObject jsonObject = new JsonObject();
		
		jsonObject.addProperty("name", name);
		jsonObject.addProperty("age", age);
		jsonObject.addProperty("book", book);
		
		String returndata = process.update(jsonObject);
		return Response.ok(returndata).build();
	}*/
}
