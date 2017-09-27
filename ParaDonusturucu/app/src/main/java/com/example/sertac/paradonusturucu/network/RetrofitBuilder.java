package com.example.sertac.paradonusturucu.network;

import com.example.sertac.paradonusturucu.constants.Constants;
import com.example.sertac.paradonusturucu.interfaces.ServiceConnector;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static ServiceConnector serviceConnector;

    public static ServiceConnector getServiceConnector(){
        if (serviceConnector==null){
            Retrofit retrofit=new Retrofit.Builder()
                             .baseUrl(Constants.BASE_URL)
                              .addConverterFactory(GsonConverterFactory.create())
                             .build();
            serviceConnector=retrofit.create(ServiceConnector.class);
            return serviceConnector;
        }
        else
            return serviceConnector;
    }
}
