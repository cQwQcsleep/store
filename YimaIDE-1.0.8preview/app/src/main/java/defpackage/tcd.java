package defpackage;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import javax.security.auth.x500.X500Principal;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class tcd {
    public static final tcd a = new tcd();

    public static final class d {
        public final boolean a;
        public final boolean b;
        public final String c;

        public d(boolean z, boolean z2, String str) {
            str.getClass();
            this.a = z;
            this.b = z2;
            this.c = str;
        }

        public final String a() {
            return this.c;
        }

        public final boolean b() {
            return this.b;
        }

        public final boolean c() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            return this.a == dVar.a && this.b == dVar.b && Intrinsics.areEqual(this.c, dVar.c);
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.a) * 31) + Boolean.hashCode(this.b)) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "SigningUiState(useReleaseKey=" + this.a + ", hasKeystore=" + this.b + ", alias=" + this.c + ")";
        }
    }

    public static final class e {
        public final boolean a;
        public final String b;
        public final String c;
        public final String d;

        public e(boolean z, String str, String str2, String str3) {
            str.getClass();
            this.a = z;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }

        public final String a() {
            return this.b;
        }

        public final String b() {
            return this.d;
        }

        public final String c() {
            return this.c;
        }

        public final boolean d() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof e)) {
                return false;
            }
            e eVar = (e) obj;
            return this.a == eVar.a && Intrinsics.areEqual(this.b, eVar.b) && Intrinsics.areEqual(this.c, eVar.c) && Intrinsics.areEqual(this.d, eVar.d);
        }

        public int hashCode() {
            int iHashCode = ((Boolean.hashCode(this.a) * 31) + this.b.hashCode()) * 31;
            String str = this.c;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.d;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "StoredConfig(useReleaseKey=" + this.a + ", alias=" + this.b + ", storePassword=" + this.c + ", keyPassword=" + this.d + ")";
        }
    }

    public static final class f extends SuspendLambda implements Function2 {
        public int b;
        public final /* synthetic */ String c;
        public final /* synthetic */ String d;
        public final /* synthetic */ String e;
        public final /* synthetic */ int f;
        public final /* synthetic */ String g;
        public final /* synthetic */ String h;
        public final /* synthetic */ Context i;
        public final /* synthetic */ String j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, String str2, String str3, int i, String str4, String str5, Context context, String str6, Continuation continuation) {
            super(2, continuation);
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = i;
            this.g = str4;
            this.h = str5;
            this.i = context;
            this.j = str6;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new f(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.b != 0) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            String string = StringsKt.trim(this.c).toString();
            if (string.length() == 0) {
                return new a(false, "别名不能为空");
            }
            if (this.d.length() < 6) {
                return new a(false, "密钥库密码至少 6 位");
            }
            if (this.e.length() < 6) {
                return new a(false, "密钥密码至少 6 位");
            }
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(2048);
                KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
                long jCurrentTimeMillis = System.currentTimeMillis();
                Date date = new Date(jCurrentTimeMillis - 86400000);
                int iCoerceIn = RangesKt.coerceIn(this.f, 1, 100);
                Date date2 = new Date((((long) iCoerceIn) * 31536000000L) + jCurrentTimeMillis);
                String string2 = StringsKt.trim(this.g).toString();
                if (string2.length() == 0) {
                    string2 = string;
                }
                String string3 = StringsKt.trim(this.h).toString();
                if (string3.length() == 0) {
                    string3 = "Yima";
                }
                X500Principal x500Principal = new X500Principal("CN=" + string2 + ", O=" + string3);
                BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
                X509Certificate certificate = new JcaX509CertificateConverter().setProvider(bouncyCastleProvider).getCertificate(new JcaX509v3CertificateBuilder(x500Principal, BigInteger.valueOf(jCurrentTimeMillis), date, date2, x500Principal, keyPairGenerateKeyPair.getPublic()).build(new JcaContentSignerBuilder("SHA256withRSA").setProvider(bouncyCastleProvider).build(keyPairGenerateKeyPair.getPrivate())));
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(null, null);
                PrivateKey privateKey = keyPairGenerateKeyPair.getPrivate();
                char[] charArray = this.e.toCharArray();
                charArray.getClass();
                certificate.getClass();
                keyStore.setKeyEntry(string, privateKey, charArray, new Certificate[]{certificate});
                tcd tcdVar = tcd.a;
                File fileJ = tcdVar.j(this.i, this.j);
                File parentFile = fileJ.getParentFile();
                if (parentFile != null) {
                    Boxing.boxBoolean(parentFile.mkdirs());
                }
                FileOutputStream fileOutputStream = new FileOutputStream(fileJ);
                try {
                    char[] charArray2 = this.d.toCharArray();
                    charArray2.getClass();
                    keyStore.store(fileOutputStream, charArray2);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                    tcdVar.q(this.i, this.j, new e(true, string, this.d, this.e));
                    return new a(true, "已生成自有密钥库（别名 " + string + "，有效期 " + iCoerceIn + " 年）");
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileOutputStream, th);
                        throw th2;
                    }
                }
            } catch (Exception e) {
                Log.e("SigningManager", "generateKeystore failed", e);
                return new a(false, "生成失败：" + e.getMessage());
            }
        }
    }

    public static final class g extends SuspendLambda implements Function2 {
        public int b;
        public final /* synthetic */ String c;
        public final /* synthetic */ Context d;
        public final /* synthetic */ Uri e;
        public final /* synthetic */ String f;
        public final /* synthetic */ String g;
        public final /* synthetic */ String h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, Context context, Uri uri, String str2, String str3, String str4, Continuation continuation) {
            super(2, continuation);
            this.c = str;
            this.d = context;
            this.e = uri;
            this.f = str2;
            this.g = str3;
            this.h = str4;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new g(this.c, this.d, this.e, this.f, this.g, this.h, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            byte[] bArr = null;
            if (this.b != 0) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            String string = StringsKt.trim(this.c).toString();
            if (string.length() == 0) {
                return new a(false, "别名不能为空");
            }
            try {
                InputStream inputStreamOpenInputStream = this.d.getContentResolver().openInputStream(this.e);
                if (inputStreamOpenInputStream != null) {
                    try {
                        byte[] bytes = ByteStreamsKt.readBytes(inputStreamOpenInputStream);
                        CloseableKt.closeFinally(inputStreamOpenInputStream, (Throwable) null);
                        bArr = bytes;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(inputStreamOpenInputStream, th);
                            throw th2;
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("SigningManager", "read keystore uri failed", e);
            }
            if (bArr == null) {
                return new a(false, "无法读取所选文件");
            }
            try {
                tcd tcdVar = tcd.a;
                char[] charArray = this.f.toCharArray();
                charArray.getClass();
                KeyStore keyStoreK = tcdVar.k(bArr, charArray);
                if (keyStoreK == null) {
                    return new a(false, "密钥库密码错误或格式不支持（请使用 PKCS12/.p12）");
                }
                if (!keyStoreK.containsAlias(string)) {
                    Enumeration<String> enumerationAliases = keyStoreK.aliases();
                    enumerationAliases.getClass();
                    ArrayList list = Collections.list(enumerationAliases);
                    list.getClass();
                    if (list.isEmpty()) {
                        str = "";
                    } else {
                        str = "；文件内别名：" + CollectionsKt.joinToString$default(list, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null);
                    }
                    return new a(false, "密钥库中找不到别名「" + string + "」" + str);
                }
                try {
                    char[] charArray2 = this.g.toCharArray();
                    charArray2.getClass();
                    if (!(keyStoreK.getKey(string, charArray2) instanceof PrivateKey)) {
                        return new a(false, "别名「" + string + "」下不是私钥条目");
                    }
                    try {
                        File fileJ = tcdVar.j(this.d, this.h);
                        File parentFile = fileJ.getParentFile();
                        if (parentFile != null) {
                            Boxing.boxBoolean(parentFile.mkdirs());
                        }
                        FilesKt.writeBytes(fileJ, bArr);
                        tcdVar.q(this.d, this.h, new e(true, string, this.f, this.g));
                        return new a(true, "已导入自有密钥库（别名 " + string + "）");
                    } catch (Exception e2) {
                        Log.e("SigningManager", "persist imported keystore failed", e2);
                        return new a(false, "保存失败：" + e2.getMessage());
                    }
                } catch (UnrecoverableKeyException unused) {
                    return new a(false, "密钥密码错误");
                }
            } catch (Exception e3) {
                Log.e("SigningManager", "verify imported keystore failed", e3);
                return new a(false, "校验失败：" + e3.getMessage());
            }
        }
    }

    public static KeyStore a() throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("BKS", (Provider) new BouncyCastleProvider());
        keyStore.getClass();
        return keyStore;
    }

    public static KeyStore b() throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.getClass();
        return keyStore;
    }

    public final void f(Context context, String str) {
        context.getClass();
        str.getClass();
        FilesKt.deleteRecursively(l(context, str));
    }

    public final File g(Context context, String str) {
        return new File(l(context, str), "config.json");
    }

    public final Object h(Context context, String str, String str2, String str3, String str4, String str5, String str6, int i, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new f(str2, str3, str4, i, str5, str6, context, str, null), continuation);
    }

    public final Object i(Context context, String str, Uri uri, String str2, String str3, String str4, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new g(str3, context, uri, str2, str4, str, null), continuation);
    }

    public final File j(Context context, String str) {
        return new File(l(context, str), "release.p12");
    }

    public final KeyStore k(byte[] bArr, char[] cArr) {
        Iterator it = CollectionsKt.listOf(new Function0[]{new Function0() { // from class: rcd
            public final Object invoke() {
                return tcd.b();
            }
        }, new Function0() { // from class: scd
            public final Object invoke() {
                return tcd.a();
            }
        }}).iterator();
        while (it.hasNext()) {
            try {
                KeyStore keyStore = (KeyStore) ((Function0) it.next()).invoke();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    continue;
                    keyStore.load(byteArrayInputStream, cArr);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(byteArrayInputStream, (Throwable) null);
                    return keyStore;
                } catch (Throwable th) {
                    try {
                        continue;
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(byteArrayInputStream, th);
                        throw th2;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final File l(Context context, String str) {
        return new File(context.getFilesDir(), "project-signing/" + str);
    }

    public final e m(Context context, String str) {
        File fileG = g(context, str);
        if (!fileG.isFile()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(FilesKt.readText$default(fileG, (Charset) null, 1, (Object) null));
            boolean zOptBoolean = jSONObject.optBoolean("useReleaseKey", false);
            String strOptString = jSONObject.optString("alias", "");
            strOptString.getClass();
            b bVar = b.a;
            String strOptString2 = jSONObject.optString("storePassword", "");
            strOptString2.getClass();
            String strA = bVar.a(strOptString2);
            String strOptString3 = jSONObject.optString("keyPassword", "");
            strOptString3.getClass();
            return new e(zOptBoolean, strOptString, strA, bVar.a(strOptString3));
        } catch (Exception e2) {
            Log.w("SigningManager", "readConfig failed", e2);
            return null;
        }
    }

    public final c n(Context context, String str) {
        context.getClass();
        str.getClass();
        e eVarM = m(context, str);
        if (eVarM == null) {
            return new c.b(hb0.c.a.a);
        }
        File fileJ = j(context, str);
        if (!eVarM.d() || !fileJ.isFile() || fileJ.length() == 0 || StringsKt.isBlank(eVarM.a())) {
            return new c.b(hb0.c.a.a);
        }
        String strC = eVarM.c();
        if (strC == null || strC.length() == 0 || eVarM.b() == null) {
            return new c.a("签名密钥配置已损坏（密码解密失败），请在签名设置中重新导入密钥库");
        }
        String strA = eVarM.a();
        String strB = eVarM.b();
        if (strB.length() == 0) {
            strB = strC;
        }
        return new c.b(new hb0.c.b(fileJ, strC, strA, strB));
    }

    public final void o(Context context, String str, boolean z) {
        context.getClass();
        str.getClass();
        File fileG = g(context, str);
        if (fileG.isFile()) {
            try {
                JSONObject jSONObject = new JSONObject(FilesKt.readText$default(fileG, (Charset) null, 1, (Object) null));
                jSONObject.put("useReleaseKey", z);
                String string = jSONObject.toString();
                string.getClass();
                FilesKt.writeText$default(fileG, string, (Charset) null, 2, (Object) null);
            } catch (Exception e2) {
                Log.w("SigningManager", "setUseReleaseKey failed", e2);
            }
        }
    }

    public final d p(Context context, String str) {
        JSONObject jSONObject;
        context.getClass();
        str.getClass();
        File fileG = g(context, str);
        if (fileG.isFile()) {
            try {
                jSONObject = new JSONObject(FilesKt.readText$default(fileG, (Charset) null, 1, (Object) null));
            } catch (Exception unused) {
                jSONObject = null;
            }
        } else {
            jSONObject = null;
        }
        File fileJ = j(context, str);
        boolean z = fileJ.isFile() && fileJ.length() > 0;
        boolean z2 = jSONObject != null && jSONObject.optBoolean("useReleaseKey", false) && z;
        String strOptString = jSONObject != null ? jSONObject.optString("alias", "") : null;
        return new d(z2, z, strOptString != null ? strOptString : "");
    }

    public final void q(Context context, String str, e eVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("useReleaseKey", eVar.d());
        jSONObject.put("alias", eVar.a());
        b bVar = b.a;
        String strC = eVar.c();
        if (strC == null) {
            strC = "";
        }
        jSONObject.put("storePassword", bVar.b(strC));
        String strB = eVar.b();
        jSONObject.put("keyPassword", bVar.b(strB != null ? strB : ""));
        File fileG = g(context, str);
        File parentFile = fileG.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        String string = jSONObject.toString();
        string.getClass();
        FilesKt.writeText$default(fileG, string, (Charset) null, 2, (Object) null);
    }

    public static abstract class c {

        public static final class a extends c {
            public final String a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str) {
                super(null);
                str.getClass();
                this.a = str;
            }

            public final String a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof a) && Intrinsics.areEqual(this.a, ((a) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Corrupted(message=" + this.a + ")";
            }
        }

        public static final class b extends c {
            public final hb0.c a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(hb0.c cVar) {
                super(null);
                cVar.getClass();
                this.a = cVar;
            }

            public final hb0.c a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof b) && Intrinsics.areEqual(this.a, ((b) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Ok(key=" + this.a + ")";
            }
        }

        public /* synthetic */ c(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public c() {
        }
    }
}
