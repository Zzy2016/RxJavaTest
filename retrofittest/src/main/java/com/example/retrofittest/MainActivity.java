package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRetrofit();
            }
        });

    }

    public void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.URL_BASE).build();
        final RequestServices requestServices = retrofit.create(RequestServices.class);
        Call<ResponseBody> call=requestServices.getString();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        Log.e("Retrofit","isSuccessful-----"+response.body().string());
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Retrofit","onFailure-----"+t.toString());
            }
        });
    }
}
