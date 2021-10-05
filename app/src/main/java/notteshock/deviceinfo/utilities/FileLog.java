/*
 * This is the source code of 7 Device Info.
 * It is licensed under the The GNU General Public License v3.0.
 * You should have received a copy of the license in this repo (see LICENSE).
 *
 * Copyright lahds13, 2021.
 */

package notteshock.deviceinfo.utilities;

import android.util.Log;

public class FileLog {
    public static void e(StackTraceElement e){
        Log.e("TelegramBot", e.toString());
    }
    public static void d(StackTraceElement e){
        Log.e("TelegramBot", e.toString());
    }
    public static void d(String e){
        Log.e("TelegramBot", e);
    }

    public static void e(Exception e) {
        Log.e("TelegramBot", e.toString());
    }

    public static void e(String toString) {
        Log.e("TelegramBot", toString);
    }
}
