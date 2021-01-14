package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button);

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://3.18.145.46:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                String textId = editText.getText().toString();
                String textPw = editText.getText().toString();
                LoginService retrofitAPI2 = retrofit2.create(LoginService.class);
                UserInfo userInfo = new UserInfo(textId,textPw);
                Call<Login> signinCall = retrofitAPI2.requestLogin(userInfo);

                signinCall.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        switch (response.code()){
                            case 200 :
                                Toast.makeText(MainActivity.this,"정상적으로 로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        switch (response.code()){
                            case 403 :
                                Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
                                break;
                        }
                        switch (response.code()){
                            case 428 :
                                Toast.makeText(MainActivity.this,"비밀번호를 변경해야 합니다.",Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                    Log.e("##########","Fail",t);
                    }
                });

            }
        });
    }
}