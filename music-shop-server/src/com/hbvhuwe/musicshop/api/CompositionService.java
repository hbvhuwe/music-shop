package com.hbvhuwe.musicshop.api;

import java.sql.Date;
import java.sql.Time;
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

import controller.IModel;
import controller.JpaController;
import model.Disk;
import model.Composition;

@Path("/compositions")
public class CompositionService {
	private JpaController db = new JpaController();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCompositions() {
		List<Composition> compositions = db.getCompositions();
		JsonArray result = new JsonArray();
		for (Composition it : compositions) {
			JsonObject composition = new JsonObject();
			composition.addProperty("CompositionID", it.getCompositionID());
			composition.addProperty("Duration", it.getDuration().toString());
			composition.addProperty("Name", it.getName());
			composition.addProperty("PresentDate", it.getPresentDate().toString());
			composition.addProperty("DiskID", it.getDisk().getDiskID());
			result.add(composition);
		}
		return result.toString();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getComposition(@PathParam("id") int id) {
		JsonParser parser = new JsonParser();
		JsonArray compositions = new JsonArray();
		compositions = parser.parse(getCompositions()).getAsJsonArray();
		JsonObject composition = new JsonObject();
		compositions.forEach((it) -> {
			if (it.getAsJsonObject().get("CompositionID").getAsInt() == id) {
				composition.add("CompositionID", it.getAsJsonObject().get("CompositionID"));
				composition.add("Duration", it.getAsJsonObject().get("Duration"));
				composition.add("Name", it.getAsJsonObject().get("Name"));
				composition.add("PresentDate", it.getAsJsonObject().get("PresentDate"));
				composition.add("DiskID", it.getAsJsonObject().get("DiskID"));
			}
		});
		return composition.toString();
	}

	@PUT
	@Path("/add")
	public void addComposition(@QueryParam("Duration") String duration,
		@QueryParam("Name") String name,
		@QueryParam("PresentDate") String presentDate,
		@QueryParam("DiskID") int diskId) {
		Composition composition = new Composition();
		composition.setDuration(Time.valueOf(duration));
		composition.setName(name);
		composition.setPresentDate(Date.valueOf(presentDate));
		composition.setDisk((Disk) findById(diskId, Disk.class));
		db.add(composition);
	}

	@DELETE
	@Path("/delete/{id}")
	public void deleteComposition(@PathParam("id") int id) {
		db.delete(id, Composition.class.getSimpleName());
	}

	private IModel findById(int id, Class<?> clazz) {
		IModel obj = null;
		for (Object x : db.getObjectList(clazz)) {
			obj = (IModel) x;
			if (obj.getId() == id) {
				return obj;
			}
		}
		return null;
	}
}