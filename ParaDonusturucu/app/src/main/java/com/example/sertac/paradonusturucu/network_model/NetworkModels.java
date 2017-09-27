package com.example.sertac.paradonusturucu.network_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkModels {

    @SerializedName("base")
    @Expose
    public String donusturulenBirim;

    @SerializedName("date")
    @Expose
    public String tarih;

    @SerializedName("rates")
    public NetworkMoneyUnits moneyUnits;
}
