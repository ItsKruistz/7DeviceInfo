/*
 * This is the source code of 7 Device Info.
 * It is licensed under the The GNU General Public License v3.0.
 * You should have received a copy of the license in this repo (see LICENSE).
 *
 * Copyright lahds13, 2021.
 */

package notteshock.deviceinfo.components;

import android.graphics.drawable.Drawable;

public abstract class StatusDrawable extends Drawable {
    public abstract void start();
    public abstract void stop();
    public abstract void setIsChat(boolean value);
    public abstract void setColor(int color);
}
