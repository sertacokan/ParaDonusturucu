package com.example.sertac.paradonusturucu.ui;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.sertac.paradonusturucu.R;
import com.example.sertac.paradonusturucu.network.RetrofitBuilder;
import com.example.sertac.paradonusturucu.network_model.NetworkModels;
import com.example.sertac.paradonusturucu.network_model.NetworkMoneyUnits;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText paraMiktari;
    private Spinner donusturulenPara,donusturulecekPara;
    private TextView paraGosterici;
    private Button donustur;
    private String[] paraBirimleri={"TRY","USD","GBP"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        paraMiktari= (EditText) findViewById(R.id.paraMiktari);
        donusturulenPara= (Spinner) findViewById(R.id.donusturulenPara);
            donusturulenPara.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,paraBirimleri));
        donusturulecekPara= (Spinner) findViewById(R.id.donusturulecekPara);
            donusturulecekPara.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,paraBirimleri));
        paraGosterici= (TextView) findViewById(R.id.paraGosterici);
        donustur= (Button) findViewById(R.id.donustur);
        donustur.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String donusturulecek=donusturulecekPara.getSelectedItem().toString();
        final String donusturulen=donusturulenPara.getSelectedItem().toString();
        RetrofitBuilder.getServiceConnector().networkModel(donusturulecek,donusturulen).enqueue(new Callback<NetworkModels>() {
            @Override
            public void onResponse(Call<NetworkModels> call, Response<NetworkModels> response) {
                NetworkMoneyUnits networkMoneyUnits=response.body().moneyUnits;
                    switch (donusturulen){
                        case "TRY":
                            paraGosterici.setText(String.valueOf(Integer.parseInt(paraMiktari.getText().toString())*networkMoneyUnits.TRY)+"₺");
                            break;
                        case "USD":
                            paraGosterici.setText(String.valueOf(Integer.parseInt(paraMiktari.getText().toString())*networkMoneyUnits.USD)+"$");
                            break;
                        case "GBP":
                            paraGosterici.setText(String.valueOf(Integer.parseInt(paraMiktari.getText().toString())*networkMoneyUnits.GBP)+"€");
                            break;
                    }
                }


            @Override
            public void onFailure(Call<NetworkModels> call, Throwable t) {

            }
        });
    }
}
