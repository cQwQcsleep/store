package androidx.core.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class DisplayManagerCompat {
    static final String DISPLAY_CATEGORY_ALL = "android.hardware.display.category.ALL_INCLUDING_DISABLED";
    public static final String DISPLAY_CATEGORY_BUILT_IN_DISPLAYS = "android.hardware.display.category.BUILT_IN_DISPLAYS";
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    static final int DISPLAY_TYPE_INTERNAL = 1;
    private final Context mContext;

    private DisplayManagerCompat(Context context) {
        this.mContext = context;
    }

    private static Display[] computeBuiltInDisplays(DisplayManager displayManager) {
        Display[] displays = displayManager.getDisplays(DISPLAY_CATEGORY_ALL);
        Display[] displayArr = new Display[numberOfDisplaysByType(1, displays)];
        int i = 0;
        for (Display display : displays) {
            if (1 == getTypeCompat(display)) {
                displayArr[i] = display;
                i++;
            }
        }
        return displayArr;
    }

    public static DisplayManagerCompat getInstance(Context context) {
        return new DisplayManagerCompat(context);
    }

    public static int getTypeCompat(Display display) {
        try {
            Object objInvoke = Display.class.getMethod("getType", null).invoke(display, null);
            Objects.requireNonNull(objInvoke);
            return ((Integer) objInvoke).intValue();
        } catch (NoSuchMethodException unused) {
            return 0;
        } catch (Exception e) {
            rc6.a(e);
            return 0;
        }
    }

    private static int numberOfDisplaysByType(int i, Display[] displayArr) {
        int i2 = 0;
        for (Display display : displayArr) {
            if (i == getTypeCompat(display)) {
                i2++;
            }
        }
        return i2;
    }

    public Display getDisplay(int i) {
        return ((DisplayManager) this.mContext.getSystemService("display")).getDisplay(i);
    }

    public Display[] getDisplays(String str) {
        return DISPLAY_CATEGORY_BUILT_IN_DISPLAYS.equals(str) ? computeBuiltInDisplays((DisplayManager) this.mContext.getSystemService("display")) : ((DisplayManager) this.mContext.getSystemService("display")).getDisplays(str);
    }

    public Display[] getDisplays() {
        return ((DisplayManager) this.mContext.getSystemService("display")).getDisplays();
    }
}
