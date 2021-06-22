package com.example.serializationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity {

    CircleImageView img;
    TextView txt1,txt2,txt3,txt4,txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt1=findViewById(R.id.nametxt);
        txt2=findViewById(R.id.phonetxt);
        txt3=findViewById(R.id.addresstxt);
        txt4=findViewById(R.id.collegetxt);
        txt5=findViewById(R.id.emailtxt);
        img=findViewById(R.id.img);
        //image view convert

        UserInfo userInfo=(UserInfo)getIntent().getSerializableExtra("userinfo");

        byte[] profile=userInfo.getImg();
        String name=userInfo.getName();
        String phone=userInfo.getPhone();
        String address=userInfo.getAddress();
        String college=userInfo.getCollegeName();
        String email=userInfo.getEmail();
        Bitmap bitmap= BitmapFactory.decodeByteArray(profile,0,profile.length);

        txt1.setText("Name : "+name);
        txt2.setText("Mobile No : "+phone);
        txt3.setText("Address : "+address);
        txt4.setText("College Name : "+college);
        txt5.setText("Email : "+email);
        img.setImageBitmap(bitmap);

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calling=new Intent(Intent.ACTION_CALL);
                calling.setData(Uri.parse("tel:"+phone));
                startActivity(calling);
            }
        });

        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{txt5.getText().toString()});
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,"Choose client"));
            }
        });

    }
}