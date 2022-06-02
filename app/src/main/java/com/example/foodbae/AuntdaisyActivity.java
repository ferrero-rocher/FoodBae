package com.example.foodbae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AuntdaisyActivity extends AppCompatActivity {
   Button btNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auntdaisy);
        btNotification=findViewById(R.id.bt_notification);
        btNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "your order has been placed";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        AuntdaisyActivity.this
                )
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("new notification")
                        .setContentText(message)
                        ;
                Intent intent = new Intent(AuntdaisyActivity.this,
                        NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);

                PendingIntent pendingintent = PendingIntent.getActivity(AuntdaisyActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingintent);

                NotificationManager notificationManager = (NotificationManager)getSystemService(
                        Context.NOTIFICATION_SERVICE
                );
                notificationManager.notify(0,builder.build());

            }
        });


    }
    public void displayToast(View v){
        Toast.makeText(AuntdaisyActivity.this,"item selected",Toast.LENGTH_SHORT).show();
    }

    public void displayIndia(View vs){
        Toast.makeText(AuntdaisyActivity.this,"Order Placed",Toast.LENGTH_SHORT).show();
    }
}
