package com.hbvhuwe.musicshop.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controller.JpaController;
import model.Group;

@Path("/groups")
public class GroupService {
	private JpaController db = new JpaController();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getGroups() {
		List<Group> groups = db.getGroups();
		JsonArray result = new JsonArray();
		for (Group it : groups) {
			JsonObject object = new JsonObject();
			object.addProperty("GroupID", it.getGroupID());
			object.addProperty("Name", it.getName());
			object.addProperty("Musician", it.getMusician());
			object.addProperty("Style", it.getStyle());
			result.add(object);
		}
		return result.toString();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getGroup(@PathParam("id") int id) {
		JsonArray groups = new JsonArray();
		JsonParser parser = new JsonParser();
		groups = parser.parse(getGroups()).getAsJsonArray();
		JsonObject group = new JsonObject();
		groups.forEach((it) -> {
			if (it.getAsJsonObject().get("GroupID").getAsInt() == id) {
				group.add("GroupID", it.getAsJsonObject().get("GroupID"));
				group.add("Name", it.getAsJsonObject().get("Name"));
				group.add("Musician", it.getAsJsonObject().get("Musician"));
				group.add("Style", it.getAsJsonObject().get("Style"));
			}
		});
		return group.toString();
	}
	
	@PUT
	@Path("/add")
	public void addGroup(@QueryParam("Name") String name,
			@QueryParam("Musician") String musician,
			@QueryParam("Style") String style) {
		Group group = new Group();
		group.setName(name);
		group.setMusician(musician);
		group.setStyle(style);
		db.add(group);
	}
	
	@DELETE
	@Path("/delete/{id}")
	public void deleteGroup(@PathParam("id") int id) {
		db.delete(id, Group.class.getSimpleName());
	}
}
