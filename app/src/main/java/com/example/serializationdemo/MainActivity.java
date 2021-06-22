package com.example.serializationdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    EditText edtname,edtphone,edtaddress,edtcollegename,edtemail;
    CircleImageView profile;
    int CAMERA_REQUEST_CODE=101;
    int CAMERA_PERMISSION_CODE=102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtname=findViewById(R.id.name);
        edtphone=findViewById(R.id.phone);
        edtaddress=findViewById(R.id.address);
        edtcollegename=findViewById(R.id.collegename);
        edtemail=findViewById(R.id.email);

        profile=findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

    }

    void openCamera()
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap imageData=(Bitmap)data.getExtras().get("data");
        profile.setImageBitmap(imageData);
        //getData from intent
    }

    public byte[] convert_to_byteArray(ImageView img)
    {
        Bitmap myimg=((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        myimg.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] bytearr=byteArrayOutputStream.toByteArray();
        return bytearr;
    }


    public void gotoNextActivity(View view)
    {
        UserInfo userInfo=new UserInfo();
        userInfo.setName(edtname.getText().toString());
        userInfo.setPhone(edtphone.getText().toString());
        userInfo.setAddress(edtaddress.getText().toString());
        userInfo.setCollegeName(edtcollegename.getText().toString());
        userInfo.setEmail(edtemail.getText().toString());
        byte[] bytearray=convert_to_byteArray(profile);
        //image
        userInfo.setImg(bytearray);

        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("userinfo",userInfo);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}