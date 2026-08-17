package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class xb3 {
    public static final xb3 a = new xb3();

    public static final class a {
        public final int a;
        public final long b;
        public final boolean c;
        public final String d;
        public final long e;

        public a(int i, long j, boolean z, String str, long j2) {
            str.getClass();
            this.a = i;
            this.b = j;
            this.c = z;
            this.d = str;
            this.e = j2;
        }

        public final long a() {
            return this.e;
        }

        public final long b() {
            return this.b;
        }

        public final int c() {
            return this.a;
        }

        public final boolean d() {
            return this.c;
        }

        public final String e() {
            return this.d;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b && this.c == aVar.c && Intrinsics.areEqual(this.d, aVar.d) && this.e == aVar.e;
        }

        public int hashCode() {
            return (((((((Integer.hashCode(this.a) * 31) + Long.hashCode(this.b)) * 31) + Boolean.hashCode(this.c)) * 31) + this.d.hashCode()) * 31) + Long.hashCode(this.e);
        }

        public String toString() {
            return "SlotStat(files=" + this.a + ", bytes=" + this.b + ", hasMarker=" + this.c + ", runId=" + this.d + ", applyGen=" + this.e + ")";
        }
    }

    public static final class b implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(((File) obj).getName(), ((File) obj2).getName());
        }
    }

    public static boolean a(File file) {
        if (file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex")) {
            String name = file.getName();
            name.getClass();
            if (StringsKt.startsWith$default(name, "classes", false, 2, (Object) null) && file.length() > 0) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean g(xb3 xb3Var, Context context, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = cc3.a.F(context, str);
        }
        return xb3Var.f(context, str, str2);
    }

    public final boolean b(Context context, String str) {
        context.getClass();
        str.getClass();
        a aVarK = k(context, str);
        return aVarK != null && aVarK.c() > 0 && aVarK.b() > 0 && aVarK.d();
    }

    public final Uri c(String str) {
        Uri uri = Uri.parse("content://" + str);
        uri.getClass();
        return uri;
    }

    /* JADX WARN: Code duplicated, block: B:164:0x03bb  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r11v17, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r11v23 */
    /* JADX WARN: Type inference failed for: r11v24 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v25 */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v27 */
    /* JADX WARN: Type inference failed for: r13v3, types: [int] */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6, types: [int] */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r24v0, types: [xb3] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v18, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    public final boolean d(Context context, File file, String str) throws Throwable {
        Throwable th;
        ?? r13;
        ?? r11;
        ?? r3;
        ?? r2;
        Iterator it;
        boolean z;
        int i;
        int i2;
        ?? r4 = "ok";
        context.getClass();
        file.getClass();
        str.getClass();
        ?? applicationContext = context.getApplicationContext();
        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: wb3
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return xb3.a(file2);
            }
        });
        List<File> listSortedWith = fileArrListFiles != null ? ArraysKt.sortedWith(fileArrListFiles, new b()) : null;
        if (listSortedWith == null) {
            listSortedWith = CollectionsKt.emptyList();
        }
        ?? HasNext = 0;
        if (listSortedWith.isEmpty()) {
            Log.w("DebugHostCodeSlot", "no loadable dex in " + file);
            return false;
        }
        Iterator it2 = listSortedWith.iterator();
        ?? length = 0;
        while (it2.hasNext()) {
            length = (length == true ? 1L : 0L) + ((File) it2.next()).length();
        }
        String strG = kb3.a.g();
        File file2 = new File(applicationContext.getFilesDir(), "codeslot-stage");
        FilesKt.deleteRecursively(file2);
        file2.mkdirs();
        ArrayList arrayList = new ArrayList();
        for (File file3 : listSortedWith) {
            File file4 = new File(file2, file3.getName());
            FilesKt.copyTo$default(file3, file4, true, 0, 4, (Object) null);
            arrayList.add(TuplesKt.to(file4, file3.getName()));
        }
        String strJ = j(applicationContext, str);
        if (strJ == null) {
            Log.w("DebugHostCodeSlot", "authority not resolved for " + str);
            return false;
        }
        ArrayList arrayList2 = new ArrayList();
        try {
            try {
                try {
                    Bundle bundle = new Bundle();
                    z = false;
                    try {
                        bundle.putInt("count", arrayList.size());
                        if (strG.length() > 0) {
                            try {
                                bundle.putString("run_id", strG);
                            } catch (Throwable th2) {
                                th = th2;
                                r2 = applicationContext;
                                r11 = arrayList2;
                                r3 = str;
                                r13 = 1;
                            }
                        }
                        Iterator it3 = arrayList.iterator();
                        int i3 = 0;
                        while (true) {
                            HasNext = it3.hasNext();
                            if (HasNext == 0) {
                                break;
                            }
                            Object next = it3.next();
                            int i4 = i3 + 1;
                            if (i3 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            Pair pair = (Pair) next;
                            File file5 = (File) pair.component1();
                            String str2 = (String) pair.component2();
                            Iterator it4 = it3;
                            Uri uriForFile = FileProvider.getUriForFile((Context) applicationContext, applicationContext.getPackageName() + ".fileprovider", file5);
                            arrayList2.add(uriForFile);
                            applicationContext.grantUriPermission(str, uriForFile, 1);
                            bundle.putParcelable("uri_" + i3, uriForFile);
                            bundle.putString("name_" + i3, str2);
                            i3 = i4;
                            it3 = it4;
                            th = th;
                            r2 = r4;
                            r3 = applicationContext;
                            r11 = HasNext;
                            r13 = length;
                            it = r11.iterator();
                            it.getClass();
                            while (it.hasNext()) {
                                Object next2 = it.next();
                                next2.getClass();
                                Uri uri = (Uri) next2;
                                try {
                                    Result.Companion companion = Result.Companion;
                                    r2.revokeUriPermission(r3, uri, r13);
                                    Result.constructor-impl(Unit.INSTANCE);
                                } catch (Throwable th3) {
                                    Result.Companion companion2 = Result.Companion;
                                    Result.constructor-impl(ResultKt.createFailure(th3));
                                }
                            }
                            throw th;
                        }
                        ContentResolver contentResolver = applicationContext.getContentResolver();
                        Bundle bundleCall = contentResolver.call(c(strJ), "import", (String) null, bundle);
                        try {
                            if (bundleCall != null) {
                                if (bundleCall.getBoolean("ok")) {
                                    long j = length == true ? 1 : 0;
                                    if (bundleCall.getLong("bytes", -1L) != j) {
                                        Log.w("DebugHostCodeSlot", "byte mismatch");
                                        Iterator it5 = arrayList2.iterator();
                                        it5.getClass();
                                        while (it5.hasNext()) {
                                            Object next3 = it5.next();
                                            next3.getClass();
                                            Uri uri2 = (Uri) next3;
                                            try {
                                                Result.Companion companion3 = Result.Companion;
                                                applicationContext.revokeUriPermission(str, uri2, 1);
                                                Result.constructor-impl(Unit.INSTANCE);
                                            } catch (Throwable th4) {
                                                Result.Companion companion4 = Result.Companion;
                                                Result.constructor-impl(ResultKt.createFailure(th4));
                                            }
                                        }
                                        return false;
                                    }
                                    Bundle bundleCall2 = contentResolver.call(c(strJ), "apply", (String) null, (Bundle) null);
                                    if (bundleCall2 == null) {
                                        i2 = 1;
                                    } else if (bundleCall2.getBoolean("ok")) {
                                        try {
                                            long j2 = bundleCall2.getLong("applyGen", -1L);
                                            int i5 = bundleCall2.getInt("pid", -1);
                                            Thread.sleep(250L);
                                            HasNext = arrayList2;
                                            r4 = applicationContext;
                                            length = 1;
                                            try {
                                                try {
                                                    boolean zM = m(r4, strJ, j2, i5, 4000L);
                                                    if (!zM) {
                                                        try {
                                                            Log.w("DebugHostCodeSlot", "restart not observed after apply; requesting die");
                                                            try {
                                                                Result.Companion companion5 = Result.Companion;
                                                                Result.constructor-impl(contentResolver.call(c(strJ), "die", (String) null, (Bundle) null));
                                                            } catch (Throwable th5) {
                                                                Result.Companion companion6 = Result.Companion;
                                                                Result.constructor-impl(ResultKt.createFailure(th5));
                                                            }
                                                            Thread.sleep(200L);
                                                            zM = m(r4, strJ, j2, i5, 2500L);
                                                        } catch (Throwable th6) {
                                                            th = th6;
                                                            r3 = str;
                                                            r2 = r4;
                                                            r11 = HasNext;
                                                            r13 = length;
                                                        }
                                                    }
                                                    if (!zM) {
                                                        Log.w("DebugHostCodeSlot", "provider restart still not observed; continuing with launch+verify");
                                                    }
                                                    if (!h(r4, str)) {
                                                        Iterator it6 = HasNext.iterator();
                                                        it6.getClass();
                                                        while (it6.hasNext()) {
                                                            Object next4 = it6.next();
                                                            next4.getClass();
                                                            Uri uri3 = (Uri) next4;
                                                            try {
                                                                Result.Companion companion7 = Result.Companion;
                                                                r4.revokeUriPermission(str, uri3, 1);
                                                                Result.constructor-impl(Unit.INSTANCE);
                                                            } catch (Throwable th7) {
                                                                Result.Companion companion8 = Result.Companion;
                                                                Result.constructor-impl(ResultKt.createFailure(th7));
                                                            }
                                                        }
                                                        return false;
                                                    }
                                                    boolean zL = l(r4, str, j, strG, j2);
                                                    h(r4, str);
                                                    Iterator it7 = HasNext.iterator();
                                                    it7.getClass();
                                                    while (it7.hasNext()) {
                                                        Object next5 = it7.next();
                                                        next5.getClass();
                                                        Uri uri4 = (Uri) next5;
                                                        try {
                                                            Result.Companion companion9 = Result.Companion;
                                                            r4.revokeUriPermission(str, uri4, 1);
                                                            Result.constructor-impl(Unit.INSTANCE);
                                                        } catch (Throwable th8) {
                                                            Result.Companion companion10 = Result.Companion;
                                                            Result.constructor-impl(ResultKt.createFailure(th8));
                                                        }
                                                    }
                                                    return zL;
                                                } catch (Exception e) {
                                                    e = e;
                                                    applicationContext = str;
                                                    Log.w("DebugHostCodeSlot", "codeslot transfer failed", e);
                                                    Iterator it8 = HasNext.iterator();
                                                    it8.getClass();
                                                    while (it8.hasNext()) {
                                                        Object next6 = it8.next();
                                                        next6.getClass();
                                                        Uri uri5 = (Uri) next6;
                                                        try {
                                                            Result.Companion companion11 = Result.Companion;
                                                            r4.revokeUriPermission(applicationContext, uri5, length);
                                                            Result.constructor-impl(Unit.INSTANCE);
                                                        } catch (Throwable th9) {
                                                            Result.Companion companion12 = Result.Companion;
                                                            Result.constructor-impl(ResultKt.createFailure(th9));
                                                        }
                                                    }
                                                    return z;
                                                }
                                            } catch (Throwable th10) {
                                                th = th10;
                                                applicationContext = str;
                                                th = th;
                                                r2 = r4;
                                                r3 = applicationContext;
                                                r11 = HasNext;
                                                r13 = length;
                                            }
                                        } catch (Exception e2) {
                                            e = e2;
                                            r4 = applicationContext;
                                            HasNext = arrayList2;
                                            length = 1;
                                        } catch (Throwable th11) {
                                            th = th11;
                                            r4 = applicationContext;
                                            HasNext = arrayList2;
                                            length = 1;
                                        }
                                    } else {
                                        i2 = 1;
                                    }
                                    Log.w("DebugHostCodeSlot", "apply failed: " + (bundleCall2 != null ? bundleCall2.getString("error") : null));
                                    Iterator it9 = arrayList2.iterator();
                                    it9.getClass();
                                    while (it9.hasNext()) {
                                        Object next7 = it9.next();
                                        next7.getClass();
                                        Uri uri6 = (Uri) next7;
                                        try {
                                            Result.Companion companion13 = Result.Companion;
                                            applicationContext.revokeUriPermission(str, uri6, i2);
                                            Result.constructor-impl(Unit.INSTANCE);
                                        } catch (Throwable th12) {
                                            Result.Companion companion14 = Result.Companion;
                                            Result.constructor-impl(ResultKt.createFailure(th12));
                                        }
                                    }
                                    return false;
                                }
                                i = 1;
                                th = th;
                                r2 = r4;
                                r3 = applicationContext;
                                r11 = HasNext;
                                r13 = length;
                                it = r11.iterator();
                                it.getClass();
                                while (it.hasNext()) {
                                    Object next8 = it.next();
                                    next8.getClass();
                                    Uri uri7 = (Uri) next8;
                                    Result.Companion companion15 = Result.Companion;
                                    r2.revokeUriPermission(r3, uri7, r13);
                                    Result.constructor-impl(Unit.INSTANCE);
                                }
                                throw th;
                            }
                            i = 1;
                            Log.w("DebugHostCodeSlot", "import failed: " + (bundleCall != null ? bundleCall.getString("error") : null));
                            Iterator it10 = arrayList2.iterator();
                            it10.getClass();
                            while (it10.hasNext()) {
                                Object next9 = it10.next();
                                next9.getClass();
                                Uri uri8 = (Uri) next9;
                                try {
                                    Result.Companion companion16 = Result.Companion;
                                    applicationContext.revokeUriPermission(str, uri8, i);
                                    Result.constructor-impl(Unit.INSTANCE);
                                } catch (Throwable th13) {
                                    Result.Companion companion17 = Result.Companion;
                                    Result.constructor-impl(ResultKt.createFailure(th13));
                                }
                            }
                            return false;
                        } catch (Exception e3) {
                            e = e3;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        r4 = applicationContext;
                        HasNext = arrayList2;
                        applicationContext = str;
                        length = 1;
                    }
                } catch (Exception e5) {
                    e = e5;
                    r4 = applicationContext;
                    applicationContext = str;
                    z = false;
                    length = 1;
                    HasNext = arrayList2;
                }
            } catch (Throwable th14) {
                th = th14;
                r4 = applicationContext;
                HasNext = arrayList2;
                applicationContext = str;
                length = 1;
            }
        } catch (Throwable th15) {
            th = th15;
        }
    }

    public final boolean e(Context context, String str) {
        context.getClass();
        str.getClass();
        try {
            String strJ = j(context, str);
            if (strJ == null) {
                return false;
            }
            Bundle bundleCall = context.getApplicationContext().getContentResolver().call(c(strJ), "version", (String) null, (Bundle) null);
            return (bundleCall != null ? bundleCall.getInt("protocol", 0) : 0) >= 9;
        } catch (Exception e) {
            Log.w("DebugHostCodeSlot", "isCodeSlotCapable failed", e);
            return false;
        }
    }

    public final boolean f(Context context, String str, String str2) {
        context.getClass();
        str.getClass();
        str2.getClass();
        long jI = i(context, str);
        if (jI <= 0) {
            return false;
        }
        cc3 cc3Var = cc3.a;
        if (cc3Var.E(context, str) == jI) {
            if (str2.length() == 0) {
                return true;
            }
            String strF = cc3Var.F(context, str);
            if (strF.length() == 0 || Intrinsics.areEqual(strF, str2)) {
                return true;
            }
        }
        a aVarK = k(context, dc3.a.a(str));
        if (aVarK != null && aVarK.d() && aVarK.c() > 0 && aVarK.b() == jI) {
            return str2.length() <= 0 || Intrinsics.areEqual(aVarK.e(), str2);
        }
        return false;
    }

    public final boolean h(Context context, String str) {
        context.getClass();
        str.getClass();
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return false;
        }
        launchIntentForPackage.addFlags(335577088);
        try {
            context.startActivity(launchIntentForPackage);
            return true;
        } catch (Exception e) {
            Log.w("DebugHostCodeSlot", "launchHost failed", e);
            return false;
        }
    }

    public final long i(Context context, String str) {
        context.getClass();
        str.getClass();
        File file = new File(context.getFilesDir(), "projects/" + str + "/build/code-slot-dex");
        long length = 0;
        if (!file.isDirectory()) {
            return 0L;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            ArrayList arrayList = new ArrayList();
            for (File file2 : fileArrListFiles) {
                if (file2.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file2), "dex")) {
                    String name = file2.getName();
                    name.getClass();
                    if (StringsKt.startsWith$default(name, "classes", false, 2, (Object) null) && file2.length() > 0) {
                        arrayList.add(file2);
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                length += ((File) it.next()).length();
            }
        }
        return length;
    }

    public final String j(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        dc3 dc3Var = dc3.a;
        for (String str2 : CollectionsKt.listOf(new String[]{dc3Var.d(str), dc3Var.i(str)})) {
            ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(str2, 0);
            if (providerInfoResolveContentProvider != null && Intrinsics.areEqual(providerInfoResolveContentProvider.packageName, str)) {
                return str2;
            }
        }
        return null;
    }

    public final a k(Context context, String str) {
        Bundle bundleCall;
        context.getClass();
        str.getClass();
        try {
            String strJ = j(context, str);
            if (strJ == null || (bundleCall = context.getApplicationContext().getContentResolver().call(c(strJ), "stat", (String) null, (Bundle) null)) == null) {
                return null;
            }
            int i = bundleCall.getInt("files", 0);
            long j = bundleCall.getLong("bytes", 0L);
            boolean z = bundleCall.getBoolean("hasMarker", false);
            String string = bundleCall.getString("run_id");
            if (string == null) {
                string = "";
            }
            return new a(i, j, z, string, bundleCall.getLong("applyGen", 0L));
        } catch (Exception e) {
            Log.w("DebugHostCodeSlot", "slotStat failed", e);
            return null;
        }
    }

    public final boolean l(Context context, String str, long j, String str2, long j2) {
        context.getClass();
        str.getClass();
        str2.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis() + 4000;
        while (System.currentTimeMillis() < jCurrentTimeMillis) {
            a aVarK = k(context, str);
            if (aVarK != null && aVarK.c() > 0 && aVarK.b() > 0 && aVarK.d()) {
                boolean z = j <= 0 || aVarK.b() == j;
                boolean z2 = str2.length() == 0 || Intrinsics.areEqual(aVarK.e(), str2);
                boolean z3 = j2 < 0 || aVarK.a() == j2;
                if (z && z2 && z3) {
                    return true;
                }
            }
            try {
                Thread.sleep(80L);
            } catch (InterruptedException unused) {
            }
        }
        Log.w("DebugHostCodeSlot", "verifySlotReady failed bytes=" + j + " runId=" + str2 + " gen=" + j2);
        return false;
    }

    public final boolean m(Context context, String str, long j, int i, long j2) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriC = c(str);
        long jCurrentTimeMillis = System.currentTimeMillis() + j2;
        boolean z = false;
        while (System.currentTimeMillis() < jCurrentTimeMillis) {
            Bundle bundleCall = null;
            try {
                bundleCall = contentResolver.call(uriC, "version", (String) null, (Bundle) null);
            } catch (Exception unused) {
            }
            if (bundleCall == null || !bundleCall.getBoolean("ok")) {
                z = true;
            } else {
                if (z) {
                    return true;
                }
                boolean z2 = j < 0 || bundleCall.getLong("applyGen", -1L) == j;
                int i2 = bundleCall.getInt("pid", -1);
                if (z2 && i > 0 && i2 > 0 && i2 != i) {
                    return true;
                }
            }
            try {
                Thread.sleep(60L);
            } catch (InterruptedException unused2) {
            }
        }
        return false;
    }

    public final boolean n(Context context, String str) {
        context.getClass();
        str.getClass();
        String strJ = j(context, str);
        if (strJ == null) {
            return false;
        }
        try {
            Bundle bundleCall = context.getApplicationContext().getContentResolver().call(c(strJ), "wipeAndDie", (String) null, (Bundle) null);
            return bundleCall != null && bundleCall.getBoolean("ok");
        } catch (Exception e) {
            Log.w("DebugHostCodeSlot", "wipeAndDie failed", e);
            return false;
        }
    }
}
