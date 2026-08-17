package defpackage;

import android.util.Log;
import com.intellij.psi.PsiKeyword;
import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class bl3 {
    public static final bl3 a = new bl3();
    public static final List b = CollectionsKt.listOf(new String[]{"https://maven.aliyun.com/repository/public", "https://maven.aliyun.com/repository/google", "https://maven.aliyun.com/repository/central", "https://repo.huaweicloud.com/repository/maven", "https://mirrors.cloud.tencent.com/nexus/repository/maven-public", "https://repo1.maven.org/maven2", "https://dl.google.com/dl/android/maven2"});
    public static final Set c = SetsKt.setOf(new String[]{"compile", "runtime"});
    public static final Regex d = new Regex("(?:implementation|api|compileOnly|runtimeOnly|annotationProcessor)\\s*\\(?\\s*[\"']([^\"']+)[\"']");
    public static final int e = 8;

    public static final class a {
        public final String a;
        public final String b;
        public final String c;

        public a(String str, String str2, String str3) {
            str.getClass();
            str2.getClass();
            str3.getClass();
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public final String a(String str) {
            str.getClass();
            return this.b + "-" + this.c + Constants.ATTRVAL_THIS + str;
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
            return StringsKt.replace$default(this.a, '.', '/', false, 4, (Object) null) + PsuedoNames.PSEUDONAME_ROOT + this.b + PsuedoNames.PSEUDONAME_ROOT + this.c;
        }

        public final String d() {
            return this.a + ":" + this.b;
        }

        public final String e() {
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
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c);
        }

        public final String f() {
            return this.c;
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public String toString() {
            return this.a + ":" + this.b + ":" + this.c;
        }
    }

    public enum b {
        OK,
        FAILED,
        RETRYABLE;

        public static final /* synthetic */ EnumEntries f = EnumEntriesKt.enumEntries(b());
    }

    public enum c {
        OK,
        NOT_FOUND,
        FAILED;

        public static final /* synthetic */ EnumEntries f = EnumEntriesKt.enumEntries(b());
    }

    public static final class d {
        public final String a;
        public final Map b;
        public final List c;

        public d(String str, Map map, List list) {
            str.getClass();
            map.getClass();
            list.getClass();
            this.a = str;
            this.b = map;
            this.c = list;
        }

        public final List a() {
            return this.c;
        }

        public final Map b() {
            return this.b;
        }

        public final String c() {
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
            return Intrinsics.areEqual(this.a, dVar.a) && Intrinsics.areEqual(this.b, dVar.b) && Intrinsics.areEqual(this.c, dVar.c);
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "EffectivePom(packaging=" + this.a + ", managed=" + this.b + ", dependencies=" + this.c + ")";
        }
    }

    public static final class e {
        public final a a;
        public final Set b;

        public e(a aVar, Set set) {
            aVar.getClass();
            set.getClass();
            this.a = aVar;
            this.b = set;
        }

        public final a a() {
            return this.a;
        }

        public final Set b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof e)) {
                return false;
            }
            e eVar = (e) obj;
            return Intrinsics.areEqual(this.a, eVar.a) && Intrinsics.areEqual(this.b, eVar.b);
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }

        public String toString() {
            return "GraphNode(coord=" + this.a + ", exclusions=" + this.b + ")";
        }
    }

    public static final class g {
        public final String a;
        public final String b;
        public final String c;
        public final String d;
        public final boolean e;
        public final String f;
        public final Set g;

        public g(String str, String str2, String str3, String str4, boolean z, String str5, Set set) {
            str.getClass();
            str2.getClass();
            str4.getClass();
            str5.getClass();
            set.getClass();
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = z;
            this.f = str5;
            this.g = set;
        }

        public final String a() {
            return this.b;
        }

        public final Set b() {
            return this.g;
        }

        public final String c() {
            return this.a + ":" + this.b;
        }

        public final String d() {
            return this.a;
        }

        public final boolean e() {
            return this.e;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof g)) {
                return false;
            }
            g gVar = (g) obj;
            return Intrinsics.areEqual(this.a, gVar.a) && Intrinsics.areEqual(this.b, gVar.b) && Intrinsics.areEqual(this.c, gVar.c) && Intrinsics.areEqual(this.d, gVar.d) && this.e == gVar.e && Intrinsics.areEqual(this.f, gVar.f) && Intrinsics.areEqual(this.g, gVar.g);
        }

        public final String f() {
            return this.d;
        }

        public final String g() {
            return this.f;
        }

        public final String h() {
            return this.c;
        }

        public int hashCode() {
            int iHashCode = ((this.a.hashCode() * 31) + this.b.hashCode()) * 31;
            String str = this.c;
            return ((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.d.hashCode()) * 31) + Boolean.hashCode(this.e)) * 31) + this.f.hashCode()) * 31) + this.g.hashCode();
        }

        public String toString() {
            return "RawDep(group=" + this.a + ", artifact=" + this.b + ", version=" + this.c + ", scope=" + this.d + ", optional=" + this.e + ", type=" + this.f + ", exclusions=" + this.g + ")";
        }
    }

    public static final class i {
        public final String a;
        public final String b;
        public final List c;
        public final File d;
        public final File e;
        public final File f;
        public final File g;
        public final File h;

        public i(String str, String str2, List list, File file, File file2, File file3, File file4, File file5) {
            str.getClass();
            list.getClass();
            this.a = str;
            this.b = str2;
            this.c = list;
            this.d = file;
            this.e = file2;
            this.f = file3;
            this.g = file4;
            this.h = file5;
        }

        public final File a() {
            return this.e;
        }

        public final List b() {
            return this.c;
        }

        public final String c() {
            return this.a;
        }

        public final File d() {
            return this.f;
        }

        public final File e() {
            return this.g;
        }

        public final String f() {
            return this.b;
        }

        public final File g() {
            return this.d;
        }
    }

    public static final class j {
        public final List a;
        public final List b;
        public final List c;
        public final List d;

        public j(List list, List list2, List list3, List list4) {
            list.getClass();
            list2.getClass();
            list3.getClass();
            list4.getClass();
            this.a = list;
            this.b = list2;
            this.c = list3;
            this.d = list4;
        }

        public final List a() {
            return this.b;
        }

        public final List b() {
            List list = this.b;
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                File fileA = ((i) it.next()).a();
                if (fileA != null) {
                    arrayList.add(fileA);
                }
            }
            return arrayList;
        }

        public final List c() {
            List list = this.a;
            List list2 = this.b;
            ArrayList arrayList = new ArrayList();
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((i) it.next()).b());
            }
            return CollectionsKt.plus(list, arrayList);
        }

        public final List d() {
            return this.d;
        }

        public final List e() {
            return this.a;
        }

        public final List f() {
            List list = this.b;
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                File fileD = ((i) it.next()).d();
                if (fileD != null) {
                    arrayList.add(fileD);
                }
            }
            return arrayList;
        }

        public final List g() {
            return this.c;
        }

        public final List h() {
            List list = this.b;
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                File fileE = ((i) it.next()).e();
                if (fileE != null) {
                    arrayList.add(fileE);
                }
            }
            return arrayList;
        }

        public final List i() {
            List list = this.b;
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                File fileG = ((i) it.next()).g();
                if (fileG != null) {
                    arrayList.add(fileG);
                }
            }
            return arrayList;
        }
    }

    public static final class l {
        public final int a;
        public final int b;
        public final List c;

        public l(int i, int i2, List list) {
            list.getClass();
            this.a = i;
            this.b = i2;
            this.c = list;
        }

        public static /* synthetic */ l b(l lVar, int i, int i2, List list, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = lVar.a;
            }
            if ((i3 & 2) != 0) {
                i2 = lVar.b;
            }
            if ((i3 & 4) != 0) {
                list = lVar.c;
            }
            return lVar.a(i, i2, list);
        }

        public final l a(int i, int i2, List list) {
            list.getClass();
            return new l(i, i2, list);
        }

        public final int c() {
            return this.a;
        }

        public final int d() {
            return this.b;
        }

        public final boolean e() {
            return this.b > 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof l)) {
                return false;
            }
            l lVar = (l) obj;
            return this.a == lVar.a && this.b == lVar.b && Intrinsics.areEqual(this.c, lVar.c);
        }

        public int hashCode() {
            return (((Integer.hashCode(this.a) * 31) + Integer.hashCode(this.b)) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "WarmupPlan(artifactTotal=" + this.a + ", missingCount=" + this.b + ", errors=" + this.c + ")";
        }
    }

    public static final /* synthetic */ class m {
        public static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[c.values().length];
            try {
                iArr[c.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[c.NOT_FOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[c.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            a = iArr;
            int[] iArr2 = new int[b.values().length];
            try {
                iArr2[b.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[b.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[b.RETRYABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            b = iArr2;
        }
    }

    public static final class n implements Comparator {
        public final /* synthetic */ HashMap b;

        public n(HashMap map) {
            this.b = map;
        }

        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            Integer num = (Integer) this.b.get((File) obj2);
            Integer numValueOf = Integer.valueOf(num != null ? num.intValue() : 0);
            Integer num2 = (Integer) this.b.get((File) obj);
            return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(num2 != null ? num2.intValue() : 0));
        }
    }

    public static final class o implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(((File) obj).getName(), ((File) obj2).getName());
        }
    }

    public static /* synthetic */ j U(bl3 bl3Var, List list, File file, Function1 function1, Function1 function2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function1 = new Function1() { // from class: tk3
                public final Object invoke(Object obj2) {
                    return bl3.a((String) obj2);
                }
            };
        }
        if ((i2 & 8) != 0) {
            function2 = null;
        }
        return bl3Var.T(list, file, function1, function2);
    }

    public static final void W(Function1 function1, Ref.IntRef intRef, int i2, String str, int i3, long j2, long j3, boolean z) {
        if (function1 != null) {
            function1.invoke(new f("download", str, RangesKt.coerceIn(i3, 0, 100), intRef.element, i2, j2, j3, z));
        }
    }

    public static /* synthetic */ void X(Function1 function1, Ref.IntRef intRef, int i2, String str, int i3, long j2, long j3, boolean z, int i4, Object obj) {
        if ((i4 & 32) != 0) {
            j2 = -1;
        }
        if ((i4 & 64) != 0) {
            j3 = -1;
        }
        if ((i4 & 128) != 0) {
            z = false;
        }
        W(function1, intRef, i2, str, i3, j2, j3, z);
    }

    public static final Function2 Y(final Function1 function1, final Ref.IntRef intRef, final int i2, final a aVar) {
        if (function1 == null) {
            return null;
        }
        return new Function2() { // from class: rk3
            public final Object invoke(Object obj, Object obj2) {
                return bl3.e(aVar, function1, intRef, i2, ((Long) obj).longValue(), ((Long) obj2).longValue());
            }
        };
    }

    public static Unit a(String str) {
        str.getClass();
        return Unit.INSTANCE;
    }

    public static boolean b(File file) {
        return file.isFile() && StringsKt.equals(FilesKt.getExtension(file), "jar", true);
    }

    public static String c(ZipEntry zipEntry) {
        return zipEntry.getName();
    }

    public static Unit d(Ref.BooleanRef booleanRef) {
        booleanRef.element = true;
        return Unit.INSTANCE;
    }

    public static Unit e(a aVar, Function1 function1, Ref.IntRef intRef, int i2, long j2, long j3) {
        if (j2 < 0) {
            W(function1, intRef, i2, aVar + " · 连接", 0, 0L, 0L, false);
        } else {
            W(function1, intRef, i2, aVar.toString(), j3 > 0 ? RangesKt.coerceIn((int) ((j2 * 100.0d) / j3), 0, 99) : 0, j2, j3, false);
        }
        return Unit.INSTANCE;
    }

    public static boolean f(ZipEntry zipEntry) {
        if (!zipEntry.isDirectory()) {
            String name = zipEntry.getName();
            name.getClass();
            if (StringsKt.endsWith$default(name, JavaClass.EXTENSION, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static Unit g(Ref.BooleanRef booleanRef) {
        booleanRef.element = true;
        return Unit.INSTANCE;
    }

    public static Unit h(Ref.BooleanRef booleanRef) {
        booleanRef.element = true;
        return Unit.INSTANCE;
    }

    public static a i(List list, MatchResult matchResult) {
        matchResult.getClass();
        bl3 bl3Var = a;
        Object obj = matchResult.getGroupValues().get(1);
        obj.getClass();
        return bl3Var.L(StringsKt.trim((String) obj).toString(), list);
    }

    public static Unit j(Ref.BooleanRef booleanRef) {
        booleanRef.element = true;
        return Unit.INSTANCE;
    }

    public static final Pair s(String str) {
        int iIndexOf$default = StringsKt.indexOf$default(str, LocaleUtility.IETF_SEPARATOR, 0, false, 6, (Object) null);
        String strSubstring = iIndexOf$default >= 0 ? str.substring(0, iIndexOf$default) : str;
        String strSubstring2 = iIndexOf$default >= 0 ? str.substring(iIndexOf$default + 1) : "";
        List listSplit$default = StringsKt.split$default(strSubstring, new char[]{'.'}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull(StringsKt.trim((String) it.next()).toString());
            arrayList.add(Integer.valueOf(intOrNull != null ? intOrNull.intValue() : 0));
        }
        return TuplesKt.to(arrayList, strSubstring2);
    }

    public static final Set w(File file) {
        try {
            ZipFile zipFile = new ZipFile(file);
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                enumerationEntries.getClass();
                HashSet hashSet = SequencesKt.toHashSet(SequencesKt.map(SequencesKt.filter(SequencesKt.asSequence(CollectionsKt.iterator(enumerationEntries)), new Function1() { // from class: uk3
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(bl3.f((ZipEntry) obj));
                    }
                }), new Function1() { // from class: vk3
                    public final Object invoke(Object obj) {
                        return bl3.c((ZipEntry) obj);
                    }
                }));
                CloseableKt.closeFinally(zipFile, (Throwable) null);
                return hashSet;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(zipFile, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            Log.w("DependencyResolver", "could not read classes from " + file.getName(), th3);
            return SetsKt.emptySet();
        }
    }

    public static /* synthetic */ File z(bl3 bl3Var, a aVar, String str, File file, List list, Function2 function2, Function0 function0, boolean z, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            function2 = null;
        }
        if ((i2 & 32) != 0) {
            function0 = null;
        }
        if ((i2 & 64) != 0) {
            z = false;
        }
        return bl3Var.y(aVar, str, file, list, function2, function0, z);
    }

    public final b A(String str, File file, String str2, Function2 function2, int i2, int i3) {
        File file2 = new File(file.getParentFile(), file.getName() + ".part");
        HttpURLConnection httpURLConnection = null;
        try {
            URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
            uRLConnectionOpenConnection.getClass();
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) uRLConnectionOpenConnection;
            httpURLConnection2.setConnectTimeout(i2);
            httpURLConnection2.setReadTimeout(i3);
            httpURLConnection2.setInstanceFollowRedirects(true);
            httpURLConnection2.setRequestMethod("GET");
            try {
                int responseCode = httpURLConnection2.getResponseCode();
                if (responseCode != 200) {
                    b bVar = responseCode >= 500 ? b.RETRYABLE : b.FAILED;
                    httpURLConnection2.disconnect();
                    return bVar;
                }
                long jCoerceAtLeast = RangesKt.coerceAtLeast(httpURLConnection2.getContentLengthLong(), -1L);
                InputStream inputStream = httpURLConnection2.getInputStream();
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        bl3 bl3Var = a;
                        inputStream.getClass();
                        bl3Var.t(inputStream, fileOutputStream, jCoerceAtLeast, function2);
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                        CloseableKt.closeFinally(inputStream, (Throwable) null);
                        if (file2.length() != 0 && I(file2, str2)) {
                            if (J(file2, file)) {
                                b bVar2 = b.OK;
                                httpURLConnection2.disconnect();
                                return bVar2;
                            }
                            Log.w("DependencyResolver", "could not move " + file2.getName() + " into place");
                            file2.delete();
                            b bVar3 = b.RETRYABLE;
                            httpURLConnection2.disconnect();
                            return bVar3;
                        }
                        file2.delete();
                        b bVar4 = b.RETRYABLE;
                        httpURLConnection2.disconnect();
                        return bVar4;
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
                        CloseableKt.closeFinally(inputStream, th3);
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                httpURLConnection = httpURLConnection2;
                try {
                    Log.w("DependencyResolver", "download failed: " + str, th);
                    file2.delete();
                    return b.RETRYABLE;
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }

    /* JADX WARN: Code duplicated, block: B:74:0x013a  */
    /* JADX WARN: Code duplicated, block: B:86:0x0159  */
    /* JADX WARN: Code duplicated, block: B:97:0x0177  */
    public final i B(File file, a aVar, List list) {
        File file2;
        File file3;
        File file4;
        File[] fileArrListFiles;
        File[] fileArrListFiles2;
        File[] fileArrListFiles3;
        List listSortedWith;
        File file5 = new File(file.getParentFile(), "exploded");
        File file6 = new File(file5, ".extracted");
        if (!file6.isFile()) {
            FilesKt.deleteRecursively(file5);
            file5.mkdirs();
            try {
                ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file), 8192));
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    while (nextEntry != null) {
                        File file7 = new File(file5, nextEntry.getName());
                        String canonicalPath = file7.getCanonicalPath();
                        canonicalPath.getClass();
                        if (StringsKt.startsWith$default(canonicalPath, file5.getCanonicalPath() + File.separator, false, 2, (Object) null)) {
                            if (nextEntry.isDirectory()) {
                                file7.mkdirs();
                            } else {
                                File parentFile = file7.getParentFile();
                                if (parentFile != null) {
                                    parentFile.mkdirs();
                                }
                                FileOutputStream fileOutputStream = new FileOutputStream(file7);
                                try {
                                    ByteStreamsKt.copyTo$default(zipInputStream, fileOutputStream, 0, 2, (Object) null);
                                    CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                                } catch (Throwable th) {
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        CloseableKt.closeFinally(fileOutputStream, th);
                                        throw th2;
                                    }
                                }
                            }
                            nextEntry = zipInputStream.getNextEntry();
                        } else {
                            nextEntry = zipInputStream.getNextEntry();
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(zipInputStream, (Throwable) null);
                    FilesKt.writeText$default(file6, "ok", (Charset) null, 2, (Object) null);
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(zipInputStream, th3);
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                Log.e("DependencyResolver", "aar explode failed: " + aVar, th5);
                return null;
            }
        }
        ArrayList arrayList = new ArrayList();
        File file8 = new File(file5, "classes.jar");
        if (!file8.isFile()) {
            file8 = null;
        }
        if (file8 != null) {
            arrayList.add(file8);
        }
        File[] fileArrListFiles4 = new File(file5, "libs").listFiles(new FileFilter() { // from class: sk3
            @Override // java.io.FileFilter
            public final boolean accept(File file9) {
                return bl3.b(file9);
            }
        });
        if (fileArrListFiles4 != null && (listSortedWith = ArraysKt.sortedWith(fileArrListFiles4, new o())) != null) {
            arrayList.addAll(listSortedWith);
        }
        File file9 = new File(file5, "AndroidManifest.xml");
        File file10 = file9.isFile() ? file9 : null;
        String string = aVar.toString();
        String strR = file10 != null ? a.R(file10) : null;
        File file11 = new File(file5, "res");
        if (!file11.isDirectory() || (fileArrListFiles3 = file11.listFiles()) == null) {
            file2 = null;
        } else {
            if (!(fileArrListFiles3.length == 0)) {
                file2 = file11;
            } else {
                file2 = null;
            }
        }
        File file12 = new File(file5, "assets");
        if (!file12.isDirectory() || (fileArrListFiles2 = file12.listFiles()) == null) {
            file3 = null;
        } else {
            if (!(fileArrListFiles2.length == 0)) {
                file3 = file12;
            } else {
                file3 = null;
            }
        }
        File file13 = new File(file5, "jni");
        if (!file13.isDirectory() || (fileArrListFiles = file13.listFiles()) == null) {
            file4 = null;
        } else {
            if (!(fileArrListFiles.length == 0)) {
                file4 = file13;
            } else {
                file4 = null;
            }
        }
        File file14 = new File(file5, "R.txt");
        return new i(string, strR, arrayList, file2, file3, file4, file10, (!file14.isFile() || file14.length() <= 0) ? null : file14);
    }

    public final boolean C(a aVar, File file) {
        for (Object obj : CollectionsKt.listOf(new String[]{"aar", "jar"})) {
            obj.getClass();
            String str = (String) obj;
            File file2 = new File(file, aVar.c() + PsuedoNames.PSEUDONAME_ROOT + aVar.a(str));
            if (file2.isFile() && file2.length() > 0 && I(file2, str)) {
                return true;
            }
        }
        return false;
    }

    public final String D(String str) {
        try {
            String host = new URL(str).getHost();
            host.getClass();
            return host;
        } catch (Throwable unused) {
            return str;
        }
    }

    public final boolean E(String str, Set set) {
        if (set.isEmpty()) {
            return false;
        }
        if (set.contains(str)) {
            return true;
        }
        String strSubstringBefore$default = StringsKt.substringBefore$default(str, ':', (String) null, 2, (Object) null);
        StringBuilder sb = new StringBuilder();
        sb.append(strSubstringBefore$default);
        sb.append(":*");
        return set.contains(sb.toString()) || set.contains("*:*");
    }

    public final boolean F(File file) {
        File file2 = new File(file.getParentFile(), file.getName() + ".has_classes");
        boolean z = false;
        boolean z2 = true;
        if (file2.isFile()) {
            String string = StringsKt.trim(FilesKt.readText$default(file2, (Charset) null, 1, (Object) null)).toString();
            return Intrinsics.areEqual(string, "1") || !Intrinsics.areEqual(string, "0");
        }
        ZipFile zipFile = new ZipFile(file);
        try {
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            enumerationEntries.getClass();
            for (ZipEntry zipEntry : SequencesKt.asSequence(CollectionsKt.iterator(enumerationEntries))) {
                if (!zipEntry.isDirectory()) {
                    String name = zipEntry.getName();
                    name.getClass();
                    if (StringsKt.endsWith$default(name, JavaClass.EXTENSION, false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                }
            }
            CloseableKt.closeFinally(zipFile, (Throwable) null);
            z2 = z;
            try {
                Result.Companion companion = Result.Companion;
                FilesKt.writeText$default(file2, z2 ? "1" : "0", (Charset) null, 2, (Object) null);
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.constructor-impl(ResultKt.createFailure(th));
            }
            return z2;
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(zipFile, th2);
                throw th3;
            }
        }
    }

    public final a G(a aVar, String str) {
        String strB = aVar.b();
        if (StringsKt.endsWith$default(strB, "-" + str, false, 2, (Object) null) || StringsKt.endsWith$default(strB, "-common", false, 2, (Object) null) || StringsKt.endsWith$default(strB, "-metadata", false, 2, (Object) null) || StringsKt.contains$default(strB, "-common-", false, 2, (Object) null) || StringsKt.endsWith$default(strB, "-jvm", false, 2, (Object) null) || StringsKt.endsWith$default(strB, "-android", false, 2, (Object) null) || StringsKt.endsWith$default(strB, "-js", false, 2, (Object) null) || StringsKt.endsWith$default(strB, "-native", false, 2, (Object) null) || StringsKt.endsWith$default(strB, "-ios", false, 2, (Object) null) || StringsKt.endsWith$default(strB, "-wasm-js", false, 2, (Object) null)) {
            return null;
        }
        return new a(aVar.e(), strB + "-" + str, aVar.f());
    }

    public final d H(a aVar, h hVar, Set set) {
        String string = aVar.toString();
        if (hVar.d().containsKey(string)) {
            return (d) hVar.d().get(string);
        }
        d dVarL = null;
        if (!set.add(string)) {
            return null;
        }
        try {
            dVarL = l(aVar, hVar, set);
        } catch (Throwable th) {
            Log.w("DependencyResolver", "pom parse failed: " + aVar, th);
            hVar.b().add("could not parse POM for " + aVar + " (" + th.getMessage() + "); treating as leaf jar");
        }
        hVar.d().put(string, dVarL);
        return dVarL;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0057  */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0071, code lost:
    
        if (r7.equals("aar") == false) goto L38;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean I(File file, String str) {
        byte b2;
        BufferedReader bufferedReader;
        int i2;
        try {
            String lowerCase = str.toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            switch (lowerCase.hashCode()) {
                case 96338:
                    break;
                case 104987:
                    if (!lowerCase.equals("jar")) {
                        return true;
                    }
                    byte[] bArr = new byte[4];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        int i3 = fileInputStream.read(bArr);
                        CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                        return i3 == 4 && bArr[0] == 80 && bArr[1] == 75 && (((b2 = bArr[2]) == 3 && bArr[3] == 4) || (b2 == 5 && bArr[3] == 6));
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(fileInputStream, th);
                            throw th2;
                        }
                    }
                case 111182:
                    if (!lowerCase.equals("pom")) {
                        return true;
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8), 8192);
                    try {
                        i2 = bufferedReader.read();
                        while (i2 >= 0 && CharsKt.isWhitespace((char) i2)) {
                            i2 = bufferedReader.read();
                        }
                        boolean z = ((char) i2) == '<';
                        CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                        return z;
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(bufferedReader, th3);
                            throw th4;
                        }
                    }
                case 118807:
                    if (!lowerCase.equals("xml")) {
                        return true;
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8), 8192);
                    i2 = bufferedReader.read();
                    while (i2 >= 0) {
                        i2 = bufferedReader.read();
                    }
                    if (((char) i2) == '<') {
                    }
                    CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                    return z;
                default:
                    return true;
            }
        } catch (Throwable th5) {
            Log.w("DependencyResolver", "integrity check failed: " + file.getName(), th5);
            return false;
        }
    }

    public final boolean J(File file, File file2) {
        if (file.renameTo(file2)) {
            return true;
        }
        try {
            FilesKt.copyTo$default(file, file2, true, 0, 4, (Object) null);
            file.delete();
            return true;
        } catch (Throwable th) {
            Log.w("DependencyResolver", "copy fallback failed: " + file2.getName(), th);
            file2.delete();
            return false;
        }
    }

    public final String K(String str) {
        String string = StringsKt.trim(str).toString();
        if (string.length() == 0) {
            return string;
        }
        if (!StringsKt.startsWith$default(string, "[", false, 2, (Object) null) && !StringsKt.startsWith$default(string, "(", false, 2, (Object) null)) {
            return string;
        }
        String string2 = StringsKt.trim(StringsKt.trim(string, new char[]{'[', ']', '(', ')'})).toString();
        String string3 = StringsKt.trim(StringsKt.substringBefore$default(string2, ',', (String) null, 2, (Object) null)).toString();
        if (string3.length() > 0) {
            return string3;
        }
        String string4 = StringsKt.trim(StringsKt.substringAfter(string2, ',', "")).toString();
        return string4.length() == 0 ? string : string4;
    }

    public final a L(String str, List list) {
        if (StringsKt.isBlank(str)) {
            return null;
        }
        List listSplit$default = StringsKt.split$default(str, new char[]{':'}, false, 0, 6, (Object) null);
        if (listSplit$default.size() == 3) {
            List list2 = listSplit$default;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    if (StringsKt.isBlank((String) it.next())) {
                    }
                }
            }
            Object obj = listSplit$default.get(0);
            obj.getClass();
            String string = StringsKt.trim((String) obj).toString();
            Object obj2 = listSplit$default.get(1);
            obj2.getClass();
            String string2 = StringsKt.trim((String) obj2).toString();
            Object obj3 = listSplit$default.get(2);
            obj3.getClass();
            return new a(string, string2, StringsKt.trim((String) obj3).toString());
        }
        list.add("invalid coordinate '" + str + "' (expected group:artifact:version)");
        return null;
    }

    public final g M(Element element, Map map) {
        bl3 bl3Var;
        String strA0;
        String strK;
        String strO = o(element, "groupId");
        if (strO == null) {
            strO = "";
        }
        String strA1 = a0(strO, map);
        String strO2 = o(element, "artifactId");
        String strA2 = a0(strO2 != null ? strO2 : "", map);
        String strO3 = o(element, "version");
        String str = (strO3 == null || (strA0 = (bl3Var = a).a0(strO3, map)) == null || (strK = bl3Var.K(strA0)) == null || StringsKt.isBlank(strK)) ? null : strK;
        String strO4 = o(element, "scope");
        if (strO4 == null) {
            strO4 = "compile";
        }
        String string = StringsKt.trim(strO4).toString();
        String str2 = string.length() != 0 ? string : "compile";
        String strO5 = o(element, SchemaSymbols.ATTVAL_OPTIONAL);
        boolean zEquals = StringsKt.equals(strO5 != null ? StringsKt.trim(strO5).toString() : null, "true", true);
        String strO6 = o(element, "type");
        if (strO6 == null) {
            strO6 = "jar";
        }
        String string2 = StringsKt.trim(strO6).toString();
        String str3 = string2.length() == 0 ? "jar" : string2;
        HashSet hashSet = new HashSet();
        Element elementM = m(element, "exclusions");
        if (elementM != null) {
            for (Element element2 : a.n(elementM, "exclusion")) {
                bl3 bl3Var2 = a;
                String strO7 = bl3Var2.o(element2, "groupId");
                String str4 = "*";
                if (strO7 == null) {
                    strO7 = "*";
                }
                String strA3 = bl3Var2.a0(strO7, map);
                String strO8 = bl3Var2.o(element2, "artifactId");
                if (strO8 != null) {
                    str4 = strO8;
                }
                hashSet.add(strA3 + ":" + bl3Var2.a0(str4, map));
            }
        }
        return new g(strA1, strA2, str, str2, zEquals, str3, hashSet);
    }

    public final Element N(File file) {
        try {
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setNamespaceAware(false);
            documentBuilderFactoryNewInstance.setValidating(false);
            try {
                documentBuilderFactoryNewInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            } catch (Throwable unused) {
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                Element documentElement = documentBuilderFactoryNewInstance.newDocumentBuilder().parse(fileInputStream).getDocumentElement();
                CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                return documentElement;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            Log.w("DependencyResolver", "XML parse failed: " + file.getName(), th3);
            return null;
        }
    }

    public final l O(File file, File file2) {
        String strC;
        file.getClass();
        file2.getClass();
        ArrayList arrayList = new ArrayList();
        List listQ = Q(file, arrayList);
        if (listQ.isEmpty()) {
            return new l(0, 0, arrayList);
        }
        h hVar = new h(file2, new ArrayList(), arrayList, null, 8, null);
        try {
            LinkedHashMap linkedHashMapZ = Z(listQ, hVar);
            int i2 = 0;
            for (Object obj : linkedHashMapZ.values()) {
                obj.getClass();
                a aVar = (a) obj;
                d dVar = (d) hVar.d().get(String.valueOf(aVar));
                if (dVar == null || (strC = dVar.c()) == null) {
                    strC = "jar";
                }
                String str = StringsKt.equals(strC, "aar", true) ? "aar" : "jar";
                File file3 = new File(file2, aVar.c() + PsuedoNames.PSEUDONAME_ROOT + aVar.a(str));
                if (!file3.isFile() || file3.length() == 0 || !I(file3, str)) {
                    i2++;
                }
            }
            return new l(linkedHashMapZ.size(), i2, CollectionsKt.toList(arrayList));
        } catch (Throwable th) {
            Log.e("DependencyResolver", "plan failed", th);
            String message = th.getMessage();
            if (message == null) {
                message = th.getClass().getSimpleName();
            }
            arrayList.add(message);
            return new l(0, 1, CollectionsKt.toList(arrayList));
        }
    }

    public final l P(File file, File file2) {
        file.getClass();
        file2.getClass();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        if (!new File(file, "build.gradle").isFile()) {
            arrayList.add("build.gradle missing: " + file.getAbsolutePath());
            Log.w("DependencyResolver", "planLocal: " + CollectionsKt.last(arrayList));
            return new l(0, 0, arrayList);
        }
        List listQ = Q(file, arrayList);
        if (listQ.isEmpty()) {
            Log.i("DependencyResolver", "planLocal: no declared dependencies in " + file.getName());
            return new l(0, 0, arrayList);
        }
        Iterator it = listQ.iterator();
        while (it.hasNext()) {
            if (!C((a) it.next(), file2)) {
                i2++;
            }
        }
        Log.i("DependencyResolver", "planLocal: " + file.getName() + " roots=" + listQ.size() + " missing=" + i2);
        return new l(listQ.size(), i2, arrayList);
    }

    public final List Q(File file, final List list) {
        File file2 = new File(file, "build.gradle");
        if (!file2.isFile()) {
            return CollectionsKt.emptyList();
        }
        try {
            return SequencesKt.toList(SequencesKt.mapNotNull(Regex.findAll$default(d, FilesKt.readText$default(file2, (Charset) null, 1, (Object) null), 0, 2, (Object) null), new Function1() { // from class: wk3
                public final Object invoke(Object obj) {
                    return bl3.i(list, (MatchResult) obj);
                }
            }));
        } catch (Throwable th) {
            list.add("could not read dependencies: " + th.getMessage());
            return CollectionsKt.emptyList();
        }
    }

    public final String R(File file) {
        String attribute;
        String string;
        try {
            Element elementN = N(file);
            if (elementN == null || (attribute = elementN.getAttribute(PsiKeyword.PACKAGE)) == null || (string = StringsKt.trim(attribute).toString()) == null || string.length() <= 0) {
                return null;
            }
            return string;
        } catch (Throwable th) {
            Log.w("DependencyResolver", "could not read package from " + file.getPath(), th);
            return null;
        }
    }

    public final j S(File file, File file2, Function1 function1, Function1 function2) {
        file.getClass();
        file2.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        List listQ = Q(file, arrayList);
        return listQ.isEmpty() ? new j(CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), arrayList) : V(listQ, file2, arrayList, function1, function2);
    }

    public final j T(List list, File file, Function1 function1, Function1 function2) {
        list.getClass();
        file.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            a aVarL = a.L((String) it.next(), arrayList);
            if (aVarL != null) {
                arrayList2.add(aVarL);
            }
        }
        return arrayList2.isEmpty() ? new j(CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), arrayList) : V(arrayList2, file, arrayList, function1, function2);
    }

    /* JADX WARN: Code duplicated, block: B:111:0x034b A[Catch: all -> 0x033b, TryCatch #13 {all -> 0x033b, blocks: (B:105:0x0336, B:111:0x034b, B:120:0x0386, B:140:0x040c), top: B:245:0x0336 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x0386 A[Catch: all -> 0x033b, TryCatch #13 {all -> 0x033b, blocks: (B:105:0x0336, B:111:0x034b, B:120:0x0386, B:140:0x040c), top: B:245:0x0336 }] */
    /* JADX WARN: Code duplicated, block: B:253:0x02a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:267:0x0225 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x01df A[Catch: all -> 0x01d5, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x01d5, blocks: (B:52:0x01b2, B:58:0x01df), top: B:269:0x01b2 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x0211 A[Catch: all -> 0x037c, TRY_ENTER, TryCatch #14 {all -> 0x037c, blocks: (B:49:0x01a7, B:56:0x01d9, B:60:0x0211, B:62:0x021b), top: B:247:0x01a7 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x021b A[Catch: all -> 0x037c, TRY_LEAVE, TryCatch #14 {all -> 0x037c, blocks: (B:49:0x01a7, B:56:0x01d9, B:60:0x0211, B:62:0x021b), top: B:247:0x01a7 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x024f  */
    /* JADX WARN: Code duplicated, block: B:84:0x02a9 A[Catch: all -> 0x02be, TRY_LEAVE, TryCatch #17 {all -> 0x02be, blocks: (B:82:0x02a5, B:84:0x02a9, B:93:0x02f6, B:97:0x0300), top: B:253:0x02a5 }] */
    /* JADX WARN: Code duplicated, block: B:88:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:91:0x02d7 A[Catch: all -> 0x02fa, TRY_LEAVE, TryCatch #11 {all -> 0x02fa, blocks: (B:89:0x02c8, B:91:0x02d7), top: B:241:0x02c8 }] */
    /* JADX WARN: Code duplicated, block: B:96:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:99:0x0307  */
    /* JADX WARN: Instruction removed from duplicated block: B:111:0x034b, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:120:0x0386, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:58:0x01df, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:91:0x02d7, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 8 */
    public final j V(List list, File file, List list2, Function1 function1, Function1 function2) {
        String str;
        ArrayList arrayList;
        h hVar;
        a aVar;
        String str2;
        ArrayList arrayList2;
        String strC;
        String str3;
        ArrayList arrayList3;
        ArrayList arrayList4;
        a aVar2;
        i iVarB;
        a aVarG;
        final Ref.BooleanRef booleanRef;
        File fileY;
        i iVarB2;
        String str4;
        this = this;
        List list3 = list2;
        function2 = function2;
        String str5 = " · 检查";
        String str6 = "exploded/.extracted";
        k kVar = new k(function1);
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        h hVar2 = new h(file, kVar, list3, function2);
        kVar.add("Resolving " + list.size() + " declared dependency(ies) (transitive)...");
        if (function2 != null) {
            function2.invoke(new f("resolve", "解析依赖图…", 0, 0, 0, 0L, 0L, false, WinError.ERROR_FORMS_AUTH_REQUIRED, null));
        }
        LinkedHashMap linkedHashMapZ = this.Z(list, hVar2);
        int size = linkedHashMapZ.size() - list.size();
        if (size > 0) {
            kVar.add("Graph resolved: " + linkedHashMapZ.size() + " artifact(s) (+" + size + " transitive)");
        }
        boolean z = true;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(linkedHashMapZ.size(), 1);
        Ref.IntRef intRef = new Ref.IntRef();
        for (Object obj : linkedHashMapZ.values()) {
            obj.getClass();
            a aVar3 = (a) obj;
            intRef.element += z ? 1 : 0;
            boolean z2 = false;
            try {
                X(function2, intRef, iCoerceAtLeast, aVar3.toString(), 0, 0L, 0L, false, 96, null);
                d dVar = (d) hVar2.d().get(String.valueOf(aVar3));
                if (dVar == null || (strC = dVar.c()) == null) {
                    strC = "jar";
                }
                if (StringsKt.equals(strC, "aar", z)) {
                    try {
                        final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                        String str7 = str6;
                        ArrayList arrayList7 = arrayList5;
                        try {
                            File fileA = hVar2.a();
                            ArrayList arrayList8 = arrayList6;
                            try {
                                Function2 function2Y = Y(function2, intRef, iCoerceAtLeast, aVar3);
                                h hVar3 = hVar2;
                                try {
                                    Function0 function0 = new Function0() { // from class: xk3
                                        public final Object invoke() {
                                            return bl3.d(booleanRef2);
                                        }
                                    };
                                    str = str7;
                                    arrayList3 = arrayList7;
                                    arrayList = arrayList8;
                                    hVar = hVar3;
                                    z = (z ? 1 : 0) == true ? 1 : 0;
                                    str5 = str5;
                                    try {
                                        File fileZ = z(this, aVar3, "aar", fileA, kVar, function2Y, function0, false, 64, null);
                                        aVar2 = aVar3;
                                        try {
                                            boolean z3 = booleanRef2.element;
                                            if (fileZ == null) {
                                                try {
                                                    list3.add(aVar2 + ": no .aar found");
                                                    X(function2, intRef, iCoerceAtLeast, aVar2.toString(), 100, 0L, 0L, booleanRef2.element, 96, null);
                                                    str6 = str;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    z2 = z3;
                                                    list2 = list3;
                                                    arrayList2 = arrayList3;
                                                    aVar = aVar2;
                                                    str2 = str5;
                                                    Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                    list2.add(aVar + ": " + th.getMessage());
                                                    function2 = function2;
                                                    X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                                    arrayList5 = arrayList2;
                                                    str6 = str;
                                                    arrayList6 = arrayList;
                                                    hVar2 = hVar;
                                                    list3 = list2;
                                                    str5 = str2;
                                                }
                                            } else {
                                                if (z3) {
                                                    X(function2, intRef, iCoerceAtLeast, aVar2.toString(), 100, 0L, 0L, true, 96, null);
                                                }
                                                try {
                                                    try {
                                                        if (new File(fileZ.getParentFile(), str).isFile()) {
                                                            iVarB = this.B(fileZ, aVar2, kVar);
                                                            if (iVarB == null) {
                                                                list3.add(aVar2 + ": failed to explode .aar");
                                                                X(function2, intRef, iCoerceAtLeast, aVar2.toString(), 100, 0L, 0L, booleanRef2.element, 96, null);
                                                                str6 = str;
                                                            } else {
                                                                if (iVarB.b().isEmpty()) {
                                                                    aVarG = this.G(aVar2, "android");
                                                                    if (aVarG == null) {
                                                                        kVar.add(aVar2 + " (aar, no classes)");
                                                                        arrayList.add(iVarB);
                                                                        arrayList4 = arrayList;
                                                                        z2 = z3;
                                                                        str3 = str;
                                                                    } else {
                                                                        X(function2, intRef, iCoerceAtLeast, aVarG + " · 查找", 0, 0L, 0L, false, 96, null);
                                                                        booleanRef = new Ref.BooleanRef();
                                                                        str = str;
                                                                        arrayList = arrayList;
                                                                        z2 = z3;
                                                                        str3 = str;
                                                                        fileY = this.y(aVarG, "aar", hVar.a(), kVar, Y(function2, intRef, iCoerceAtLeast, aVarG), new Function0() { // from class: yk3
                                                                            public final Object invoke() {
                                                                                return bl3.g(booleanRef);
                                                                            }
                                                                        }, true);
                                                                        if (fileY != null) {
                                                                            if (booleanRef.element) {
                                                                                X(function2, intRef, iCoerceAtLeast, aVarG.toString(), 100, 0L, 0L, true, 96, null);
                                                                            }
                                                                            if (!new File(fileY.getParentFile(), str3).isFile()) {
                                                                                X(function2, intRef, iCoerceAtLeast, aVarG + " · 解压", 0, 0L, 0L, booleanRef.element, 96, null);
                                                                            }
                                                                            iVarB2 = a.B(fileY, aVarG, kVar);
                                                                        } else {
                                                                            iVarB2 = null;
                                                                        }
                                                                        if (iVarB2 != null) {
                                                                            arrayList4 = arrayList;
                                                                            kVar.add(aVar2 + " (aar, no classes)");
                                                                            arrayList4.add(iVarB);
                                                                        } else {
                                                                            arrayList4 = arrayList;
                                                                            kVar.add(aVar2 + " (aar, no classes)");
                                                                            arrayList4.add(iVarB);
                                                                        }
                                                                    }
                                                                } else {
                                                                    z2 = z3;
                                                                    arrayList4 = arrayList;
                                                                    str3 = str;
                                                                    kVar.add(aVar2 + " (aar)");
                                                                    arrayList4.add(iVarB);
                                                                }
                                                                this = this;
                                                                list2 = list2;
                                                                str = str3;
                                                                arrayList = arrayList4;
                                                                arrayList2 = arrayList3;
                                                                aVar = aVar2;
                                                                str2 = str5;
                                                                function2 = function2;
                                                                X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                                                arrayList5 = arrayList2;
                                                                str6 = str;
                                                                arrayList6 = arrayList;
                                                                hVar2 = hVar;
                                                                list3 = list2;
                                                                str5 = str2;
                                                            }
                                                            this = this;
                                                            list2 = list2;
                                                            str = str3;
                                                            arrayList2 = arrayList3;
                                                            aVar = aVar2;
                                                            str2 = str5;
                                                            Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                            list2.add(aVar + ": " + th.getMessage());
                                                            function2 = function2;
                                                            X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                                            arrayList5 = arrayList2;
                                                            str6 = str;
                                                            arrayList6 = arrayList;
                                                            hVar2 = hVar;
                                                            list3 = list2;
                                                            str5 = str2;
                                                        } else {
                                                            try {
                                                                X(function2, intRef, iCoerceAtLeast, aVar2 + " · 解压", 0, 0L, 0L, booleanRef2.element, 96, null);
                                                                iVarB = this.B(fileZ, aVar2, kVar);
                                                                if (iVarB == null) {
                                                                    list3.add(aVar2 + ": failed to explode .aar");
                                                                    X(function2, intRef, iCoerceAtLeast, aVar2.toString(), 100, 0L, 0L, booleanRef2.element, 96, null);
                                                                    str6 = str;
                                                                } else {
                                                                    if (iVarB.b().isEmpty()) {
                                                                        aVarG = this.G(aVar2, "android");
                                                                        if (aVarG == null) {
                                                                            try {
                                                                                kVar.add(aVar2 + " (aar, no classes)");
                                                                                try {
                                                                                    arrayList.add(iVarB);
                                                                                    arrayList4 = arrayList;
                                                                                    z2 = z3;
                                                                                    str3 = str;
                                                                                } catch (Throwable th2) {
                                                                                    th = th2;
                                                                                    str = str;
                                                                                    arrayList = arrayList;
                                                                                    z2 = z3;
                                                                                    list2 = list3;
                                                                                    arrayList2 = arrayList3;
                                                                                    aVar = aVar2;
                                                                                    str2 = str5;
                                                                                    Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                                                    list2.add(aVar + ": " + th.getMessage());
                                                                                }
                                                                            } catch (Throwable th3) {
                                                                                th = th3;
                                                                                str = str;
                                                                                z2 = z3;
                                                                                list2 = list3;
                                                                                arrayList2 = arrayList3;
                                                                                aVar = aVar2;
                                                                                str2 = str5;
                                                                                Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                                                list2.add(aVar + ": " + th.getMessage());
                                                                                function2 = function2;
                                                                                X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                                                                arrayList5 = arrayList2;
                                                                                str6 = str;
                                                                                arrayList6 = arrayList;
                                                                                hVar2 = hVar;
                                                                                list3 = list2;
                                                                                str5 = str2;
                                                                                z = z;
                                                                            }
                                                                        } else {
                                                                            try {
                                                                                try {
                                                                                    X(function2, intRef, iCoerceAtLeast, aVarG + " · 查找", 0, 0L, 0L, false, 96, null);
                                                                                    booleanRef = new Ref.BooleanRef();
                                                                                    str = str;
                                                                                    try {
                                                                                        arrayList = arrayList;
                                                                                        z2 = z3;
                                                                                        str3 = str;
                                                                                        try {
                                                                                            fileY = this.y(aVarG, "aar", hVar.a(), kVar, Y(function2, intRef, iCoerceAtLeast, aVarG), new Function0() { // from class: yk3
                                                                                                public final Object invoke() {
                                                                                                    return bl3.g(booleanRef);
                                                                                                }
                                                                                            }, true);
                                                                                            if (fileY != null) {
                                                                                                try {
                                                                                                    if (booleanRef.element) {
                                                                                                        X(function2, intRef, iCoerceAtLeast, aVarG.toString(), 100, 0L, 0L, true, 96, null);
                                                                                                    }
                                                                                                    try {
                                                                                                        if (!new File(fileY.getParentFile(), str3).isFile()) {
                                                                                                            X(function2, intRef, iCoerceAtLeast, aVarG + " · 解压", 0, 0L, 0L, booleanRef.element, 96, null);
                                                                                                        }
                                                                                                        iVarB2 = a.B(fileY, aVarG, kVar);
                                                                                                    } catch (Throwable th4) {
                                                                                                        th = th4;
                                                                                                        this = this;
                                                                                                        list2 = list2;
                                                                                                        str = str3;
                                                                                                        arrayList2 = arrayList3;
                                                                                                        aVar = aVar2;
                                                                                                        str2 = str5;
                                                                                                        Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                                                                        list2.add(aVar + ": " + th.getMessage());
                                                                                                    }
                                                                                                } catch (Throwable th5) {
                                                                                                    th = th5;
                                                                                                }
                                                                                            } else {
                                                                                                iVarB2 = null;
                                                                                            }
                                                                                            if (iVarB2 != null || iVarB2.b().isEmpty()) {
                                                                                                arrayList4 = arrayList;
                                                                                                kVar.add(aVar2 + " (aar, no classes)");
                                                                                                arrayList4.add(iVarB);
                                                                                            } else {
                                                                                                kVar.add(aVar2 + ": empty KMP shell, using " + aVarG.b() + " for Android classes");
                                                                                                arrayList4 = arrayList;
                                                                                                try {
                                                                                                    arrayList4.add(iVarB2);
                                                                                                } catch (Throwable th6) {
                                                                                                    th = th6;
                                                                                                    str = str3;
                                                                                                    arrayList = arrayList4;
                                                                                                    arrayList2 = arrayList3;
                                                                                                    aVar = aVar2;
                                                                                                    str2 = str5;
                                                                                                    Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                                                                    list2.add(aVar + ": " + th.getMessage());
                                                                                                }
                                                                                            }
                                                                                        } catch (Throwable th7) {
                                                                                            th = th7;
                                                                                        }
                                                                                    } catch (Throwable th8) {
                                                                                        th = th8;
                                                                                        arrayList4 = arrayList;
                                                                                        z2 = z3;
                                                                                        arrayList = arrayList4;
                                                                                        arrayList2 = arrayList3;
                                                                                        aVar = aVar2;
                                                                                        str2 = str5;
                                                                                        Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                                                        list2.add(aVar + ": " + th.getMessage());
                                                                                    }
                                                                                } catch (Throwable th9) {
                                                                                    th = th9;
                                                                                    arrayList4 = arrayList;
                                                                                    z2 = z3;
                                                                                    str3 = str;
                                                                                    str = str3;
                                                                                    arrayList = arrayList4;
                                                                                    arrayList2 = arrayList3;
                                                                                    aVar = aVar2;
                                                                                    str2 = str5;
                                                                                    Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                                                    list2.add(aVar + ": " + th.getMessage());
                                                                                    function2 = function2;
                                                                                    X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                                                                    arrayList5 = arrayList2;
                                                                                    str6 = str;
                                                                                    arrayList6 = arrayList;
                                                                                    hVar2 = hVar;
                                                                                    list3 = list2;
                                                                                    str5 = str2;
                                                                                    z = z;
                                                                                }
                                                                            } catch (Throwable th10) {
                                                                                th = th10;
                                                                            }
                                                                        }
                                                                    } else {
                                                                        z2 = z3;
                                                                        arrayList4 = arrayList;
                                                                        str3 = str;
                                                                        kVar.add(aVar2 + " (aar)");
                                                                        arrayList4.add(iVarB);
                                                                    }
                                                                    this = this;
                                                                    list2 = list2;
                                                                    str = str3;
                                                                    arrayList = arrayList4;
                                                                    arrayList2 = arrayList3;
                                                                    aVar = aVar2;
                                                                    str2 = str5;
                                                                    function2 = function2;
                                                                    X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                                                    arrayList5 = arrayList2;
                                                                    str6 = str;
                                                                    arrayList6 = arrayList;
                                                                    hVar2 = hVar;
                                                                    list3 = list2;
                                                                    str5 = str2;
                                                                }
                                                            } catch (Throwable th11) {
                                                                th = th11;
                                                                str = str;
                                                                z2 = z3;
                                                                list2 = list3;
                                                                arrayList2 = arrayList3;
                                                                aVar = aVar2;
                                                                str2 = str5;
                                                                Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                                list2.add(aVar + ": " + th.getMessage());
                                                                function2 = function2;
                                                                X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                                                arrayList5 = arrayList2;
                                                                str6 = str;
                                                                arrayList6 = arrayList;
                                                                hVar2 = hVar;
                                                                list3 = list2;
                                                                str5 = str2;
                                                                z = z;
                                                            }
                                                            this = this;
                                                            list2 = list2;
                                                            str = str3;
                                                            arrayList2 = arrayList3;
                                                            aVar = aVar2;
                                                            str2 = str5;
                                                            Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                            list2.add(aVar + ": " + th.getMessage());
                                                            function2 = function2;
                                                            X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                                            arrayList5 = arrayList2;
                                                            str6 = str;
                                                            arrayList6 = arrayList;
                                                            hVar2 = hVar;
                                                            list3 = list2;
                                                            str5 = str2;
                                                        }
                                                    } catch (Throwable th12) {
                                                        th = th12;
                                                        z2 = z3;
                                                        str3 = str;
                                                    }
                                                } catch (Throwable th13) {
                                                    th = th13;
                                                    z2 = z3;
                                                    this = this;
                                                    list2 = list2;
                                                }
                                            }
                                            arrayList5 = arrayList3;
                                            arrayList6 = arrayList;
                                            hVar2 = hVar;
                                        } catch (Throwable th14) {
                                            th = th14;
                                        }
                                    } catch (Throwable th15) {
                                        th = th15;
                                        aVar2 = aVar3;
                                    }
                                } catch (Throwable th16) {
                                    th = th16;
                                    str3 = str7;
                                    arrayList3 = arrayList7;
                                    arrayList4 = arrayList8;
                                    hVar = hVar3;
                                    aVar2 = aVar3;
                                    str = str3;
                                    arrayList = arrayList4;
                                    arrayList2 = arrayList3;
                                    aVar = aVar2;
                                    str2 = str5;
                                    Log.e("DependencyResolver", "download failed: " + aVar, th);
                                    list2.add(aVar + ": " + th.getMessage());
                                    function2 = function2;
                                    X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                    arrayList5 = arrayList2;
                                    str6 = str;
                                    arrayList6 = arrayList;
                                    hVar2 = hVar;
                                    list3 = list2;
                                    str5 = str2;
                                    z = z;
                                }
                            } catch (Throwable th17) {
                                th = th17;
                                hVar = hVar2;
                                str3 = str7;
                                arrayList3 = arrayList7;
                                arrayList4 = arrayList8;
                            }
                        } catch (Throwable th18) {
                            th = th18;
                            arrayList4 = arrayList6;
                            hVar = hVar2;
                            str3 = str7;
                            arrayList3 = arrayList7;
                        }
                    } catch (Throwable th19) {
                        th = th19;
                        str3 = str6;
                        arrayList3 = arrayList5;
                        arrayList4 = arrayList6;
                        hVar = hVar2;
                    }
                } else {
                    String str8 = str5;
                    ArrayList arrayList9 = arrayList5;
                    ArrayList arrayList10 = arrayList6;
                    hVar = hVar2;
                    z = z ? 1 : 0;
                    String str9 = str6;
                    final Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
                    File fileA2 = hVar.a();
                    Function2 function2Y2 = Y(function2, intRef, iCoerceAtLeast, aVar3);
                    Function0 function3 = new Function0() { // from class: zk3
                        public final Object invoke() {
                            return bl3.j(booleanRef3);
                        }
                    };
                    str = str9;
                    arrayList = arrayList10;
                    this = this;
                    try {
                        File fileZ2 = z(this, aVar3, "jar", fileA2, kVar, function2Y2, function3, false, 64, null);
                        aVar = aVar3;
                        try {
                            boolean z4 = booleanRef3.element;
                            if (fileZ2 != null) {
                                if (z4) {
                                    try {
                                        str4 = " · 查找";
                                        X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, true, 96, null);
                                    } catch (Throwable th20) {
                                        th = th20;
                                        z2 = z4;
                                        str2 = str8;
                                        arrayList2 = arrayList9;
                                        list2 = list2;
                                        Log.e("DependencyResolver", "download failed: " + aVar, th);
                                        list2.add(aVar + ": " + th.getMessage());
                                        function2 = function2;
                                        X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                        arrayList5 = arrayList2;
                                        str6 = str;
                                        arrayList6 = arrayList;
                                        hVar2 = hVar;
                                        list3 = list2;
                                        str5 = str2;
                                        z = z;
                                    }
                                } else {
                                    str4 = " · 查找";
                                }
                                StringBuilder sb = new StringBuilder();
                                sb.append(aVar);
                                try {
                                    sb.append(str8);
                                    X(function2, intRef, iCoerceAtLeast, sb.toString(), 100, 0L, 0L, booleanRef3.element, 96, null);
                                    if (this.F(fileZ2)) {
                                        arrayList2 = arrayList9;
                                        try {
                                            arrayList2.add(fileZ2);
                                            z2 = z4;
                                            str2 = str8;
                                            list2 = list2;
                                        } catch (Throwable th21) {
                                            th = th21;
                                            z2 = z4;
                                            str2 = str8;
                                            list2 = list2;
                                            Log.e("DependencyResolver", "download failed: " + aVar, th);
                                            list2.add(aVar + ": " + th.getMessage());
                                        }
                                    } else {
                                        arrayList2 = arrayList9;
                                        a aVarG2 = this.G(aVar, "jvm");
                                        if (aVarG2 == null) {
                                            arrayList2.add(fileZ2);
                                            z2 = z4;
                                            str2 = str8;
                                        } else {
                                            X(function2, intRef, iCoerceAtLeast, aVarG2 + str4, 0, 0L, 0L, false, 96, null);
                                            final Ref.BooleanRef booleanRef4 = new Ref.BooleanRef();
                                            try {
                                                try {
                                                    z2 = z4;
                                                    str2 = str8;
                                                    try {
                                                        File fileY2 = this.y(aVarG2, "jar", hVar.a(), kVar, Y(function2, intRef, iCoerceAtLeast, aVarG2), new Function0() { // from class: al3
                                                            public final Object invoke() {
                                                                return bl3.h(booleanRef4);
                                                            }
                                                        }, true);
                                                        if (fileY2 != null) {
                                                            X(function2, intRef, iCoerceAtLeast, aVarG2 + str2, 100, 0L, 0L, booleanRef4.element, 96, null);
                                                            if (this.F(fileY2)) {
                                                                if (booleanRef4.element) {
                                                                    X(function2, intRef, iCoerceAtLeast, aVarG2.toString(), 100, 0L, 0L, true, 96, null);
                                                                }
                                                                kVar.add(aVar + ": empty KMP shell, using " + aVarG2.b() + " for JVM classes");
                                                                arrayList2.add(fileY2);
                                                            } else {
                                                                arrayList2.add(fileZ2);
                                                            }
                                                        } else {
                                                            arrayList2.add(fileZ2);
                                                        }
                                                    } catch (Throwable th22) {
                                                        th = th22;
                                                        list2 = list2;
                                                        Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                        list2.add(aVar + ": " + th.getMessage());
                                                    }
                                                } catch (Throwable th23) {
                                                    th = th23;
                                                    z2 = z4;
                                                    str2 = str8;
                                                    list2 = list2;
                                                    Log.e("DependencyResolver", "download failed: " + aVar, th);
                                                    list2.add(aVar + ": " + th.getMessage());
                                                    function2 = function2;
                                                    X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                                    arrayList5 = arrayList2;
                                                    str6 = str;
                                                    arrayList6 = arrayList;
                                                    hVar2 = hVar;
                                                    list3 = list2;
                                                    str5 = str2;
                                                    z = z;
                                                }
                                            } catch (Throwable th24) {
                                                th = th24;
                                                z2 = z4;
                                            }
                                        }
                                        list2 = list2;
                                    }
                                } catch (Throwable th25) {
                                    th = th25;
                                    z2 = z4;
                                    str2 = str8;
                                    arrayList2 = arrayList9;
                                    list2 = list2;
                                    Log.e("DependencyResolver", "download failed: " + aVar, th);
                                    list2.add(aVar + ": " + th.getMessage());
                                    function2 = function2;
                                    X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                                    arrayList5 = arrayList2;
                                    str6 = str;
                                    arrayList6 = arrayList;
                                    hVar2 = hVar;
                                    list3 = list2;
                                    str5 = str2;
                                    z = z;
                                }
                            } else {
                                z2 = z4;
                                str2 = str8;
                                arrayList2 = arrayList9;
                                list2 = list2;
                                try {
                                    list2.add(aVar + ": no .jar found");
                                } catch (Throwable th26) {
                                    th = th26;
                                    Log.e("DependencyResolver", "download failed: " + aVar, th);
                                    list2.add(aVar + ": " + th.getMessage());
                                }
                            }
                        } catch (Throwable th27) {
                            th = th27;
                            list2 = list2;
                            str2 = str8;
                            arrayList2 = arrayList9;
                        }
                    } catch (Throwable th28) {
                        th = th28;
                        aVar = aVar3;
                    }
                    function2 = function2;
                    X(function2, intRef, iCoerceAtLeast, aVar.toString(), 100, 0L, 0L, z2, 96, null);
                    arrayList5 = arrayList2;
                    str6 = str;
                    arrayList6 = arrayList;
                    hVar2 = hVar;
                    list3 = list2;
                    str5 = str2;
                }
            } catch (Throwable th29) {
                th = th29;
                str = str6;
                arrayList = arrayList6;
                hVar = hVar2;
                aVar = aVar3;
                z = z ? 1 : 0;
                str2 = str5;
                list2 = list3;
                arrayList2 = arrayList5;
            }
            z = z;
        }
        List list4 = list3;
        ArrayList arrayList11 = arrayList5;
        HashSet hashSet = new HashSet();
        ArrayList arrayList12 = new ArrayList();
        for (Object obj2 : arrayList6) {
            if (hashSet.add(((i) obj2).c())) {
                arrayList12.add(obj2);
            }
        }
        HashSet hashSet2 = new HashSet();
        ArrayList arrayList13 = new ArrayList();
        for (Object obj3 : arrayList11) {
            if (hashSet2.add(((File) obj3).getAbsolutePath())) {
                arrayList13.add(obj3);
            }
        }
        return new j(arrayList13, arrayList12, kVar, list4);
    }

    public final LinkedHashMap Z(List list, h hVar) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        HashSet hashSet = new HashSet();
        ArrayDeque arrayDeque = new ArrayDeque();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayDeque.addLast(new e((a) it.next(), SetsKt.emptySet()));
        }
        while (!arrayDeque.isEmpty()) {
            e eVar = (e) arrayDeque.removeFirst();
            a aVarA = eVar.a();
            a aVar = (a) linkedHashMap.get(aVarA.d());
            if (aVar == null || r(aVarA.f(), aVar.f()) > 0) {
                linkedHashMap.put(aVarA.d(), aVarA);
            }
            if (hashSet.add(aVarA.toString())) {
                String str = aVarA.e() + ":" + aVarA.b() + ":" + aVarA.f();
                hVar.b().add("· ".concat(str));
                Function1 function1C = hVar.c();
                if (function1C != null) {
                    function1C.invoke(new f("resolve", str, 0, hashSet.size(), 0, 0L, 0L, false, WinError.ERROR_FORMS_AUTH_REQUIRED, null));
                }
                d dVarH = H(aVarA, hVar, new HashSet());
                if (dVarH != null) {
                    for (g gVar : dVarH.a()) {
                        if (c.contains(gVar.f()) && !gVar.e() && !E(gVar.c(), eVar.b())) {
                            String strH = gVar.h();
                            if (strH == null) {
                                strH = (String) dVarH.b().get(gVar.c());
                            }
                            if (strH == null || StringsKt.isBlank(strH)) {
                                hVar.b().add("skip " + gVar.c() + " (no version; managed entry missing)");
                            } else {
                                arrayDeque.addLast(new e(new a(gVar.d(), gVar.a(), strH), SetsKt.plus(eVar.b(), gVar.b())));
                            }
                        }
                    }
                }
            }
        }
        k(linkedHashMap, hVar);
        return linkedHashMap;
    }

    public final String a0(String str, Map map) {
        if (!StringsKt.contains$default(str, "${", false, 2, (Object) null)) {
            return str;
        }
        String string = str;
        for (int i2 = 0; i2 < 5 && StringsKt.contains$default(string, "${", false, 2, (Object) null); i2++) {
            StringBuilder sb = new StringBuilder();
            int i3 = 0;
            while (i3 < string.length()) {
                int iIndexOf$default = StringsKt.indexOf$default(string, "${", i3, false, 4, (Object) null);
                int i4 = i3;
                if (iIndexOf$default < 0) {
                    sb.append((CharSequence) string, i4, string.length());
                    break;
                }
                int i5 = iIndexOf$default + 2;
                int iIndexOf$default2 = StringsKt.indexOf$default(string, '}', i5, false, 4, (Object) null);
                if (iIndexOf$default2 < 0) {
                    sb.append((CharSequence) string, i4, string.length());
                    break;
                }
                sb.append((CharSequence) string, i4, iIndexOf$default);
                String str2 = (String) map.get(string.substring(i5, iIndexOf$default2));
                if (str2 != null) {
                    sb.append(str2);
                } else {
                    sb.append((CharSequence) string, iIndexOf$default, iIndexOf$default2 + 1);
                }
                i3 = iIndexOf$default2 + 1;
            }
            string = sb.toString();
        }
        return string;
    }

    public final void k(LinkedHashMap linkedHashMap, h hVar) {
        a aVar;
        String strF;
        a aVar2;
        HashMap map = new HashMap();
        for (Object obj : linkedHashMap.values()) {
            obj.getClass();
            a aVar3 = (a) obj;
            String strP = p(aVar3.e(), aVar3.b());
            if (strP != null && ((aVar2 = (a) map.get(strP)) == null || r(aVar3.f(), aVar2.f()) > 0)) {
                map.put(strP, aVar3);
            }
        }
        for (Pair pair : MapsKt.toList(linkedHashMap)) {
            String str = (String) pair.component1();
            a aVar4 = (a) pair.component2();
            String strP2 = p(aVar4.e(), aVar4.b());
            if (strP2 != null && (aVar = (a) map.get(strP2)) != null && (strF = aVar.f()) != null && r(aVar4.f(), strF) < 0) {
                a aVar5 = new a(aVar4.e(), aVar4.b(), strF);
                if (H(aVar5, hVar, new HashSet()) == null) {
                    hVar.b().add("align skip " + aVar4.b() + " " + aVar4.f() + " -> " + strF + " (not found)");
                } else {
                    linkedHashMap.put(str, aVar5);
                    hVar.b().add("aligned " + aVar4.b() + " " + aVar4.f() + " -> " + strF + " (" + strP2 + ")");
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0049  */
    public final d l(a aVar, h hVar, Set set) {
        Element elementN;
        a aVar2;
        d dVarH;
        Map mapB;
        Map mapB2;
        File fileZ = z(this, aVar, "pom", hVar.a(), hVar.b(), null, null, false, 112, null);
        if (fileZ == null || (elementN = N(fileZ)) == null) {
            return null;
        }
        Element elementM = m(elementN, "parent");
        if (elementM != null) {
            bl3 bl3Var = a;
            String strO = bl3Var.o(elementM, "groupId");
            String strO2 = bl3Var.o(elementM, "artifactId");
            String strO3 = bl3Var.o(elementM, "version");
            if (strO == null || strO2 == null || strO3 == null) {
                aVar2 = null;
            } else {
                aVar2 = new a(strO, strO2, strO3);
            }
        } else {
            aVar2 = null;
        }
        d dVarH2 = aVar2 != null ? a.H(aVar2, hVar, set) : null;
        HashMap map = new HashMap();
        q(elementN, aVar2, hVar, set, map);
        String strO4 = o(elementN, "groupId");
        if (strO4 == null) {
            strO4 = aVar2 != null ? aVar2.e() : null;
            if (strO4 == null) {
                strO4 = aVar.e();
            }
        }
        String strO5 = o(elementN, "version");
        if (strO5 == null) {
            strO5 = aVar2 != null ? aVar2.f() : null;
            if (strO5 == null) {
                strO5 = aVar.f();
            }
        }
        String strO6 = o(elementN, "artifactId");
        if (strO6 == null) {
            strO6 = aVar.b();
        }
        map.putIfAbsent("project.groupId", strO4);
        map.putIfAbsent("project.artifactId", strO6);
        map.putIfAbsent("project.version", strO5);
        map.putIfAbsent("pom.groupId", strO4);
        map.putIfAbsent("pom.version", strO5);
        map.putIfAbsent("groupId", strO4);
        map.putIfAbsent("version", strO5);
        if (aVar2 != null) {
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (dVarH2 != null && (mapB2 = dVarH2.b()) != null) {
            linkedHashMap.putAll(mapB2);
        }
        Element elementM2 = m(elementN, "dependencyManagement");
        Element elementM3 = elementM2 != null ? a.m(elementM2, "dependencies") : null;
        if (elementM3 != null) {
            Iterator it = n(elementM3, "dependency").iterator();
            while (it.hasNext()) {
                g gVarM = M((Element) it.next(), map);
                if (Intrinsics.areEqual(gVarM.f(), "import") && StringsKt.equals(gVarM.g(), "pom", true)) {
                    String strH = gVarM.h();
                    if (strH != null && !StringsKt.isBlank(strH) && (dVarH = H(new a(gVarM.d(), gVarM.a(), strH), hVar, set)) != null && (mapB = dVarH.b()) != null) {
                        for (Map.Entry entry : mapB.entrySet()) {
                            linkedHashMap.putIfAbsent((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                } else {
                    String strH2 = gVarM.h();
                    if (strH2 != null && !StringsKt.isBlank(strH2)) {
                        linkedHashMap.put(gVarM.c(), gVarM.h());
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Element elementM4 = m(elementN, "dependencies");
        if (elementM4 != null) {
            Iterator it2 = a.n(elementM4, "dependency").iterator();
            while (it2.hasNext()) {
                arrayList.add(a.M((Element) it2.next(), map));
            }
        }
        String strO7 = o(elementN, "packaging");
        if (strO7 == null) {
            strO7 = "jar";
        }
        String string = StringsKt.trim(strO7).toString();
        return new d(string.length() != 0 ? string : "jar", linkedHashMap, arrayList);
    }

    public final Element m(Element element, String str) {
        for (Node firstChild = element.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild instanceof Element) {
                Element element2 = (Element) firstChild;
                if (Intrinsics.areEqual(element2.getTagName(), str)) {
                    return element2;
                }
            }
        }
        return null;
    }

    public final List n(Element element, String str) {
        ArrayList arrayList = new ArrayList();
        for (Node firstChild = element.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if ((firstChild instanceof Element) && Intrinsics.areEqual(((Element) firstChild).getTagName(), str)) {
                arrayList.add(firstChild);
            }
        }
        return arrayList;
    }

    public final String o(Element element, String str) {
        String textContent;
        String string;
        Element elementM = m(element, str);
        if (elementM == null || (textContent = elementM.getTextContent()) == null || (string = StringsKt.trim(textContent).toString()) == null || string.length() <= 0) {
            return null;
        }
        return string;
    }

    public final String p(String str, String str2) {
        if (Intrinsics.areEqual(str, "org.jetbrains.kotlin") && SetsKt.setOf(new String[]{"kotlin-stdlib", "kotlin-stdlib-jdk7", "kotlin-stdlib-jdk8"}).contains(str2)) {
            return "kotlin-stdlib";
        }
        if (Intrinsics.areEqual(str, "androidx.appcompat") && SetsKt.setOf(new String[]{"appcompat", "appcompat-resources"}).contains(str2)) {
            return "androidx.appcompat";
        }
        if (Intrinsics.areEqual(str, "androidx.lifecycle")) {
            return "androidx.lifecycle";
        }
        if (Intrinsics.areEqual(str, "androidx.core") && SetsKt.setOf(new String[]{"core", "core-ktx"}).contains(str2)) {
            return "androidx.core";
        }
        return null;
    }

    public final void q(Element element, a aVar, h hVar, Set set, HashMap map) {
        String string;
        if (aVar != null) {
            try {
                File fileZ = z(this, aVar, "pom", hVar.a(), hVar.b(), null, null, false, 112, null);
                a aVar2 = null;
                Element elementN = fileZ != null ? a.N(fileZ) : null;
                if (elementN != null) {
                    Element elementM = m(elementN, "parent");
                    if (elementM != null) {
                        bl3 bl3Var = a;
                        String strO = bl3Var.o(elementM, "groupId");
                        String strO2 = bl3Var.o(elementM, "artifactId");
                        String strO3 = bl3Var.o(elementM, "version");
                        if (strO != null && strO2 != null && strO3 != null) {
                            aVar2 = new a(strO, strO2, strO3);
                        }
                    }
                    q(elementN, aVar2, hVar, set, map);
                }
            } catch (Throwable th) {
                Log.w("DependencyResolver", "parent props read failed: " + aVar, th);
            }
        }
        Element elementM2 = m(element, "properties");
        if (elementM2 == null) {
            return;
        }
        for (Node firstChild = elementM2.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild instanceof Element) {
                Element element2 = (Element) firstChild;
                String tagName = element2.getTagName();
                String textContent = element2.getTextContent();
                if (textContent == null || (string = StringsKt.trim(textContent).toString()) == null) {
                    string = "";
                }
                map.put(tagName, string);
            }
        }
    }

    public final int r(String str, String str2) {
        Pair pairS = s(str);
        List list = (List) pairS.component1();
        String str3 = (String) pairS.component2();
        Pair pairS2 = s(str2);
        List list2 = (List) pairS2.component1();
        String str4 = (String) pairS2.component2();
        int iMax = Math.max(list.size(), list2.size());
        int i2 = 0;
        while (i2 < iMax) {
            int iIntValue = ((Number) ((i2 < 0 || i2 >= list.size()) ? 0 : list.get(i2))).intValue();
            int iIntValue2 = ((Number) ((i2 < 0 || i2 >= list2.size()) ? 0 : list2.get(i2))).intValue();
            if (iIntValue != iIntValue2) {
                return Intrinsics.compare(iIntValue, iIntValue2);
            }
            i2++;
        }
        if (str3.length() == 0 && str4.length() == 0) {
            return 0;
        }
        if (str3.length() == 0) {
            return 1;
        }
        if (str4.length() == 0) {
            return -1;
        }
        return str3.compareTo(str4);
    }

    public final void t(InputStream inputStream, OutputStream outputStream, long j2, Function2 function2) throws IOException {
        if (function2 == null) {
            ByteStreamsKt.copyTo$default(inputStream, outputStream, 0, 2, (Object) null);
            return;
        }
        byte[] bArr = new byte[8192];
        long j3 = 0;
        function2.invoke(0L, Long.valueOf(j2));
        while (true) {
            long j4 = j3;
            do {
                int i2 = inputStream.read(bArr);
                if (i2 < 0) {
                    function2.invoke(Long.valueOf(j3), Long.valueOf(j2));
                    return;
                } else {
                    outputStream.write(bArr, 0, i2);
                    j3 += (long) i2;
                }
            } while (j3 - j4 < 8192);
            function2.invoke(Long.valueOf(j3), Long.valueOf(j2));
        }
    }

    public final List u(List list) {
        File parentFile;
        list.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            File parentFile2 = file.getParentFile();
            String name = null;
            File parentFile3 = parentFile2 != null ? parentFile2.getParentFile() : null;
            String name2 = parentFile2 != null ? parentFile2.getName() : null;
            if (parentFile3 != null && name2 != null) {
                if (StringsKt.equals(file.getName(), parentFile3.getName() + "-" + name2 + ".jar", true)) {
                    String absolutePath = parentFile3.getAbsolutePath();
                    File file2 = (File) linkedHashMap.get(absolutePath);
                    if (file2 != null && (parentFile = file2.getParentFile()) != null) {
                        name = parentFile.getName();
                    }
                    if (name == null || r(name2, name) > 0) {
                        linkedHashMap.put(absolutePath, file);
                    }
                }
            }
            arrayList.add(file);
        }
        Collection collectionValues = linkedHashMap.values();
        collectionValues.getClass();
        return CollectionsKt.plus(arrayList, collectionValues);
    }

    public final List v(List list) {
        list.getClass();
        if (list.size() < 2) {
            return list;
        }
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        while (true) {
            int i2 = 0;
            if (!it.hasNext()) {
                HashSet hashSet = new HashSet();
                HashSet hashSet2 = new HashSet();
                List list2 = list;
                for (File file : CollectionsKt.sortedWith(list2, new n(map))) {
                    Integer num = (Integer) map.get(file);
                    Set setW = (num != null ? num.intValue() : 0) > 0 ? w(file) : SetsKt.emptySet();
                    if (setW.isEmpty() || !hashSet2.containsAll(setW)) {
                        hashSet.add(file);
                        hashSet2.addAll(setW);
                    }
                }
                ArrayList arrayList = new ArrayList();
                for (Object obj : list2) {
                    if (hashSet.contains((File) obj)) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
            }
            File file2 = (File) it.next();
            try {
                ZipFile zipFile = new ZipFile(file2);
                try {
                    Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                    enumerationEntries.getClass();
                    int i3 = 0;
                    for (ZipEntry zipEntry : SequencesKt.asSequence(CollectionsKt.iterator(enumerationEntries))) {
                        if (!zipEntry.isDirectory()) {
                            String name = zipEntry.getName();
                            name.getClass();
                            if (StringsKt.endsWith$default(name, JavaClass.EXTENSION, false, 2, (Object) null) && (i3 = i3 + 1) < 0) {
                                CollectionsKt.throwCountOverflow();
                            }
                        }
                    }
                    CloseableKt.closeFinally(zipFile, (Throwable) null);
                    i2 = i3;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(zipFile, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                Log.w("DependencyResolver", "could not read classes from " + file2.getName(), th3);
            }
            map.put(file2, Integer.valueOf(i2));
        }
    }

    public final c x(String str, File file, String str2, Function2 function2, int i2, int i3, boolean z) {
        long[] jArr = z ? new long[]{0, 1000, 2000} : new long[]{0};
        c cVar = c.FAILED;
        int length = jArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            long j2 = jArr[i4];
            if (j2 > 0) {
                try {
                    Thread.sleep(j2);
                } catch (InterruptedException unused) {
                    return c.FAILED;
                }
            }
            int i5 = m.b[A(str, file, str2, function2, i2, i3).ordinal()];
            if (i5 == 1) {
                return c.OK;
            }
            if (i5 == 2) {
                return c.NOT_FOUND;
            }
            if (i5 != 3) {
                bu8.a();
                return null;
            }
            cVar = c.FAILED;
            if (i4 < ArraysKt.getLastIndex(jArr)) {
                Log.w("DependencyResolver", "download retry " + (i4 + 1) + ": " + str);
            }
        }
        return cVar;
    }

    public final File y(a aVar, String str, File file, List list, Function2 function2, Function0 function0, boolean z) {
        function2 = function2;
        File file2 = new File(file, aVar.c() + PsuedoNames.PSEUDONAME_ROOT + aVar.a(str));
        File file3 = new File(file2.getParentFile(), file2.getName() + ".missing");
        long j2 = 0;
        if (file2.isFile() && file2.length() > 0) {
            if (I(file2, str)) {
                if (Intrinsics.areEqual(str, "jar")) {
                    list.add(aVar + " (cached)");
                }
                if (function0 != null) {
                    function0.invoke();
                }
                file3.delete();
                return file2;
            }
            list.add(aVar + ": cached " + str + " corrupt, re-downloading");
            file2.delete();
        }
        Charset charset = null;
        if (file3.isFile()) {
            long jCurrentTimeMillis = System.currentTimeMillis() - file3.lastModified();
            if (0 <= jCurrentTimeMillis && jCurrentTimeMillis < 604800001) {
                if (z) {
                    list.add(aVar + ": skip (known missing)");
                }
                return null;
            }
            file3.delete();
        }
        File parentFile = file2.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        StringBuilder sb = z ? new StringBuilder("Probing ") : new StringBuilder("Downloading ");
        sb.append(aVar);
        sb.append(" …");
        list.add(sb.toString());
        List listTake = b;
        if (z) {
            listTake = CollectionsKt.take(listTake, 3);
        }
        List list2 = listTake;
        int i2 = z ? WinError.ERROR_WINS_INTERNAL : WinError.ERROR_EVT_INVALID_CHANNEL_PATH;
        int i3 = z ? 8000 : 30000;
        boolean z2 = !z;
        Iterator it = list2.iterator();
        boolean z3 = false;
        while (true) {
            j2 = j2;
            if (!it.hasNext()) {
                Charset charset2 = charset;
                if (!z3) {
                    return null;
                }
                try {
                    Result.Companion companion = Result.Companion;
                    FilesKt.writeText$default(file3, String.valueOf(System.currentTimeMillis()), charset2, 2, charset2);
                    Result.constructor-impl(Unit.INSTANCE);
                    return null;
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    Result.constructor-impl(ResultKt.createFailure(th));
                    return null;
                }
            }
            Object next = it.next();
            next.getClass();
            String str2 = (String) next;
            if (!z && function2 != null) {
                function2.invoke(-1L, Long.valueOf(j2));
            }
            int i4 = m.a[x(str2 + PsuedoNames.PSEUDONAME_ROOT + aVar.c() + PsuedoNames.PSEUDONAME_ROOT + aVar.a(str), file2, str, function2, i2, i3, z2).ordinal()];
            if (i4 == 1) {
                if (Intrinsics.areEqual(str, "jar")) {
                    list.add(aVar + " <- " + D(str2));
                }
                file3.delete();
                return file2;
            }
            if (i4 == 2) {
                z3 = true;
            } else if (i4 != 3) {
                bu8.a();
                return charset;
            }
        }
    }

    public static final class k extends ArrayList {
        public final Function1 b;

        public k(Function1 function1) {
            function1.getClass();
            this.b = function1;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(String str) {
            str.getClass();
            this.b.invoke(str);
            return super.add(str);
        }

        public /* bridge */ int b() {
            return super.size();
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof String) {
                return contains((String) obj);
            }
            return false;
        }

        public /* bridge */ int d(String str) {
            return super.indexOf(str);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof String) {
                return d((String) obj);
            }
            return -1;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof String) {
                return n((String) obj);
            }
            return -1;
        }

        public /* bridge */ int n(String str) {
            return super.lastIndexOf(str);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof String) {
                return z((String) obj);
            }
            return false;
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ int size() {
            return b();
        }

        public /* bridge */ boolean z(String str) {
            return super.remove(str);
        }

        public /* bridge */ boolean contains(String str) {
            return super.contains((Object) str);
        }
    }

    public static final class f {
        public final String a;
        public final String b;
        public final int c;
        public final int d;
        public final int e;
        public final long f;
        public final long g;
        public final boolean h;

        public f(String str, String str2, int i, int i2, int i3, long j, long j2, boolean z) {
            str.getClass();
            str2.getClass();
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = j;
            this.g = j2;
            this.h = z;
        }

        public final long a() {
            return this.f;
        }

        public final long b() {
            return this.g;
        }

        public final String c() {
            return this.b;
        }

        public final int d() {
            return this.d;
        }

        public final boolean e() {
            return this.h;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof f)) {
                return false;
            }
            f fVar = (f) obj;
            return Intrinsics.areEqual(this.a, fVar.a) && Intrinsics.areEqual(this.b, fVar.b) && this.c == fVar.c && this.d == fVar.d && this.e == fVar.e && this.f == fVar.f && this.g == fVar.g && this.h == fVar.h;
        }

        public final int f() {
            return this.c;
        }

        public final String g() {
            return this.a;
        }

        public final int h() {
            return this.e;
        }

        public int hashCode() {
            return (((((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Long.hashCode(this.f)) * 31) + Long.hashCode(this.g)) * 31) + Boolean.hashCode(this.h);
        }

        public String toString() {
            return "ProgressUpdate(phase=" + this.a + ", detail=" + this.b + ", percent=" + this.c + ", done=" + this.d + ", total=" + this.e + ", bytesRead=" + this.f + ", contentLength=" + this.g + ", fromCache=" + this.h + ")";
        }

        public /* synthetic */ f(String str, String str2, int i, int i2, int i3, long j, long j2, boolean z, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, i, i2, i3, (i4 & 32) != 0 ? -1L : j, (i4 & 64) != 0 ? -1L : j2, (i4 & 128) != 0 ? false : z);
        }
    }

    public static final class h {
        public final File a;
        public final List b;
        public final List c;
        public final Function1 d;
        public final HashMap e;

        public h(File file, List list, List list2, Function1 function1) {
            file.getClass();
            list.getClass();
            list2.getClass();
            this.a = file;
            this.b = list;
            this.c = list2;
            this.d = function1;
            this.e = new HashMap();
        }

        public final File a() {
            return this.a;
        }

        public final List b() {
            return this.b;
        }

        public final Function1 c() {
            return this.d;
        }

        public final HashMap d() {
            return this.e;
        }

        public /* synthetic */ h(File file, List list, List list2, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(file, list, list2, (i & 8) != 0 ? null : function1);
        }
    }
}
