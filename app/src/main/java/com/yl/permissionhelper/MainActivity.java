package com.yl.permissionhelper;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callPhone = (Button) findViewById(R.id.call_phone);
        callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionHelper.with(MainActivity.this)
                        .requestPermission(Manifest.permission.CALL_PHONE)
                        .requestCode(1)
                        .setListener(new PermissionHelper.RequestListener() {
                            @Override
                            public void onGranted() {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:10086"));
                                startActivity(intent);
                            }

                            @Override
                            public void onDenied() {
                                Toast.makeText(MainActivity.this, "权限拒绝",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).request();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        PermissionHelper.requestPermissionResult(requestCode, grantResults);
    }
}
