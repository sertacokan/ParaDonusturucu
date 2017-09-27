package com.example.sertac.paradonusturucu.interfaces;

import com.example.sertac.paradonusturucu.network_model.NetworkModels;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ServiceConnector {
    @GET("/latest")
    Call<NetworkModels> networkModel(@Query("base")String donusturulecekPara,@Query("symbols") String donusturulenPara);
}
