package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.PointF;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u001a\u001d\u0010\u0007\u001a\u00020\b*\u00020\u00012\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0086\n\u001a'\u0010\u000b\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\b\b\u0001\u0010\f\u001a\u00020\bH\u0086\n\u001a'\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0086\b\u001a#\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0086\b\u001a7\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0087\b\u001a\u0015\u0010\u0018\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001aH\u0086\n\u001a\u0015\u0010\u0018\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001bH\u0086\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001c"}, d2 = {"applyCanvas", "Landroid/graphics/Bitmap;", "block", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "", "Lkotlin/ExtensionFunctionType;", "get", "", "x", "y", "set", "color", "scale", "width", "height", "filter", "", "createBitmap", "config", "Landroid/graphics/Bitmap$Config;", "hasAlpha", "colorSpace", "Landroid/graphics/ColorSpace;", "contains", "p", "Landroid/graphics/Point;", "Landroid/graphics/PointF;", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BitmapKt {
    public static final Bitmap applyCanvas(Bitmap bitmap, Function1<? super Canvas, Unit> function1) {
        bitmap.getClass();
        function1.getClass();
        function1.invoke(new Canvas(bitmap));
        return bitmap;
    }

    public static final boolean contains(Bitmap bitmap, PointF pointF) {
        bitmap.getClass();
        pointF.getClass();
        float f = pointF.x;
        if (f < 0.0f || f >= bitmap.getWidth()) {
            return false;
        }
        float f2 = pointF.y;
        return f2 >= 0.0f && f2 < ((float) bitmap.getHeight());
    }

    public static final Bitmap createBitmap(int i, int i2, Bitmap.Config config, boolean z, ColorSpace colorSpace) {
        config.getClass();
        colorSpace.getClass();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, config, z, colorSpace);
        bitmapCreateBitmap.getClass();
        return bitmapCreateBitmap;
    }

    public static /* synthetic */ Bitmap createBitmap$default(int i, int i2, Bitmap.Config config, boolean z, ColorSpace colorSpace, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            config = Bitmap.Config.ARGB_8888;
        }
        if ((i3 & 8) != 0) {
            z = true;
        }
        if ((i3 & 16) != 0) {
            colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
            colorSpace.getClass();
        }
        config.getClass();
        colorSpace.getClass();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, config, z, colorSpace);
        bitmapCreateBitmap.getClass();
        return bitmapCreateBitmap;
    }

    public static final int get(Bitmap bitmap, int i, int i2) {
        bitmap.getClass();
        return bitmap.getPixel(i, i2);
    }

    public static final Bitmap scale(Bitmap bitmap, int i, int i2, boolean z) {
        bitmap.getClass();
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, z);
        bitmapCreateScaledBitmap.getClass();
        return bitmapCreateScaledBitmap;
    }

    public static /* synthetic */ Bitmap scale$default(Bitmap bitmap, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            z = true;
        }
        bitmap.getClass();
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, z);
        bitmapCreateScaledBitmap.getClass();
        return bitmapCreateScaledBitmap;
    }

    public static final void set(Bitmap bitmap, int i, int i2, int i3) {
        bitmap.getClass();
        bitmap.setPixel(i, i2, i3);
    }

    public static final Bitmap createBitmap(int i, int i2, Bitmap.Config config) {
        config.getClass();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, config);
        bitmapCreateBitmap.getClass();
        return bitmapCreateBitmap;
    }

    public static /* synthetic */ Bitmap createBitmap$default(int i, int i2, Bitmap.Config config, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            config = Bitmap.Config.ARGB_8888;
        }
        config.getClass();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, config);
        bitmapCreateBitmap.getClass();
        return bitmapCreateBitmap;
    }

    public static final boolean contains(Bitmap bitmap, Point point) {
        int i;
        bitmap.getClass();
        point.getClass();
        int width = bitmap.getWidth();
        int i2 = point.x;
        return i2 >= 0 && i2 < width && (i = point.y) >= 0 && i < bitmap.getHeight();
    }
}
