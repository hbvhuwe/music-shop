package com.hbvhuwe.musicshop.api;

import java.sql.Date;
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
import model.Group;

@Path("/disks")
public class DiskService {
	private JpaController db = new JpaController();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDisks() {
		List<Disk> disks = db.getDisks();
		JsonArray result = new JsonArray();
		for (Disk it : disks) {
			JsonObject object = new JsonObject();
			object.addProperty("DiskID", it.getDiskID());
			object.addProperty("Name", it.getName());
			object.addProperty("Amount", it.getAmount());
			object.addProperty("PresentDate", it.getPresentDate().toString());
			object.addProperty("Price", it.getPrice());
			object.addProperty("GroupID", it.getGroup().getGroupID());
			result.add(object);
		}
		return result.toString();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDisk(@PathParam("id") int id) {
		JsonArray disks = new JsonArray();
		JsonParser parser = new JsonParser();
		disks = parser.parse(getDisks()).getAsJsonArray();
		JsonObject disk = new JsonObject();
		disks.forEach((it) -> {
			if (it.getAsJsonObject().get("DiskID").getAsInt() == id) {
				disk.add("DiskID", it.getAsJsonObject().get("DiskID"));
				disk.add("Name", it.getAsJsonObject().get("Name"));
				disk.add("Amount", it.getAsJsonObject().get("Amount"));
				disk.add("PresentDate", it.getAsJsonObject().get("PresentDate"));
				disk.add("Price", it.getAsJsonObject().get("Price"));
				disk.add("GroupID", it.getAsJsonObject().get("GroupID"));
			}
		});
		return disk.toString();
	}

	@PUT
	@Path("/add")
	public void addDisk(@QueryParam("Name") String name,
		@QueryParam("Amount") int amount,
		@QueryParam("PresentDate") String presentDate,
		@QueryParam("Price") double price,
		@QueryParam("GroupID") int groupId) {
		Disk disk = new Disk();
		disk.setName(name);
		disk.setAmount(amount);
		disk.setPresentDate(Date.valueOf(presentDate));
		disk.setPrice(price);
		disk.setGroup((Group) findById(groupId, Group.class));
		db.add(disk);
	}

	@DELETE
	@Path("/delete/{id}")
	public void deleteDisk(@PathParam("id") int id) {
		db.delete(id, Disk.class.getSimpleName());
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