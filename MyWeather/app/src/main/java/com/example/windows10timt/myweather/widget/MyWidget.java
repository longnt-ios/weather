package com.example.windows10timt.myweather.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.example.windows10timt.myweather.R;

/**
 * Created by Windows 10 TIMT on 12/28/2016.
 */

public class MyWidget extends AppWidgetProvider {
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String widgetText = preferences.getString("city", "");
        String widgetText2 = preferences.getString("temp", "");
        String widgetText3 = preferences.getString("description", "");
        boolean check = preferences.getBoolean("checkWidget", false);


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget_layout);
        views.setTextViewText(R.id.textWidget1, widgetText);

        if (check == false) {
            views.setTextViewText(R.id.textWidget2, widgetText2 + "°F");

        } else if (check == true) {
            int newTemp = (int) Math.round((Integer.valueOf(widgetText2) - 32) / 1.8);
            String newWidget = String.valueOf(newTemp);
            views.setTextViewText(R.id.textWidget2, newWidget + "°C");
        }
        views.setTextViewText(R.id.textWidget3, widgetText3);
        if (widgetText3.equals("Showers") || widgetText3.equals("Scattered Showers")) {
            views.setImageViewResource(R.id.imageWidget, R.drawable.snow);
        } else if (widgetText3.equals("Breezy")) {
            views.setImageViewResource(R.id.imageWidget, R.drawable.breezy);
        } else if (widgetText3.equals("Sunny") || widgetText3.equals("Mostly Sunny")) {
            views.setImageViewResource(R.id.imageWidget, R.drawable.sunny);
        } else if (widgetText3.equals("Partly Cloudy") || widgetText3.equals("Cloudy") || widgetText3.equals("Mostly Cloudy")) {
            views.setImageViewResource(R.id.imageWidget, R.drawable.cloud_much);
        } else if (widgetText3.equals("Rain")) {
            views.setImageViewResource(R.id.imageWidget, R.drawable.rain);
        }


        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

        }
    }
}
