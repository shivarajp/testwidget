package widgets.triveous.com.testwidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RemoteViews;

import widgets.triveous.com.testwidget.WidgetProviders.RecorderWidget1by1Provider;


public class MainActivity2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        WidgetManager.updateWidget(this,1);
    }

    private void updateWidget() {
        Log.d("","recording started");
        //Update widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        ComponentName recording1by1Widget = new ComponentName(this, RecorderWidget1by1Provider.class);
        appWidgetManager.getAppWidgetIds(recording1by1Widget);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.recorder1by1_layout);
        remoteViews.setImageViewResource(R.id.record,R.drawable.ic_action_stop);
        AppWidgetManager.getInstance( this ).updateAppWidget( recording1by1Widget, remoteViews );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
