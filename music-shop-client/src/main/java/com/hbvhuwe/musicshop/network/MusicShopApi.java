package com.hbvhuwe.musicshop.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface MusicShopApi {
    @GET("/music-shop-api/rest/groups/all")
    Call<ResponseBody> getGroups();

    @GET("/music-shop-api/rest/groups/{id}")
    Call<ResponseBody> getGroup(@Path("id") int id);

    @GET("/music-shop-api/rest/disks/all")
    Call<ResponseBody> getDisks();

    @GET("/music-shop-api/rest/disks/{id}")
    Call<ResponseBody> getDisk(@Path("id") int id);

    @GET("/music-shop-api/rest/compositions/all")
    Call<ResponseBody> getCompositions();

    @GET("/music-shop-api/rest/compositions/{id}")
    Call<ResponseBody> getComposition(@Path("id") int id);

    @PUT("/music-shop-api/rest/groups/add")
    Call<Void> addGroup(@Query("Name") String name,
                        @Query("Musician") String musician,
                        @Query("Style") String style);

    @PUT("/music-shop-api/rest/disks/add")
    Call<Void> addDisk(@Query("Name") String name,
                       @Query("Amount") int amount,
                       @Query("PresentDate") String presentDate,
                       @Query("Price") double price,
                       @Query("GroupID") int groupId);

    @PUT("/music-shop-api/rest/compositions/add")
    Call<Void> addComposition(@Query("Name") String name,
                              @Query("Duration") String duration,
                              @Query("PresentDate") String presentDate,
                              @Query("DiskID") int diskId);

    @DELETE("/music-shop-api/rest/groups/delete/{id}")
    Call<Void> deleteGroup(@Path("id") int id);

    @DELETE("/music-shop-api/rest/disks/delete/{id}")
    Call<Void> deleteDisk(@Path("id") int id);

    @DELETE("/music-shop-api/rest/compositions/delete/{id}")
    Call<Void> deleteComposition(@Path("id") int id);
}
