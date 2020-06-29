package com.example.citatnikandroid.activities.widget;

import android.Manifest;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.citatnikandroid.R;
import com.example.citatnikandroid.models.Citata;
import com.example.citatnikandroid.server.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WidgetProvider extends AppWidgetProvider {

    private RemoteViews remoteViews;
    private ComponentName componentName;

    @Override
    public void onUpdate(Context context, final AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        componentName = new ComponentName(context, WidgetProvider.class);

        int id = (int) ((Math.random() * 4) + 1);

        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID(id)
                .enqueue(new Callback<Citata>() {
                    @Override
                    public void onResponse(@NonNull Call<Citata> call, @NonNull Response<Citata> response) {
                        Citata citata = response.body();

                        remoteViews.setTextViewText(R.id.widgetContent, citata.getContent());
                        remoteViews.setTextViewText(R.id.widgetAuthor, citata.getAuthor());
                        remoteViews.setTextViewText(R.id.widgetDate, citata.getCreationDate());

                        appWidgetManager.updateAppWidget(componentName, remoteViews);
                    }

                    @Override
                    public void onFailure(Call<Citata> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
