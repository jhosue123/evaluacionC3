package com.example.evc3.Views;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.evc3.R;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {


    ImageView imageProfile;
    ProgressDialog progressDialog;

    private  static  final  int CAMERA_REQUEST=100;
    private static final  int IMAGE_PICK_CAMERA_REQUEST=400;

    String cameraPermission[];
    Uri imageUri;
    String profileOrCoverImage;
    MaterialButton editImage;

    ImageButton btn_home;

    Button btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState){

       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);

        editImage=findViewById(R.id.edit_image);
        imageProfile=findViewById(R.id.profile_image);
        progressDialog=new ProgressDialog( this);
        progressDialog.setCanceledOnTouchOutside(false);
        cameraPermission=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};

        btn_home=(ImageButton) findViewById(R.id.btn_home);

        btn_cancel=(Button)findViewById(R.id.btn_cancel);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Update Profile Picture");
                profileOrCoverImage="image";
                showImagePicDialog();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Glide.with(this).load(imageUri).into(imageProfile);

    }
    @Override
    public  void  onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]grantResult)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResult);
        switch (requestCode){
            case CAMERA_REQUEST:{
                if(grantResult.length>0){
                    boolean cameraAccepted=grantResult[0]== PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted=grantResult[1]==PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && writeStorageAccepted){
                        pickFromCamera();
                    }else{
                        Toast.makeText(this ,"habilitar camara y permso a almacenamiento",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(this,"something went wrong! try again..",Toast.LENGTH_LONG).show();
                }

            }break;
        }

    }
    private  void  showImagePicDialog(){
        String options[]={"camera", "Gallery"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Pick image from");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromCamera();
                    }
                }
            }
        });
        builder.create().show();
    }
    private  Boolean checkCameraPermission(){
        boolean result= ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result && result1;

    }
    private void requestCameraPermission(){
        requestPermissions(cameraPermission,CAMERA_REQUEST);
    }

    private  void  pickFromCamera(){
        ContentValues contentValues=new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,"temp_pic");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"temp description");
        imageUri=this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues );
        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMERA_REQUEST);
    }
}