package com.voglo.edison.vogloclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Service;

public class MainActivity extends AppCompatActivity {
    Service service = new Service();

    Button buttonUsers;
    Button buttonServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUsers = (Button) findViewById(R.id.buttonUsers);
        buttonServices = (Button) findViewById(R.id.buttonServices);

        buttonUsers.setOnClickListener(v -> {
            Toast.makeText(this, "User", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });

        buttonServices.setOnClickListener(v -> {
            Toast.makeText(this, "Service", Toast.LENGTH_SHORT).show();
        });


        Thread t1 = new Thread(){
            @Override
            public void run() {
                DefaultApi defaultApi = new DefaultApi();

                try {
                    service = defaultApi.obtenirServeiPerId(1);
                } catch (ApiException e) {
                    e.printStackTrace();
                }

            }
        };

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
