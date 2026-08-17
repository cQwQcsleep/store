package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class nh1 {
    public static final nh1 a = new nh1();

    public static void a(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        imageDecoder.getClass();
        imageInfo.getClass();
        source.getClass();
        imageDecoder.setMutableRequired(true);
        int width = imageInfo.getSize().getWidth();
        int height = imageInfo.getSize().getHeight();
        int iMax = Math.max(width, height);
        if (iMax > 1920) {
            float f = 1920.0f / iMax;
            imageDecoder.setTargetSize(RangesKt.coerceAtLeast((int) (width * f), 1), RangesKt.coerceAtLeast((int) (height * f), 1));
        }
    }

    public final Object b(Context context, Uri uri, File file) {
        Bitmap bitmapC = c(context, uri);
        if (bitmapC == null) {
            Result.Companion companion = Result.Companion;
            return Result.constructor-impl(ResultKt.createFailure(new IllegalArgumentException("无法读取图片")));
        }
        Bitmap bitmapG = g(bitmapC);
        try {
            int[] iArr = {85, 70, 55, 40};
            for (int i = 0; i < 4; i++) {
                int i2 = iArr[i];
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    if (!bitmapG.compress(Bitmap.CompressFormat.JPEG, i2, fileOutputStream)) {
                        Result.Companion companion2 = Result.Companion;
                        Object obj = Result.constructor-impl(ResultKt.createFailure(new IllegalStateException("图片处理失败")));
                        CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                        bitmapG.recycle();
                        return obj;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                    if (file.length() <= 512000) {
                        Result.Companion companion3 = Result.Companion;
                        Object obj2 = Result.constructor-impl(Unit.INSTANCE);
                        bitmapG.recycle();
                        return obj2;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileOutputStream, th);
                        throw th2;
                    }
                }
                bitmapG.recycle();
                throw th;
            }
            Result.Companion companion4 = Result.Companion;
            Object obj3 = Result.constructor-impl(ResultKt.createFailure(new IllegalArgumentException("图片不能超过 500KB，请换一张或截图后重试")));
            bitmapG.recycle();
            return obj3;
        } catch (Throwable th3) {
            bitmapG.recycle();
            throw th3;
        }
    }

    public final Bitmap c(Context context, Uri uri) {
        Object obj;
        Bitmap bitmapDecodeStream;
        Bitmap bitmapD = d(context, uri);
        if (bitmapD != null) {
            return bitmapD;
        }
        try {
            Result.Companion companion = Result.Companion;
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream != null) {
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    int i = 1;
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
                    if (options.outWidth <= 0 || options.outHeight <= 0) {
                        CloseableKt.closeFinally(inputStreamOpenInputStream, (Throwable) null);
                        bitmapDecodeStream = null;
                    } else {
                        while (true) {
                            int i2 = i * 2;
                            if (options.outWidth / i2 < 1920 || options.outHeight / i2 < 1920) {
                                break;
                            }
                            i = i2;
                        }
                        InputStream inputStreamOpenInputStream2 = context.getContentResolver().openInputStream(uri);
                        if (inputStreamOpenInputStream2 != null) {
                            try {
                                BitmapFactory.Options options2 = new BitmapFactory.Options();
                                options2.inSampleSize = i;
                                Unit unit = Unit.INSTANCE;
                                bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream2, null, options2);
                                CloseableKt.closeFinally(inputStreamOpenInputStream2, (Throwable) null);
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    CloseableKt.closeFinally(inputStreamOpenInputStream2, th);
                                    throw th2;
                                }
                            }
                        } else {
                            bitmapDecodeStream = null;
                        }
                        CloseableKt.closeFinally(inputStreamOpenInputStream, (Throwable) null);
                    }
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(inputStreamOpenInputStream, th3);
                        throw th4;
                    }
                }
            } else {
                bitmapDecodeStream = null;
            }
            obj = Result.constructor-impl(bitmapDecodeStream);
        } catch (Throwable th5) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th5));
        }
        return (Bitmap) (Result.isFailure-impl(obj) ? null : obj);
    }

    public final Bitmap d(Context context, Uri uri) {
        try {
            ImageDecoder.Source sourceCreateSource = ImageDecoder.createSource(context.getContentResolver(), uri);
            sourceCreateSource.getClass();
            return ImageDecoder.decodeBitmap(sourceCreateSource, new ImageDecoder.OnHeaderDecodedListener() { // from class: mh1
                @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                    nh1.a(imageDecoder, imageInfo, source);
                }
            });
        } catch (Exception e) {
            Log.w("ChatImageAttachments", "ImageDecoder failed: " + uri, e);
            return null;
        }
    }

    public final void e(String str) {
        str.getClass();
        try {
            Result.Companion companion = Result.Companion;
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (Intrinsics.areEqual(parentFile != null ? parentFile.getName() : null, "chat_images")) {
                file.delete();
            }
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public final void f(String str) {
        str.getClass();
        try {
            Result.Companion companion = Result.Companion;
            Result.constructor-impl(Boolean.valueOf(new File(str).delete()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public final Bitmap g(Bitmap bitmap) {
        if (bitmap.getWidth() <= 1920 && bitmap.getHeight() <= 1920) {
            return bitmap;
        }
        float fMin = Math.min(1920.0f / bitmap.getWidth(), 1920.0f / bitmap.getHeight());
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, RangesKt.coerceAtLeast((int) (bitmap.getWidth() * fMin), 1), RangesKt.coerceAtLeast((int) (bitmap.getHeight() * fMin), 1), true);
        bitmapCreateScaledBitmap.getClass();
        if (!Intrinsics.areEqual(bitmapCreateScaledBitmap, bitmap)) {
            bitmap.recycle();
        }
        return bitmapCreateScaledBitmap;
    }

    public final Object h(Context context, Uri uri) {
        context.getClass();
        uri.getClass();
        File file = new File(context.getApplicationContext().getFilesDir(), "chat_images");
        file.mkdirs();
        File file2 = new File(file, "img_" + System.currentTimeMillis() + "_" + uri.hashCode() + ".jpg");
        Object objB = b(context, uri, file2);
        if (!Result.isSuccess-impl(objB)) {
            return Result.constructor-impl(objB);
        }
        return Result.constructor-impl(file2.getAbsolutePath());
    }

    public final String i(String str) {
        Object obj;
        str.getClass();
        File file = new File(str);
        if (!file.isFile() || file.length() <= 0 || file.length() > 512000) {
            return null;
        }
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl("data:image/jpeg;base64," + Base64.encodeToString(FilesKt.readBytes(file), 2));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        return (String) (Result.isFailure-impl(obj) ? null : obj);
    }
}
