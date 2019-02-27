package com.example.mapsimplementationusinggoogleapi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateService extends Service {

    private Notification.Builder builder;
    private NotificationManager notificationManager;

    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.map)
                .setContentTitle("Location Updates")
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (LocationResult.hasResult(intent)){
            LocationResult locationResult = LocationResult.extractResult(intent);

            Location location = locationResult.getLastLocation();

            double lat = location.getLatitude();
            double lon = location.getLongitude();

            builder.setContentText("Lat: "+lat+" Long: "+lon);
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());
            sendPost(String.valueOf(lat),String.valueOf(lon));
           // Toast.makeText(getApplicationContext(),"Latitude: "+lat+" Longitude: "+lon,Toast.LENGTH_SHORT).show();
        }

        return START_NOT_STICKY;
    }

    public void sendPost(String latitude, String longitude){
        Loctn loctn = new Loctn();
        loctn.setLatitude(latitude);
        loctn.setLongitude(longitude);

        ApiUtils.getApiService().sendLocation(loctn).enqueue(new Callback<Loctn>() {
            @Override
            public void onResponse(Call<Loctn> call, Response<Loctn> response) {
                Toast.makeText(getApplicationContext(),"Latitude: "+response.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Loctn> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to send",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationManager.cancel(0);
    }
}
