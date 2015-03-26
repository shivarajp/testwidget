package widgets.triveous.com.testwidget.WidgetProviders;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import widgets.triveous.com.testwidget.MainActivity2Activity;
import widgets.triveous.com.testwidget.R;

/**
 * Created by Shivam on 2/12/2015.
 */
public class RecorderWidget1by1Provider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d("case", "onUpdate()1*1");

        //Before doing this we have to check wheather already recording is going on or not
        //if yes then update buttons according to that by calling WidgetManagaer.UpdateWidget()
        for (int widgetId : appWidgetIds) {
            Intent settingsIntent = new Intent(context, MainActivity2Activity.class);
            settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            settingsIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
            settingsIntent.setData(Uri.parse(settingsIntent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent pendingSettingsIntent = PendingIntent.getActivity(context, 0, settingsIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recorder1by1_layout);
            views.setOnClickPendingIntent(R.id.record, pendingSettingsIntent);
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }
}
