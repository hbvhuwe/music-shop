package com.hbvhuwe.musicshop.providers;

import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import com.hbvhuwe.musicshop.model.Model;
import com.hbvhuwe.musicshop.network.MusicShopApi;
import com.hbvhuwe.musicshop.network.MusicShopService;
import retrofit2.Response;

import java.util.List;

public class NetProvider<T extends Model> implements DataProvider<T> {
    private final Class<T> tClass;
    private final MusicShopApi api = MusicShopService.getApi();

    NetProvider(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> selectAll() throws Exception {
        if (tClass == Group.class) {
            Response<List<Group>> response = api.getGroups().execute();
            if (response.isSuccessful()) {
                return (List<T>) response.body();
            } else {
                System.out.println(response.toString());
                throw new Exception("Error while getting info");
            }
        } else if (tClass == Disk.class) {
            Response<List<Disk>> response = api.getDisks().execute();
            if (response.isSuccessful()) {
                return (List<T>) response.body();
            } else {
                throw new Exception("Error while getting info");
            }
        } else if (tClass == Composition.class) {
            Response<List<Composition>> response = api.getCompositions().execute();
            if (response.isSuccessful()) {
                return (List<T>) response.body();
            } else {
                throw new Exception("Error while getting info");
            }
        } else {
            throw new Exception("Unknown type");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T select(int id) throws Exception {
        if (tClass == Group.class) {
            Response<Group> response = api.getGroup(id).execute();
            if (response.isSuccessful()) {
                return (T) response.body();
            } else {
                throw new Exception("Error while getting info");
            }
        } else if (tClass == Disk.class) {
            Response<Disk> response = api.getDisk(id).execute();
            if (response.isSuccessful()) {
                return (T) response.body();
            } else {
                throw new Exception("Error while getting info");
            }
        } else if (tClass == Composition.class) {
            Response<Composition> response = api.getComposition(id).execute();
            if (response.isSuccessful()) {
                return (T) response.body();
            } else {
                throw new Exception("Error while getting info");
            }
        } else {
            throw new Exception("Unknown type");
        }
    }

    @Override
    public void add(T object) throws Exception {
        if (tClass == Group.class) {
            Group group = (Group) object;
            Response<Void> response = api.addGroup(group.getName(),
                    group.getMusician(),
                    group.getStyle()).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error while adding info");
            }
        } else if (tClass == Disk.class) {
            Disk disk = (Disk) object;
            Response<Void> response = api.addDisk(disk.getName(),
                    disk.getAmount(),
                    disk.getPresentDate(),
                    disk.getPrice(),
                    disk.getGroupId()).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error while adding info");
            }
        } else if (tClass == Composition.class) {
            Composition composition = (Composition) object;
            Response<Void> response = api.addComposition(composition.getName(),
                    composition.getDuration(),
                    composition.getPresentDate(),
                    composition.getDiskId()).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error while adding info");
            }
        } else {
            throw new Exception("Unknown type");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        if (tClass == Group.class) {
            Response<Void> response = api.deleteGroup(id).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error while deleting info");
            }
        } else if (tClass == Disk.class) {
            Response<Void> response = api.deleteDisk(id).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error while deleting info");
            }
        } else if (tClass == Composition.class) {
            Response<Void> response = api.deleteComposition(id).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error while deleting info");
            }
        } else {
            throw new Exception("Unknown type");
        }
    }

    @Override
    public void updateWith(T mask) throws Exception {
        throw new Exception("Not implemented");
    }
}
