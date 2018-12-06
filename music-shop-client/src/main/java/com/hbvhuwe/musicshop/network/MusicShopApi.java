package com.hbvhuwe.musicshop.network;

import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * REST music shop api interface<br>
 * used in NetProvider implementation of DataProvider
 */
public interface MusicShopApi {
    @GET("groups/")
    Call<List<Group>> getGroups();

    @GET("groups/{id}")
    Call<Group> getGroup(@Path("id") int id);

    @GET("disks/")
    Call<List<Disk>> getDisks();

    @GET("disks/{id}")
    Call<Disk> getDisk(@Path("id") int id);

    @GET("compositions/")
    Call<List<Composition>> getCompositions();

    @GET("compositions/{id}")
    Call<Composition> getComposition(@Path("id") int id);

    @PUT("groups/add")
    Call<Void> addGroup(@Query("Name") String name,
                        @Query("Musician") String musician,
                        @Query("Style") String style);

    @PUT("disks/add")
    Call<Void> addDisk(@Query("Name") String name,
                       @Query("Amount") int amount,
                       @Query("PresentDate") String presentDate,
                       @Query("Price") double price,
                       @Query("GroupID") int groupId);

    @PUT("compositions/add")
    Call<Void> addComposition(@Query("Name") String name,
                              @Query("Duration") String duration,
                              @Query("PresentDate") String presentDate,
                              @Query("DiskID") int diskId);

    @DELETE("groups/delete/{id}")
    Call<Void> deleteGroup(@Path("id") int id);

    @DELETE("disks/delete/{id}")
    Call<Void> deleteDisk(@Path("id") int id);

    @DELETE("compositions/delete/{id}")
    Call<Void> deleteComposition(@Path("id") int id);
}
