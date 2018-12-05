package com.example.ahmadsyafiqkamil.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView tes;
    TextView txt_username,txt_password;
    Button btn_login;
    private static final String BASE_URL="http://api.lapakdhani.com/welcome/login";
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_username = (TextView)findViewById(R.id.txt_user_name);
        txt_password = (TextView)findViewById(R.id.txt_password);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();
                Log.i("data_user","username : "+username);
                login(username,password);
            }
        });
    }

    private void login(String username,String password){
        Log.i("urlnya","URL: "+BASE_URL);
        RequestBody data = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Log.i("urlnya","1");

        Request request = new Request
                .Builder()
                .url(BASE_URL)
                .post(data)
                .build();
        Log.i("urlnya","2");

        Call call = client.newCall(request);
        Log.i("urlnya","3");

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this,"Gagal Mengirimkan Pesan",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String respon = response.body().string();
                if (respon.matches("sukses")){
                    Intent intent = new Intent(MainActivity.this,home.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"Gagal Login",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
