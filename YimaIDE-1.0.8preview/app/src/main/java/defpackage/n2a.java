package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class n2a {
    public static final n2a a = new n2a();
    public static final MediaType b = MediaType.Companion.get("image/jpeg");
    public static final OkHttpClient c;
    public static final int d;

    public interface a {

        /* JADX INFO: renamed from: n2a$a$a, reason: collision with other inner class name */
        public static final class C0011a implements a {
            public final int a;
            public final String b;

            public C0011a(int i, String str) {
                str.getClass();
                this.a = i;
                this.b = str;
            }

            public final String a() {
                return this.b;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof C0011a)) {
                    return false;
                }
                C0011a c0011a = (C0011a) obj;
                return this.a == c0011a.a && Intrinsics.areEqual(this.b, c0011a.b);
            }

            public int hashCode() {
                return (Integer.hashCode(this.a) * 31) + this.b.hashCode();
            }

            public String toString() {
                return "Err(code=" + this.a + ", message=" + this.b + ")";
            }
        }

        public static final class b implements a {
            public final Object a;

            public b(Object obj) {
                this.a = obj;
            }

            public final Object a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof b) && Intrinsics.areEqual(this.a, ((b) obj).a);
            }

            public int hashCode() {
                Object obj = this.a;
                if (obj == null) {
                    return 0;
                }
                return obj.hashCode();
            }

            public String toString() {
                return "Ok(data=" + this.a + ")";
            }
        }
    }

    static {
        OkHttpClient.Builder builderNewBuilder = xl0.a.a().newBuilder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        c = builderNewBuilder.readTimeout(60L, timeUnit).writeTimeout(60L, timeUnit).build();
        d = 8;
    }

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

    /* JADX WARN: Code duplicated, block: B:60:0x00dd A[RETURN] */
    public final File b(Context context, Uri uri) throws IOException {
        File fileCreateTempFile = File.createTempFile("fb_src_", ".img", context.getCacheDir());
        try {
            ParcelFileDescriptor parcelFileDescriptorI = i(context, uri);
            if (parcelFileDescriptorI != null) {
                try {
                    long statSize = parcelFileDescriptorI.getStatSize();
                    if (statSize > 20971520) {
                        Log.w("MiscApi", "反馈图片源文件过大（" + statSize + " 字节 > 20971520），拒绝复制: " + uri);
                        fileCreateTempFile.delete();
                        CloseableKt.closeFinally(parcelFileDescriptorI, (Throwable) null);
                        return null;
                    }
                    FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorI.getFileDescriptor());
                    try {
                        fileCreateTempFile.getClass();
                        FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                        try {
                            ByteStreamsKt.copyTo$default(fileInputStream, fileOutputStream, 0, 2, (Object) null);
                            CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                            CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                            CloseableKt.closeFinally(parcelFileDescriptorI, (Throwable) null);
                            if (fileCreateTempFile.length() > 0) {
                                return fileCreateTempFile;
                            }
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(fileOutputStream, th);
                                throw th2;
                            }
                        }
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(fileInputStream, th3);
                            throw th4;
                        }
                    }
                } catch (Throwable th5) {
                    try {
                        throw th5;
                    } catch (Throwable th6) {
                        CloseableKt.closeFinally(parcelFileDescriptorI, th5);
                        throw th6;
                    }
                }
            } else {
                InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                if (inputStreamOpenInputStream != null) {
                    try {
                        Ref.LongRef longRef = new Ref.LongRef();
                        fileCreateTempFile.getClass();
                        FileOutputStream fileOutputStream2 = new FileOutputStream(fileCreateTempFile);
                        try {
                            byte[] bArr = new byte[65536];
                            while (true) {
                                int i = inputStreamOpenInputStream.read(bArr);
                                if (i < 0) {
                                    Unit unit = Unit.INSTANCE;
                                    CloseableKt.closeFinally(fileOutputStream2, (Throwable) null);
                                    CloseableKt.closeFinally(inputStreamOpenInputStream, (Throwable) null);
                                    break;
                                }
                                long j = longRef.element + ((long) i);
                                longRef.element = j;
                                if (j > 20971520) {
                                    Log.w("MiscApi", "反馈图片源文件过大（超过 20971520 字节），拒绝复制: " + uri);
                                    fileCreateTempFile.delete();
                                    CloseableKt.closeFinally(fileOutputStream2, (Throwable) null);
                                    CloseableKt.closeFinally(inputStreamOpenInputStream, (Throwable) null);
                                    return null;
                                }
                                fileOutputStream2.write(bArr, 0, i);
                            }
                            if (fileCreateTempFile.length() > 0) {
                                return fileCreateTempFile;
                            }
                        } catch (Throwable th7) {
                            try {
                                throw th7;
                            } catch (Throwable th8) {
                                CloseableKt.closeFinally(fileOutputStream2, th7);
                                throw th8;
                            }
                        }
                    } catch (Throwable th9) {
                        try {
                            throw th9;
                        } catch (Throwable th10) {
                            CloseableKt.closeFinally(inputStreamOpenInputStream, th9);
                            throw th10;
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        fileCreateTempFile.delete();
        return null;
    }

    public final Bitmap c(Context context, Uri uri) throws IOException {
        Bitmap bitmapF = f(context, uri);
        if (bitmapF != null) {
            return bitmapF;
        }
        Bitmap bitmapE = e(context, uri);
        if (bitmapE != null) {
            return bitmapE;
        }
        File fileB = b(context, uri);
        if (fileB == null) {
            return null;
        }
        try {
            Uri uriFromFile = Uri.fromFile(fileB);
            uriFromFile.getClass();
            Bitmap bitmapE2 = e(context, uriFromFile);
            if (bitmapE2 == null) {
                bitmapE2 = BitmapFactory.decodeFile(fileB.getAbsolutePath());
            }
            return bitmapE2;
        } finally {
            fileB.delete();
        }
    }

    public final boolean d(Context context, Uri uri, BitmapFactory.Options options) {
        ParcelFileDescriptor parcelFileDescriptorI = i(context, uri);
        boolean z = false;
        if (parcelFileDescriptorI == null) {
            return false;
        }
        try {
            BitmapFactory.decodeFileDescriptor(parcelFileDescriptorI.getFileDescriptor(), null, options);
            if (options.outWidth > 0 && options.outHeight > 0) {
                z = true;
            }
            CloseableKt.closeFinally(parcelFileDescriptorI, (Throwable) null);
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(parcelFileDescriptorI, th);
                throw th2;
            }
        }
    }

    public final Bitmap e(Context context, Uri uri) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        if (d(context, uri, options) && options.outWidth > 0 && options.outHeight > 0) {
            while (true) {
                int i2 = i * 2;
                if (options.outWidth / i2 < 1920 || options.outHeight / i2 < 1920) {
                    break;
                }
                i = i2;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i;
            ParcelFileDescriptor parcelFileDescriptorI = i(context, uri);
            if (parcelFileDescriptorI != null) {
                try {
                    Bitmap bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(parcelFileDescriptorI.getFileDescriptor(), null, options2);
                    CloseableKt.closeFinally(parcelFileDescriptorI, (Throwable) null);
                    return bitmapDecodeFileDescriptor;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(parcelFileDescriptorI, th);
                        throw th2;
                    }
                }
            }
        }
        return null;
    }

    public final Bitmap f(Context context, Uri uri) {
        try {
            ImageDecoder.Source sourceCreateSource = ImageDecoder.createSource(context.getContentResolver(), uri);
            sourceCreateSource.getClass();
            return ImageDecoder.decodeBitmap(sourceCreateSource, new ImageDecoder.OnHeaderDecodedListener() { // from class: m2a
                @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                    n2a.a(imageDecoder, imageInfo, source);
                }
            });
        } catch (Exception unused) {
            return null;
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

    public final a h(String str) {
        a c0011a;
        Object obj;
        int iOptInt;
        String strOptString;
        JSONObject jSONObject;
        str.getClass();
        try {
            Response responseExecute = c.newCall(new Request.Builder().url(xl0.a.b("/v1/misc/support")).header("Authorization", "Bearer " + str).get().build()).execute();
            try {
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                if (strString == null) {
                    strString = "";
                }
                try {
                    Result.Companion companion = Result.Companion;
                    obj = Result.constructor-impl(new JSONObject(strString));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (Result.isFailure-impl(obj)) {
                    obj = null;
                }
                JSONObject jSONObject2 = (JSONObject) obj;
                if (jSONObject2 != null) {
                    iOptInt = jSONObject2.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                } else {
                    iOptInt = -1;
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    if (jSONObject2 == null || (jSONObject = jSONObject2.optJSONObject("data")) == null) {
                        jSONObject = new JSONObject();
                    }
                    String strOptString2 = jSONObject.optString("qq", "");
                    strOptString2.getClass();
                    String strOptString3 = jSONObject.optString("wechat", "");
                    strOptString3.getClass();
                    c0011a = new a.b(new b(strOptString2, strOptString3));
                } else {
                    if (jSONObject2 == null || (strOptString = jSONObject2.optString(Constants.ELEMNAME_MESSAGE_STRING)) == null) {
                        strOptString = "获取客服信息失败";
                    }
                    c0011a = new a.C0011a(iOptInt, strOptString);
                }
                CloseableKt.closeFinally(responseExecute, (Throwable) null);
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(responseExecute, th2);
                    throw th3;
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "网络错误";
            }
            c0011a = new a.C0011a(-1, message);
        }
        return c0011a;
    }

    public final ParcelFileDescriptor i(Context context, Uri uri) {
        try {
            return context.getContentResolver().openFileDescriptor(uri, "r");
        } catch (Exception unused) {
            return null;
        }
    }

    public final a j(Context context, Uri uri, File file) throws IOException {
        Bitmap bitmapC = c(context, uri);
        if (bitmapC == null) {
            return new a.C0011a(-1, "无法读取图片，请换一张 jpg/png 截图试试");
        }
        Bitmap bitmapG = g(bitmapC);
        try {
            int[] iArr = {85, 70, 55, 40};
            for (int i = 0; i < 4; i++) {
                int i2 = iArr[i];
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    if (!bitmapG.compress(Bitmap.CompressFormat.JPEG, i2, fileOutputStream)) {
                        a.C0011a c0011a = new a.C0011a(-1, "图片处理失败");
                        CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                        bitmapG.recycle();
                        return c0011a;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                    if (file.length() <= 512000) {
                        a.b bVar = new a.b(Unit.INSTANCE);
                        bitmapG.recycle();
                        return bVar;
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
            a.C0011a c0011a2 = new a.C0011a(-1, "图片不能超过 500KB");
            bitmapG.recycle();
            return c0011a2;
        } catch (Throwable th3) {
            bitmapG.recycle();
            throw th3;
        }
    }

    public final a k(String str, Context context, String str2, List list) {
        a c0011a;
        Object obj;
        int iOptInt;
        String strOptString;
        str.getClass();
        context.getClass();
        str2.getClass();
        list.getClass();
        if (list.size() > 6) {
            return new a.C0011a(-1, "最多上传 6 张图片");
        }
        ArrayList arrayList = new ArrayList();
        try {
            try {
                MultipartBody.Builder builderAddFormDataPart = new MultipartBody.Builder((String) null, 1, (DefaultConstructorMarker) null).setType(MultipartBody.FORM).addFormDataPart("content", str2);
                int i = 0;
                int i2 = 0;
                for (Object obj2 : list) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    File fileCreateTempFile = File.createTempFile("fb_", ".jpg", context.getCacheDir());
                    fileCreateTempFile.getClass();
                    arrayList.add(fileCreateTempFile);
                    a aVarJ = a.j(context, (Uri) obj2, fileCreateTempFile);
                    if (aVarJ instanceof a.C0011a) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            ((File) it.next()).delete();
                        }
                        return aVarJ;
                    }
                    if (!(aVarJ instanceof a.b)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    if (fileCreateTempFile.length() > 512000) {
                        a.C0011a c0011a2 = new a.C0011a(-1, "图片不能超过 500KB");
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            ((File) it2.next()).delete();
                        }
                        return c0011a2;
                    }
                    builderAddFormDataPart.addFormDataPart("files", "feedback_" + i3 + ".jpg", RequestBody.Companion.create(fileCreateTempFile, b));
                    i2 = i3;
                }
                Response responseExecute = c.newCall(new Request.Builder().url(xl0.a.b("/v1/misc/feedback")).header("Authorization", "Bearer " + str).post(builderAddFormDataPart.build()).build()).execute();
                try {
                    ResponseBody responseBodyBody = responseExecute.body();
                    String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                    if (strString == null) {
                        strString = "";
                    }
                    try {
                        Result.Companion companion = Result.Companion;
                        obj = Result.constructor-impl(new JSONObject(strString));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.Companion;
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                    if (Result.isFailure-impl(obj)) {
                        obj = null;
                    }
                    JSONObject jSONObject = (JSONObject) obj;
                    if (jSONObject != null) {
                        if (!responseExecute.isSuccessful()) {
                            i = -1;
                        }
                        iOptInt = jSONObject.optInt("code", i);
                    } else {
                        iOptInt = -1;
                    }
                    if (responseExecute.isSuccessful() && iOptInt == 0) {
                        c0011a = new a.b(Unit.INSTANCE);
                    } else {
                        if (jSONObject == null || (strOptString = jSONObject.optString(Constants.ELEMNAME_MESSAGE_STRING)) == null || StringsKt.isBlank(strOptString)) {
                            strOptString = null;
                        }
                        if (responseExecute.code() == 413) {
                            strOptString = "提交内容过大，请减少图片数量或换小图后重试";
                        }
                        if (strOptString == null) {
                            strOptString = "提交失败（HTTP " + responseExecute.code() + "）";
                        }
                        c0011a = new a.C0011a(iOptInt, strOptString);
                    }
                    CloseableKt.closeFinally(responseExecute, (Throwable) null);
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        ((File) it3.next()).delete();
                    }
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(responseExecute, th2);
                        throw th3;
                    }
                }
            } catch (Throwable th4) {
                Iterator it4 = arrayList.iterator();
                while (it4.hasNext()) {
                    ((File) it4.next()).delete();
                }
                throw th4;
            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "网络错误";
            }
            c0011a = new a.C0011a(-1, message);
            Iterator it5 = arrayList.iterator();
            while (it5.hasNext()) {
                ((File) it5.next()).delete();
            }
        }
        return c0011a;
    }
}
