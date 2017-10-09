package com.project.my.thanh.nhat.pham.dang.camerademo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnCamera, btnGallery;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = (Button) findViewById(R.id.Button_Camera_MainActivity);
        btnGallery = (Button) findViewById(R.id.Button_Gallery_MainActivity);

        ivImage = (ImageView) findViewById(R.id.ImagaView_image_MainActivity);

        btnCamera.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 125:
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                ivImage.setImageBitmap(bitmap);
                break;
            case 251:
                Uri uri = (Uri) data.getData();
                ivImage.setImageURI(uri);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Button_Camera_MainActivity:
                    Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intentCamera, 125);
                break;
            case R.id.Button_Gallery_MainActivity:
                    Intent intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
                    intentGallery.setType("image/*");
                    startActivityForResult(intentGallery, 251);
                break;
        }
    }
}
