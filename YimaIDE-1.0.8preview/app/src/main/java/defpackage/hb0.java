package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.android.apksig.ApkSigner;
import com.android.apksig.KeyConfig;
import com.reandroid.apk.ApkModule;
import com.reandroid.archive.FileInputSource;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.security.auth.x500.X500Principal;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class hb0 {
    public static volatile a c;
    public static final hb0 a = new hb0();
    public static final char[] b = {'y', 'i', 'm', 'a', LocaleUtility.IETF_SEPARATOR, 'd', 'e', 'b', 'u', 'g'};
    public static final int d = 8;

    public static final class a {
        public final Uri a;
        public final String b;
        public final String c;
        public final long d;

        public a(Uri uri, String str, String str2, long j) {
            uri.getClass();
            str.getClass();
            str2.getClass();
            this.a = uri;
            this.b = str;
            this.c = str2;
            this.d = j;
        }

        public final String a() {
            return this.b;
        }

        public final long b() {
            return this.d;
        }

        public final Uri c() {
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
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c) && this.d == aVar.d;
        }

        public int hashCode() {
            return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Long.hashCode(this.d);
        }

        public String toString() {
            return "PublicExport(uri=" + this.a + ", displayName=" + this.b + ", relativePath=" + this.c + ", sizeBytes=" + this.d + ")";
        }
    }

    public interface c {

        public static final class a implements c {
            public static final a a = new a();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof a);
            }

            public int hashCode() {
                return -1063268081;
            }

            public String toString() {
                return "Debug";
            }
        }

        public static final class b implements c {
            public final File a;
            public final String b;
            public final String c;
            public final String d;

            public b(File file, String str, String str2, String str3) {
                file.getClass();
                str.getClass();
                str2.getClass();
                str3.getClass();
                this.a = file;
                this.b = str;
                this.c = str2;
                this.d = str3;
            }

            public final String a() {
                return this.c;
            }

            public final File b() {
                return this.a;
            }

            public final String c() {
                return this.d;
            }

            public final String d() {
                return this.b;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof b)) {
                    return false;
                }
                b bVar = (b) obj;
                return Intrinsics.areEqual(this.a, bVar.a) && Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.c, bVar.c) && Intrinsics.areEqual(this.d, bVar.d);
            }

            public int hashCode() {
                return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
            }

            public String toString() {
                return "Keystore(file=" + this.a + ", storePassword=" + this.b + ", alias=" + this.c + ", keyPassword=" + this.d + ")";
            }
        }
    }

    public static final class d implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(((File) obj).getName(), ((File) obj2).getName());
        }
    }

    public static boolean a(File file) {
        file.getClass();
        return true;
    }

    public static KeyStore b() throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.getClass();
        return keyStore;
    }

    public static boolean c(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    public static Unit d(File file, String str, File file2, String str2, String str3, OutputStream outputStream) {
        outputStream.getClass();
        a.z(outputStream, file, str, file2, str2, str3);
        return Unit.INSTANCE;
    }

    public static boolean e(File file) {
        if (file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "apk")) {
            String name = file.getName();
            name.getClass();
            if (StringsKt.startsWith$default(name, "app-", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static KeyStore f() throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("BKS", (Provider) new BouncyCastleProvider());
        keyStore.getClass();
        return keyStore;
    }

    public static boolean g(Function1 function1, File file) {
        file.getClass();
        return file.isFile() && ((Boolean) function1.invoke(file)).booleanValue();
    }

    public static boolean h(File file) {
        file.getClass();
        return Intrinsics.areEqual(FilesKt.getExtension(file), "so");
    }

    public static /* synthetic */ void j(hb0 hb0Var, ApkModule apkModule, File file, String str, Set set, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: eb0
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(hb0.a((File) obj2));
                }
            };
        }
        hb0Var.i(apkModule, file, str, set, function1);
    }

    public final void i(ApkModule apkModule, File file, String str, Set set, final Function1 function1) {
        if (file.isDirectory()) {
            for (File file2 : SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: db0
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(hb0.g(function1, (File) obj));
                }
            })) {
                String path = FilesKt.relativeTo(file2, file).getPath();
                path.getClass();
                String str2 = str + PsuedoNames.PSEUDONAME_ROOT + StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
                if (set.add(str2)) {
                    apkModule.add(new FileInputSource(file2, str2));
                }
            }
        }
    }

    public final String k(String str, String str2, c cVar) {
        StringBuilder sb = new StringBuilder("Yima IDE 正式打包导出\n====================\n\n本压缩包包含：\n");
        sb.append("  · " + str + " — 已签名的 Android 安装包");
        sb.append('\n');
        if (str2 != null) {
            sb.append("  · " + str2 + " — 本次打包使用的签名密钥库（PKCS12）");
            sb.append('\n');
        }
        sb.append("  · 本说明文件\n\n【重要】请妥善保管本压缩包\n────────────────────────\n本包内含签名密钥库与密码信息，等同于您的应用发布身份。\n· 应用商店更新、用户覆盖安装必须使用同一签名证书；\n· 丢失证书或忘记密码，将无法为已上架应用发布更新；\n· 请整包备份到安全位置（加密 U 盘、密码管理器、私有云等）。\n\n【安全提示】\n· 切勿通过公开渠道发送本 ZIP（微信群、邮件未加密、公共网盘链接等）；\n· 持有本包的人可使用您的证书签名任意 APK，请仅保存在可信任的设备上；\n· 若怀疑泄露，请尽快更换密钥并联系各应用商店更新签名（流程较繁琐，重在预防）；\n· 建议额外将 release-signing.p12 与下方密码分别备份一份，防止单点丢失。\n\n");
        if (cVar instanceof c.b) {
            sb.append("本次签名信息（请与密钥库一并保管）\n");
            sb.append("  密钥库文件：" + str2);
            sb.append('\n');
            c.b bVar = (c.b) cVar;
            sb.append("  别名 (alias)：" + bVar.a());
            sb.append('\n');
            sb.append("  密钥库密码：" + bVar.d());
            sb.append('\n');
            sb.append("  密钥密码：" + bVar.c());
            sb.append('\n');
        } else {
            if (!Intrinsics.areEqual(cVar, c.a.a)) {
                bu8.a();
                return null;
            }
            sb.append("【注意】本次 APK 使用 IDE 调试签名，不可用于应用商店上架。\n本 ZIP 未包含正式签名密钥库。请在「正式打包 → 签名设置」中\n生成或导入自有密钥并启用后重新打包，届时说明文件将写入完整密码信息。\n");
        }
        sb.append('\n');
        sb.append("导出时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
        sb.append('\n');
        return sb.toString();
    }

    public final b l(Context context, File file, File file2, File file3, int i, List list, List list2, c cVar) {
        context.getClass();
        file.getClass();
        file2.getClass();
        file3.getClass();
        list.getClass();
        list2.getClass();
        cVar.getClass();
        try {
            file3.mkdirs();
            File[] fileArrListFiles = file3.listFiles(new FileFilter() { // from class: ab0
                @Override // java.io.FileFilter
                public final boolean accept(File file4) {
                    return hb0.e(file4);
                }
            });
            if (fileArrListFiles != null) {
                for (File file4 : fileArrListFiles) {
                    file4.delete();
                }
            }
            File[] fileArrListFiles2 = file2.listFiles(new FileFilter() { // from class: bb0
                @Override // java.io.FileFilter
                public final boolean accept(File file5) {
                    return hb0.c(file5);
                }
            });
            List<File> listSortedWith = fileArrListFiles2 != null ? ArraysKt.sortedWith(fileArrListFiles2, new d()) : null;
            if (listSortedWith == null) {
                listSortedWith = CollectionsKt.emptyList();
            }
            if (listSortedWith.isEmpty()) {
                return new b(false, "no dex files to package", null, 4, null);
            }
            if (!file.isFile()) {
                return new b(false, "resources.ap_ missing", null, 4, null);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            File file5 = new File(file3, "app-unsigned-" + jCurrentTimeMillis + ".apk");
            if (file5.exists()) {
                file5.delete();
            }
            ApkModule apkModuleLoadApkFile = ApkModule.loadApkFile(file);
            for (File file6 : listSortedWith) {
                apkModuleLoadApkFile.add(new FileInputSource(file6, file6.getName()));
            }
            HashSet hashSet = new HashSet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                File file7 = (File) it.next();
                apkModuleLoadApkFile.getClass();
                j(this, apkModuleLoadApkFile, file7, "assets", hashSet, null, 16, null);
            }
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                File file8 = (File) it2.next();
                apkModuleLoadApkFile.getClass();
                i(apkModuleLoadApkFile, file8, "lib", hashSet, new Function1() { // from class: cb0
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(hb0.h((File) obj));
                    }
                });
            }
            apkModuleLoadApkFile.writeApk(file5);
            File file9 = new File(file3, "app-" + jCurrentTimeMillis + ".apk");
            if (file9.exists()) {
                file9.delete();
            }
            y(context, file5, file9, i, cVar);
            file5.delete();
            return new b(true, "APK built: " + file9.getName(), file9);
        } catch (Throwable th) {
            Log.e("ApkPackager", "buildSignedApkFromAp failed", th);
            String message = th.getMessage();
            if (message == null) {
                message = th.toString();
            }
            return new b(false, message, null, 4, null);
        }
    }

    public final boolean m(Context context) {
        context.getClass();
        return context.getPackageManager().canRequestPackageInstalls();
    }

    public final a n() {
        return c;
    }

    public final void o(Context context, File file) {
        context.getClass();
        file.getClass();
        Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        intent.addFlags(268435457);
        context.startActivity(intent);
    }

    public final KeyStore p(File file, char[] cArr) {
        Iterator it = CollectionsKt.listOf(new Function0[]{new Function0() { // from class: fb0
            public final Object invoke() {
                return hb0.b();
            }
        }, new Function0() { // from class: gb0
            public final Object invoke() {
                return hb0.f();
            }
        }}).iterator();
        Exception e = null;
        while (it.hasNext()) {
            try {
                KeyStore keyStore = (KeyStore) ((Function0) it.next()).invoke();
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    continue;
                    keyStore.load(fileInputStream, cArr);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                    return keyStore;
                } catch (Throwable th) {
                    try {
                        continue;
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileInputStream, th);
                        throw th2;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        mg9.a("无法读取密钥库（密码错误或格式不支持；请使用 PKCS12/.p12 格式）", e);
        return null;
    }

    public final Pair q(Context context) throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException {
        File file = new File(context.getFilesDir(), "signing/debug.p12");
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        if (file.exists() && file.length() > 0) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                char[] cArr = b;
                keyStore.load(fileInputStream, cArr);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                Key key = keyStore.getKey("yima", cArr);
                key.getClass();
                Certificate certificate = keyStore.getCertificate("yima");
                certificate.getClass();
                return TuplesKt.to((PrivateKey) key, (X509Certificate) certificate);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStream, th);
                    throw th2;
                }
            }
        }
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
        long jCurrentTimeMillis = System.currentTimeMillis();
        Date date = new Date(jCurrentTimeMillis - 86400000);
        Date date2 = new Date(946080000000L + jCurrentTimeMillis);
        X500Principal x500Principal = new X500Principal("CN=Yima Debug, O=Yima, C=US");
        BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
        X509Certificate certificate2 = new JcaX509CertificateConverter().setProvider(bouncyCastleProvider).getCertificate(new JcaX509v3CertificateBuilder(x500Principal, BigInteger.valueOf(jCurrentTimeMillis), date, date2, x500Principal, keyPairGenerateKeyPair.getPublic()).build(new JcaContentSignerBuilder("SHA256withRSA").setProvider(bouncyCastleProvider).build(keyPairGenerateKeyPair.getPrivate())));
        keyStore.load(null, null);
        PrivateKey privateKey = keyPairGenerateKeyPair.getPrivate();
        char[] cArr2 = b;
        keyStore.setKeyEntry("yima", privateKey, cArr2, new X509Certificate[]{certificate2});
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            keyStore.store(fileOutputStream, cArr2);
            Unit unit2 = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
            return TuplesKt.to(keyPairGenerateKeyPair.getPrivate(), certificate2);
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(fileOutputStream, th3);
                throw th4;
            }
        }
    }

    public final Triple r(Context context, c cVar) throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException {
        if (cVar instanceof c.a) {
            Pair pairQ = q(context);
            return new Triple("yima", (PrivateKey) pairQ.component1(), CollectionsKt.listOf((X509Certificate) pairQ.component2()));
        }
        if (!(cVar instanceof c.b)) {
            bu8.a();
            return null;
        }
        c.b bVar = (c.b) cVar;
        File fileB = bVar.b();
        char[] charArray = bVar.d().toCharArray();
        charArray.getClass();
        KeyStore keyStoreP = p(fileB, charArray);
        String strA = bVar.a();
        char[] charArray2 = bVar.c().toCharArray();
        charArray2.getClass();
        Key key = keyStoreP.getKey(strA, charArray2);
        PrivateKey privateKey = key instanceof PrivateKey ? (PrivateKey) key : null;
        if (privateKey == null) {
            zia.a("密钥库别名「", bVar.a(), "」下没有可用的私钥");
            return null;
        }
        Certificate[] certificateChain = keyStoreP.getCertificateChain(bVar.a());
        if (certificateChain != null) {
            ArrayList arrayList = new ArrayList();
            for (Certificate certificate : certificateChain) {
                X509Certificate x509Certificate = certificate instanceof X509Certificate ? (X509Certificate) certificate : null;
                if (x509Certificate != null) {
                    arrayList.add(x509Certificate);
                }
            }
            if (arrayList.isEmpty()) {
                arrayList = null;
            }
            if (arrayList != null) {
                return new Triple(bVar.a(), privateKey, arrayList);
            }
        }
        zia.a("密钥库别名「", bVar.a(), "」下没有证书链");
        return null;
    }

    public final void s(Context context) {
        context.getClass();
        Intent intentAddFlags = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES").setData(Uri.parse("package:" + context.getPackageName())).addFlags(268435456);
        intentAddFlags.getClass();
        context.startActivity(intentAddFlags);
    }

    public final boolean t(Context context, Uri uri) {
        context.getClass();
        uri.getClass();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "application/zip");
        intent.addFlags(268959745);
        try {
            try {
                context.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        } catch (ActivityNotFoundException unused2) {
            context.startActivity(new Intent("android.intent.action.VIEW_DOWNLOADS").addFlags(268435456));
            return true;
        }
    }

    public final long u(Context context, Uri uri) {
        Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_size"}, null, null, null);
        if (cursorQuery == null) {
            return 0L;
        }
        try {
            long j = cursorQuery.moveToFirst() ? cursorQuery.getLong(0) : 0L;
            CloseableKt.closeFinally(cursorQuery, (Throwable) null);
            return j;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorQuery, th);
                throw th2;
            }
        }
    }

    public final String v(String str) {
        String strReplace = new Regex("[\\\\/:*?\"<>|\\s]+").replace(StringsKt.trim(str).toString(), "_");
        if (StringsKt.isBlank(strReplace)) {
            strReplace = "app";
        }
        return StringsKt.take(strReplace, 40);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00e3  */
    public final a w(Context context, final File file, String str, String str2, c cVar) {
        String strA;
        tbc$a tbc_aA;
        final File file2;
        context.getClass();
        file.getClass();
        str.getClass();
        str2.getClass();
        cVar.getClass();
        kwa.b bVarB = sbc.a.b();
        if (bVarB == null || (strA = bVarB.a()) == null) {
            ubc.a aVarB = ubc.a.b();
            strA = (aVarB == null || (tbc_aA = aVarB.a()) == null) ? null : tbc_aA.a();
        }
        if (strA == null || StringsKt.isBlank(strA) || !ubc.a.c(strA)) {
            Log.e("ApkPackager", "saveReleaseBundleToDownloads: missing unlock grant");
            return null;
        }
        String strV = v(str);
        String strReplace = new Regex("[\\\\/:*?\"<>|\\s]+").replace(StringsKt.trim(str2).toString(), "_");
        if (StringsKt.isBlank(strReplace)) {
            strReplace = "1.0";
        }
        final String str3 = strV + "-" + strReplace + ".apk";
        String str4 = strV + "-" + strReplace + "-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault()).format(new Date()) + ".zip";
        if (cVar instanceof c.b) {
            File fileB = ((c.b) cVar).b();
            if (fileB.isFile() && fileB.length() > 0) {
                file2 = fileB;
            }
            final String str5 = "release-signing.p12";
            final String strK = k(str3, file2 != null ? "release-signing.p12" : null, cVar);
            return x(context, str4, new Function1() { // from class: za0
                public final Object invoke(Object obj) {
                    return hb0.d(file, str3, file2, str5, strK, (OutputStream) obj);
                }
            });
        }
        if (!Intrinsics.areEqual(cVar, c.a.a)) {
            bu8.a();
            return null;
        }
        file2 = null;
        final String str6 = "release-signing.p12";
        final String strK2 = k(str3, file2 != null ? "release-signing.p12" : null, cVar);
        return x(context, str4, new Function1() { // from class: za0
            public final Object invoke(Object obj) {
                return hb0.d(file, str3, file2, str6, strK2, (OutputStream) obj);
            }
        });
    }

    public final a x(Context context, String str, Function1 function1) {
        context.getClass();
        str.getClass();
        function1.getClass();
        String str2 = Environment.DIRECTORY_DOWNLOADS + "/Yima IDE";
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", "application/zip");
        contentValues.put("relative_path", str2.concat(PsuedoNames.PSEUDONAME_ROOT));
        contentValues.put("is_pending", (Integer) 1);
        Uri uriInsert = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
        if (uriInsert == null) {
            Log.e("ApkPackager", "saveZipToPublicDownloads: MediaStore insert failed");
            return null;
        }
        try {
            OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
            if (outputStreamOpenOutputStream == null) {
                contentResolver.delete(uriInsert, null, null);
                Log.e("ApkPackager", "saveZipToPublicDownloads: openOutputStream failed");
                return null;
            }
            try {
                function1.invoke(outputStreamOpenOutputStream);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStreamOpenOutputStream, (Throwable) null);
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("is_pending", (Integer) 0);
                contentResolver.update(uriInsert, contentValues2, null, null);
                a aVar = new a(uriInsert, str, str2 + PsuedoNames.PSEUDONAME_ROOT + str, u(context, uriInsert));
                c = aVar;
                return aVar;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(outputStreamOpenOutputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            Log.e("ApkPackager", "saveZipToPublicDownloads failed", th3);
            try {
                Result.Companion companion = Result.Companion;
                Result.constructor-impl(Integer.valueOf(contentResolver.delete(uriInsert, null, null)));
            } catch (Throwable th4) {
                Result.Companion companion2 = Result.Companion;
                Result.constructor-impl(ResultKt.createFailure(th4));
            }
            return null;
        }
    }

    public final void y(Context context, File file, File file2, int i, c cVar) throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException {
        Triple tripleR = r(context, cVar);
        String str = (String) tripleR.component1();
        PrivateKey privateKey = (PrivateKey) tripleR.component2();
        new ApkSigner.Builder(CollectionsKt.listOf(new ApkSigner.SignerConfig.Builder(str, new KeyConfig.Jca(privateKey), (List) tripleR.component3()).build())).setInputApk(file).setOutputApk(file2).setMinSdkVersion(21).setV1SigningEnabled(true).setV2SigningEnabled(true).setV3SigningEnabled(true).build().sign();
    }

    public final void z(OutputStream outputStream, File file, String str, File file2, String str2, String str3) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        try {
            zipOutputStream.putNextEntry(new ZipEntry(str));
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ByteStreamsKt.copyTo$default(fileInputStream, zipOutputStream, 0, 2, (Object) null);
                CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                zipOutputStream.closeEntry();
                if (file2 != null) {
                    zipOutputStream.putNextEntry(new ZipEntry(str2));
                    FileInputStream fileInputStream2 = new FileInputStream(file2);
                    try {
                        ByteStreamsKt.copyTo$default(fileInputStream2, zipOutputStream, 0, 2, (Object) null);
                        CloseableKt.closeFinally(fileInputStream2, (Throwable) null);
                        zipOutputStream.closeEntry();
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(fileInputStream2, th);
                            throw th2;
                        }
                    }
                }
                Charset charset = StandardCharsets.UTF_8;
                charset.getClass();
                byte[] bytes = str3.getBytes(charset);
                bytes.getClass();
                zipOutputStream.putNextEntry(new ZipEntry("请妥善保管签名证书.txt"));
                zipOutputStream.write(bytes);
                zipOutputStream.closeEntry();
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(zipOutputStream, (Throwable) null);
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
                CloseableKt.closeFinally(zipOutputStream, th5);
                throw th6;
            }
        }
    }

    public static final class b {
        public final boolean a;
        public final String b;
        public final File c;

        public b(boolean z, String str, File file) {
            str.getClass();
            this.a = z;
            this.b = str;
            this.c = file;
        }

        public final File a() {
            return this.c;
        }

        public final String b() {
            return this.b;
        }

        public final boolean c() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.a == bVar.a && Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.c, bVar.c);
        }

        public int hashCode() {
            int iHashCode = ((Boolean.hashCode(this.a) * 31) + this.b.hashCode()) * 31;
            File file = this.c;
            return iHashCode + (file == null ? 0 : file.hashCode());
        }

        public String toString() {
            return "Result(success=" + this.a + ", message=" + this.b + ", apk=" + this.c + ")";
        }

        public /* synthetic */ b(boolean z, String str, File file, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, str, (i & 4) != 0 ? null : file);
        }
    }
}
