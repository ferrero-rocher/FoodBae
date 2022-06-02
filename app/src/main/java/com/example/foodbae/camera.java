package com.example.foodbae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;

import java.io.OutputStream;


public class camera extends AppCompatActivity {
    private Button btnCapture;
    private Button btSave;
    OutputStream outputStream;
    private Button  upload;
    private ImageView imgCapture;
    private static final int Image_Capture_Code = 1;
    private long backPressedTime;
    private static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "simplified coding";
    private static final String CHANNEL_DESC = "simplified_coding Notification";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);



       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

           NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
           channel.setDescription(CHANNEL_DESC);
           NotificationManager manager = getSystemService(NotificationManager.class);
           manager.createNotificationChannel(channel);



       }


        btnCapture =(Button)findViewById(R.id.btnTakePicture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });

        Animation animation =AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        btnCapture.startAnimation(animation);
        imgCapture = (ImageView) findViewById(R.id.capturedImage);
        btSave = (Button) findViewById(R.id.bt_save);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });




    }

    private void displayNotification(){
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notification")
                .setContentText("Your Notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat mNotificationMgr=NotificationManagerCompat.from(this);
        mNotificationMgr.notify(1,mBuilder.build());

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onBackPressed() {

        if(backPressedTime+2000>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else
        {
            Toast.makeText(getBaseContext(),"Press Back again to upload",Toast.LENGTH_SHORT).show();
        }
        backPressedTime=System.currentTimeMillis();

    }
}
