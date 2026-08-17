package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class BitmapCompat {

    public static class Api27Impl {
        private Api27Impl() {
        }

        public static Bitmap copyBitmapIfHardware(Bitmap bitmap) {
            if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
                return bitmap;
            }
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            return bitmap.copy(Api31Impl.getHardwareBitmapConfig(bitmap), true);
        }

        public static Bitmap createBitmapWithSourceColorspace(int i, int i2, Bitmap bitmap, boolean z) {
            Bitmap.Config config = bitmap.getConfig();
            ColorSpace colorSpace = bitmap.getColorSpace();
            ColorSpace colorSpace2 = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
            if (z && !bitmap.getColorSpace().equals(colorSpace2)) {
                config = Bitmap.Config.RGBA_F16;
                colorSpace = colorSpace2;
            } else if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
                config = Api31Impl.getHardwareBitmapConfig(bitmap);
            }
            return Bitmap.createBitmap(i, i2, config, bitmap.hasAlpha(), colorSpace);
        }

        public static boolean isAlreadyF16AndLinear(Bitmap bitmap) {
            return bitmap.getConfig() == Bitmap.Config.RGBA_F16 && bitmap.getColorSpace().equals(ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB));
        }
    }

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static void setPaintBlendMode(Paint paint) {
            paint.setBlendMode(BlendMode.SRC);
        }
    }

    public static class Api31Impl {
        private Api31Impl() {
        }

        public static Bitmap.Config getHardwareBitmapConfig(Bitmap bitmap) {
            return bitmap.getHardwareBuffer().getFormat() == 22 ? Bitmap.Config.RGBA_F16 : Bitmap.Config.ARGB_8888;
        }
    }

    private BitmapCompat() {
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap, int i, int i2, Rect rect, boolean z) {
        int i3;
        Bitmap bitmapCreateBitmapWithSourceColorspace;
        Bitmap bitmapCreateBitmapWithSourceColorspace2;
        if (i <= 0 || i2 <= 0) {
            w01.a("dstW and dstH must be > 0!");
            return null;
        }
        if (rect != null && (rect.isEmpty() || rect.left < 0 || rect.right > bitmap.getWidth() || rect.top < 0 || rect.bottom > bitmap.getHeight())) {
            w01.a("srcRect must be contained by srcBm!");
            return null;
        }
        Bitmap bitmapCopyBitmapIfHardware = Api27Impl.copyBitmapIfHardware(bitmap);
        int iWidth = rect != null ? rect.width() : bitmap.getWidth();
        int iHeight = rect != null ? rect.height() : bitmap.getHeight();
        float f = i / iWidth;
        float f2 = i2 / iHeight;
        int i4 = rect != null ? rect.left : 0;
        int i5 = rect != null ? rect.top : 0;
        if (i4 == 0 && i5 == 0 && i == bitmap.getWidth() && i2 == bitmap.getHeight()) {
            return (bitmap.isMutable() && bitmap == bitmapCopyBitmapIfHardware) ? bitmap.copy(bitmap.getConfig(), true) : bitmapCopyBitmapIfHardware;
        }
        Paint paint = new Paint(1);
        paint.setFilterBitmap(true);
        Api29Impl.setPaintBlendMode(paint);
        if (iWidth == i && iHeight == i2) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, bitmapCopyBitmapIfHardware.getConfig());
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmapCopyBitmapIfHardware, -i4, -i5, paint);
            return bitmapCreateBitmap;
        }
        double dLog = Math.log(2.0d);
        int iCeil = (int) (f > 1.0f ? Math.ceil(Math.log(f) / dLog) : Math.floor(Math.log(f) / dLog));
        int iCeil2 = (int) (f2 > 1.0f ? Math.ceil(Math.log(f2) / dLog) : Math.floor(Math.log(f2) / dLog));
        if (!z || Api27Impl.isAlreadyF16AndLinear(bitmap)) {
            i3 = 0;
            bitmapCreateBitmapWithSourceColorspace = bitmapCopyBitmapIfHardware;
            bitmapCreateBitmapWithSourceColorspace2 = null;
        } else {
            bitmapCreateBitmapWithSourceColorspace = Api27Impl.createBitmapWithSourceColorspace(iCeil > 0 ? sizeAtStep(iWidth, i, 1, iCeil) : iWidth, iCeil2 > 0 ? sizeAtStep(iHeight, i2, 1, iCeil2) : iHeight, bitmap, true);
            bitmapCreateBitmapWithSourceColorspace2 = bitmapCopyBitmapIfHardware;
            new Canvas(bitmapCreateBitmapWithSourceColorspace).drawBitmap(bitmapCreateBitmapWithSourceColorspace2, -i4, -i5, paint);
            i5 = 0;
            i4 = 0;
            i3 = 1;
        }
        Rect rect2 = new Rect(i4, i5, iWidth, iHeight);
        Rect rect3 = new Rect();
        int i6 = iCeil;
        int i7 = iCeil2;
        while (true) {
            if (i6 == 0 && i7 == 0) {
                break;
            }
            if (i6 < 0) {
                i6++;
            } else if (i6 > 0) {
                i6--;
            }
            if (i7 < 0) {
                i7++;
            } else if (i7 > 0) {
                i7--;
            }
            int i8 = i7;
            int i9 = i3;
            int i10 = i6;
            rect3.set(0, 0, sizeAtStep(iWidth, i, i6, iCeil), sizeAtStep(iHeight, i2, i8, iCeil2));
            boolean z2 = i10 == 0 && i8 == 0;
            boolean z3 = bitmapCreateBitmapWithSourceColorspace2 != null && bitmapCreateBitmapWithSourceColorspace2.getWidth() == i && bitmapCreateBitmapWithSourceColorspace2.getHeight() == i2;
            if (bitmapCreateBitmapWithSourceColorspace2 == null || bitmapCreateBitmapWithSourceColorspace2 == bitmap || ((z && !Api27Impl.isAlreadyF16AndLinear(bitmapCreateBitmapWithSourceColorspace2)) || (z2 && (!z3 || i9 != 0)))) {
                if (bitmapCreateBitmapWithSourceColorspace2 != bitmap && bitmapCreateBitmapWithSourceColorspace2 != null) {
                    bitmapCreateBitmapWithSourceColorspace2.recycle();
                }
                bitmapCreateBitmapWithSourceColorspace2 = Api27Impl.createBitmapWithSourceColorspace(sizeAtStep(iWidth, i, i10 > 0 ? i9 : i10, iCeil), sizeAtStep(iHeight, i2, i8 > 0 ? i9 : i8, iCeil2), bitmap, z && !z2);
            }
            new Canvas(bitmapCreateBitmapWithSourceColorspace2).drawBitmap(bitmapCreateBitmapWithSourceColorspace, rect2, rect3, paint);
            rect2.set(rect3);
            Bitmap bitmap2 = bitmapCreateBitmapWithSourceColorspace2;
            bitmapCreateBitmapWithSourceColorspace2 = bitmapCreateBitmapWithSourceColorspace;
            bitmapCreateBitmapWithSourceColorspace = bitmap2;
            i3 = i9;
            i6 = i10;
            i7 = i8;
        }
        if (bitmapCreateBitmapWithSourceColorspace2 != bitmap && bitmapCreateBitmapWithSourceColorspace2 != null) {
            bitmapCreateBitmapWithSourceColorspace2.recycle();
        }
        return bitmapCreateBitmapWithSourceColorspace;
    }

    @ReplaceWith(expression = "bitmap.getAllocationByteCount()")
    @Deprecated
    public static int getAllocationByteCount(Bitmap bitmap) {
        return bitmap.getAllocationByteCount();
    }

    @ReplaceWith(expression = "bitmap.hasMipMap()")
    @Deprecated
    public static boolean hasMipMap(Bitmap bitmap) {
        return bitmap.hasMipMap();
    }

    @ReplaceWith(expression = "bitmap.setHasMipMap(hasMipMap)")
    @Deprecated
    public static void setHasMipMap(Bitmap bitmap, boolean z) {
        bitmap.setHasMipMap(z);
    }

    public static int sizeAtStep(int i, int i2, int i3, int i4) {
        if (i3 == 0) {
            return i2;
        }
        return i3 > 0 ? i * (1 << (i4 - i3)) : i2 << ((-i3) - 1);
    }
}
