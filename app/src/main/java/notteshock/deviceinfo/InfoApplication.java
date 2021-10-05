/*
 * This is the source code of 7 Device Info.
 * It is licensed under the The GNU General Public License v3.0.
 * You should have received a copy of the license in this repo (see LICENSE).
 *
 * Copyright lahds13, 2021.
 */

package notteshock.deviceinfo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import notteshock.deviceinfo.utilities.InfoUtilities;

public class InfoApplication extends Application {

    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public static Context applicationContext;
    public static volatile Handler applicationHandler;

    @Override
    public void onCreate() {
        this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {
            Intent intent = new Intent(getApplicationContext(), DebugActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("error", getStackTrace(ex));
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 11111, intent, PendingIntent.FLAG_ONE_SHOT);

            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(2);

            uncaughtExceptionHandler.uncaughtException(thread, ex);
        });
        super.onCreate();
        applicationContext = this;
        InfoUtilities.fillStatusBarHeight(applicationContext);
        applicationHandler = new Handler(applicationContext.getMainLooper());
    }

    private String getStackTrace(Throwable th){
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        Throwable cause = th;
        while(cause != null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        final String stacktraceAsString = result.toString();
        printWriter.close();
        return stacktraceAsString;
    }

}