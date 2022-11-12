package it.l_soft.offers.rest.handlers;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import it.l_soft.offers.rest.ApplicationProperties;
import it.l_soft.offers.rest.JsonHandler;
import it.l_soft.offers.rest.Utils;
import it.l_soft.offers.rest.dbUtils.DBConnection;
import it.l_soft.offers.rest.dbUtils.DBInterface;
import it.l_soft.offers.rest.dbUtils.Employee;

@Path("/user")
public class UsersHandler {
	@Context
	private ServletContext context;

	ApplicationProperties prop = ApplicationProperties.getInstance();
	final Logger log = Logger.getLogger(this.getClass());

	String contextPath = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	DBConnection conn = null;

	@POST
	@Path("/byToken/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response functionName(String body, @HeaderParam("Language") String language, @PathParam("token") String tokenParam)
	{
		int languageId = Utils.setLanguageId(language);

		JsonReader jsonReader = Json.createReader(new StringReader(body));
		JsonObject jsonIn = jsonReader.readObject();
		jsonReader.close();
		
		Employee employee;
		String emailParam = Utils.checkJsonAttribute(jsonIn, "email");

		DBConnection conn = null;
		try 
		{
			conn = DBInterface.connect();
			employee = Employee.getByEMail(conn, emailParam);
			if (employee == null)
			{
				log.debug("Employee mail '" + emailParam + "' not found");
				return Utils.jsonizeResponse(Response.Status.FORBIDDEN, null, languageId, "generic.execError");
			}
			log.trace("Retrieval completed. Employee token '" + employee.getToken() + "'");
			if (employee.getToken().compareTo(Utils.decode(employee.getClientSecure(), tokenParam)) != 0)
			{
				log.debug("Employee token '" + tokenParam + "' not matching repository");
				return Utils.jsonizeResponse(Response.Status.FORBIDDEN, null, languageId, "generic.execError");			
			}
			DBInterface.disconnect(conn);
		}
		catch(Exception e) 
		{
			log.error("Exception '" + e.getMessage() + "'", e);
			return Utils.jsonizeResponse(Response.Status.INTERNAL_SERVER_ERROR, e, languageId, "generic.execError");
		}

		HashMap<String, Object> jsonResponse = new HashMap<>();
		jsonResponse.put("authenticated", true); // here goes the object name
		JsonHandler jh = new JsonHandler();
		if (jh.jasonize(jsonResponse, language) != Response.Status.OK)
		{
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity(jh.json).build();
		}
		return Response.status(Response.Status.OK).entity(jh.json).build();
	}
	
	@GET
	@Path("/login/getSecure")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServerSecure(String body, @HeaderParam("Language") String language)
	{
		HashMap<String, Object> jsonResponse = new HashMap<>();
		jsonResponse.put("serverSecure", prop.getServerSecure()); // here goes the object name
		JsonHandler jh = new JsonHandler();
		if (jh.jasonize(jsonResponse, language) != Response.Status.OK)
		{
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity(jh.json).build();
		}
		return Response.status(Response.Status.OK).entity(jh.json).build();
		
	}
}
