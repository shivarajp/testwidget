package widgets.triveous.com.testwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import widgets.triveous.com.testwidget.WidgetProviders.RecorderWidget1by1Provider;
import widgets.triveous.com.testwidget.WidgetProviders.RecorderWidget4by1Provider;

/**
 * Created by Shivam on 2/12/2015.
 */
public class WidgetManager {

    static final int RECORDING = 1;
    static final int STOP = 2;

    public static void updateWidget(final Context context, int method) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName recording1by1Widget;
        ComponentName recording4by1Widget;
        int[] allWidgetIds;

        switch (method) {
            case RECORDING:
                //For 1*1 widget
                recording1by1Widget = new ComponentName(context, RecorderWidget1by1Provider.class);
                allWidgetIds = appWidgetManager.getAppWidgetIds(recording1by1Widget);
                for (int widgetId : allWidgetIds) {
                    RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recorder1by1_layout);
                    remoteViews.setImageViewResource(R.id.record, R.drawable.ic_action_stop);
                    // Have to get the service class and start recording
                    Intent intent = new Intent(context, MainActivity2Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    remoteViews.setOnClickPendingIntent(R.id.record, pendingIntent);
                    AppWidgetManager.getInstance(context).updateAppWidget(recording1by1Widget, remoteViews);
                }

                //For 4*1 widget
                recording4by1Widget = new ComponentName(context, RecorderWidget4by1Provider.class);
                allWidgetIds = appWidgetManager.getAppWidgetIds(recording4by1Widget);
                for (int widgetId : allWidgetIds) {
                    RemoteViews remoteViews = new RemoteViews(
                            context.getPackageName(),
                            R.layout.recorder4by1_layout);

                    //Set view visibility
                    remoteViews.setViewVisibility(R.id.pause, View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.stop, View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.camera, View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.start, View.GONE);
                    remoteViews.setViewVisibility(R.id.msg, View.GONE);
                    remoteViews.setViewVisibility(R.id.time, View.VISIBLE);

                    // Create intents for respective action classes
                    Intent i1 = new Intent(context, MainActivity2Activity.class);
                    Intent i2 = new Intent(context, MainActivity2Activity.class);
                    Intent i3 = new Intent(context, MainActivity2Activity.class);

                    //Testing purpose
                    i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    i1.setData(Uri.parse(i1.toUri(Intent.URI_INTENT_SCHEME)));
                    i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    i2.setData(Uri.parse(i1.toUri(Intent.URI_INTENT_SCHEME)));
                    i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i3.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    i3.setData(Uri.parse(i1.toUri(Intent.URI_INTENT_SCHEME)));

                    //Create pending intents to perform action
                    PendingIntent pause = PendingIntent.getActivity(context, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
                    PendingIntent stop = PendingIntent.getActivity(context, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
                    PendingIntent camera = PendingIntent.getActivity(context, 0, i3, PendingIntent.FLAG_UPDATE_CURRENT);

                    remoteViews.setOnClickPendingIntent(R.id.pause, pause);
                    remoteViews.setOnClickPendingIntent(R.id.stop, stop);
                    remoteViews.setOnClickPendingIntent(R.id.camera, camera);

                    AppWidgetManager.getInstance(context).updateAppWidget(recording4by1Widget, remoteViews);
                    Log.d("","Started recording");
                }
                break;

            case STOP:
                Log.d("case", "2");
                recording1by1Widget = new ComponentName(context, RecorderWidget1by1Provider.class);
                allWidgetIds = appWidgetManager.getAppWidgetIds(recording1by1Widget);
                for (int widgetId : allWidgetIds) {
                    RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recorder1by1_layout);
                    remoteViews.setImageViewResource(R.id.record, R.drawable.ic_action_mic);
                    Intent settingsIntent = new Intent(context, MainActivity2Activity.class);
                    settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    settingsIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    settingsIntent.setData(Uri.parse(settingsIntent.toUri(Intent.URI_INTENT_SCHEME)));
                    PendingIntent pendingSettingsIntent = PendingIntent.getActivity(context, 0, settingsIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    remoteViews.setOnClickPendingIntent(R.id.record, pendingSettingsIntent);
                    AppWidgetManager.getInstance(context).updateAppWidget(recording1by1Widget, remoteViews);
                }

                //For 4*1 widget
                recording4by1Widget = new ComponentName(context, RecorderWidget4by1Provider.class);
                allWidgetIds = appWidgetManager.getAppWidgetIds(recording4by1Widget);
                for (int widgetId : allWidgetIds) {
                    RemoteViews remoteViews = new RemoteViews(
                            context.getPackageName(),
                            R.layout.recorder4by1_layout);

                    //Set view visibility
                    remoteViews.setViewVisibility(R.id.pause, View.GONE);
                    remoteViews.setViewVisibility(R.id.stop, View.GONE);
                    remoteViews.setViewVisibility(R.id.camera, View.GONE);
                    remoteViews.setViewVisibility(R.id.start, View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.msg, View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.time, View.GONE);

                    // Create intents for respective action classes
                    Intent i1 = new Intent(context, MainActivity2Activity.class);
                    Intent i2 = new Intent(context, MainActivity2Activity.class);
                    Intent i3 = new Intent(context, MainActivity2Activity.class);

                    //Testing purpose
                    i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    i1.setData(Uri.parse(i1.toUri(Intent.URI_INTENT_SCHEME)));
                    i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    i2.setData(Uri.parse(i1.toUri(Intent.URI_INTENT_SCHEME)));
                    i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i3.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    i3.setData(Uri.parse(i1.toUri(Intent.URI_INTENT_SCHEME)));

                    //Create pending intents to perform action
                    PendingIntent pause = PendingIntent.getActivity(context, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
                    PendingIntent stop = PendingIntent.getActivity(context, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
                    PendingIntent camera = PendingIntent.getActivity(context, 0, i3, PendingIntent.FLAG_UPDATE_CURRENT);

                    remoteViews.setOnClickPendingIntent(R.id.start, pause);
                    AppWidgetManager.getInstance(context).updateAppWidget(recording4by1Widget, remoteViews);
                    Log.d("","Stopped recording");
                }


                break;
        }
    }
}
