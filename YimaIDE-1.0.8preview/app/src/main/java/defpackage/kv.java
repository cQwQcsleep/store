package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class kv {
    public static final kv a = new kv();

    public static final class a {
        public final Bitmap a;
        public final Bitmap b;

        public a(Bitmap bitmap, Bitmap bitmap2) {
            bitmap.getClass();
            bitmap2.getClass();
            this.a = bitmap;
            this.b = bitmap2;
        }

        public final Bitmap a() {
            return this.b;
        }

        public final Bitmap b() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b);
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }

        public String toString() {
            return "Layers(foreground=" + this.a + ", background=" + this.b + ")";
        }
    }

    public final a a(Bitmap bitmap) {
        Bitmap bitmapCreateBitmap;
        bitmap.getClass();
        int iCoerceAtLeast = RangesKt.coerceAtLeast(Math.min(bitmap.getWidth(), bitmap.getHeight()), 1);
        if (bitmap.getWidth() == iCoerceAtLeast && bitmap.getHeight() == iCoerceAtLeast) {
            bitmapCreateBitmap = bitmap;
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap, (bitmap.getWidth() - iCoerceAtLeast) / 2, (bitmap.getHeight() - iCoerceAtLeast) / 2, iCoerceAtLeast, iCoerceAtLeast);
            bitmapCreateBitmap.getClass();
        }
        int iC = c(bitmapCreateBitmap);
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iCoerceAtLeast, iCoerceAtLeast, config);
        new Canvas(bitmapCreateBitmap2).drawColor(iC);
        bitmapCreateBitmap2.getClass();
        int iCoerceIn = RangesKt.coerceIn((int) (iCoerceAtLeast * 0.6666667f), 1, iCoerceAtLeast);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateBitmap, iCoerceIn, iCoerceIn, true);
        bitmapCreateScaledBitmap.getClass();
        Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(iCoerceAtLeast, iCoerceAtLeast, config);
        Canvas canvas = new Canvas(bitmapCreateBitmap3);
        canvas.drawColor(0);
        float f = (iCoerceAtLeast - iCoerceIn) / 2.0f;
        canvas.drawBitmap(bitmapCreateScaledBitmap, f, f, new Paint(2));
        bitmapCreateBitmap3.getClass();
        if (!Intrinsics.areEqual(bitmapCreateScaledBitmap, bitmapCreateBitmap)) {
            bitmapCreateScaledBitmap.recycle();
        }
        if (!Intrinsics.areEqual(bitmapCreateBitmap, bitmap)) {
            bitmapCreateBitmap.recycle();
        }
        return new a(bitmapCreateBitmap3, bitmapCreateBitmap2);
    }

    public final Bitmap b(File file) {
        file.getClass();
        if (!file.isFile()) {
            return null;
        }
        try {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        } catch (Exception unused) {
            return null;
        }
    }

    public final int c(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return -1;
        }
        int i = width - 1;
        int i2 = height - 1;
        int i3 = width / 2;
        int i4 = height / 2;
        int[] iArr = {bitmap.getPixel(0, 0), bitmap.getPixel(i, 0), bitmap.getPixel(0, i2), bitmap.getPixel(i, i2), bitmap.getPixel(i3, 0), bitmap.getPixel(i3, i2), bitmap.getPixel(0, i4), bitmap.getPixel(i, i4)};
        int iRed = 0;
        int iGreen = 0;
        int iBlue = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = iArr[i5];
            iRed += Color.red(i6);
            iGreen += Color.green(i6);
            iBlue += Color.blue(i6);
        }
        return Color.rgb(iRed / 8, iGreen / 8, iBlue / 8);
    }

    public final boolean d(Bitmap bitmap, File file) {
        bitmap.getClass();
        file.getClass();
        try {
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                return true;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileOutputStream, th);
                    throw th2;
                }
            }
        } catch (Exception unused) {
            return false;
        }
    }
}
