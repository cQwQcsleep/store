package defpackage;

import android.util.Xml;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class s6b {
    public static final s6b a = new s6b();
    public static final Regex b = new Regex("(?m)^\\s*package\\s+([\\w.]+)");
    public static final Regex c = new Regex("\\bclass\\s+(\\w+)[^{;]*\\bextends\\s+([\\w.]+)");
    public static final Regex d = new Regex("\\bclass\\s+(\\w+)\\b[^{]*?:\\s*([\\w.]+)\\s*\\(");
    public static final int e = 8;

    public static final class a {
        public final String a;
        public final String b;
        public final int c;
        public final int d;

        public a(String str, String str2, int i, int i2) {
            str.getClass();
            str2.getClass();
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = i2;
        }

        public final String a() {
            return this.b;
        }

        public final int b() {
            return this.d;
        }

        public final int c() {
            return this.c;
        }

        public final String d() {
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
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && this.c == aVar.c && this.d == aVar.d;
        }

        public int hashCode() {
            return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d);
        }

        public String toString() {
            return "Component(tag=" + this.a + ", className=" + this.b + ", line=" + this.c + ", column=" + this.d + ")";
        }
    }

    public static final class b {
        public final e a;
        public final String b;
        public final String c;
        public final int d;
        public final int e;

        public static final /* synthetic */ class a {
            public static final /* synthetic */ int[] a;

            static {
                int[] iArr = new int[e.values().length];
                try {
                    iArr[e.ERROR.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[e.WARNING.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                a = iArr;
            }
        }

        public b(e eVar, String str, String str2, int i, int i2) {
            eVar.getClass();
            str.getClass();
            this.a = eVar;
            this.b = str;
            this.c = str2;
            this.d = i;
            this.e = i2;
        }

        public final String a() {
            int i = a.a[this.a.ordinal()];
            if (i == 1) {
                return t92.a.b(this.b, this.c, this.d, this.e);
            }
            if (i == 2) {
                return t92.a.k(this.b, this.c, this.d, this.e);
            }
            bu8.a();
            return null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.a == bVar.a && Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.c, bVar.c) && this.d == bVar.d && this.e == bVar.e;
        }

        public int hashCode() {
            int iHashCode = ((this.a.hashCode() * 31) + this.b.hashCode()) * 31;
            String str = this.c;
            return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e);
        }

        public String toString() {
            return "Issue(severity=" + this.a + ", message=" + this.b + ", path=" + this.c + ", line=" + this.d + ", column=" + this.e + ")";
        }
    }

    public static final class c {
        public final d a;
        public final int b;
        public final List c;
        public final Set d;
        public final boolean e;

        public c(d dVar, int i, List list, Set set, boolean z) {
            list.getClass();
            set.getClass();
            this.a = dVar;
            this.b = i;
            this.c = list;
            this.d = set;
            this.e = z;
        }

        public final Set a() {
            return this.d;
        }

        public final d b() {
            return this.a;
        }

        public final int c() {
            return this.b;
        }

        public final List d() {
            return this.c;
        }

        public final boolean e() {
            return this.e;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return Intrinsics.areEqual(this.a, cVar.a) && this.b == cVar.b && Intrinsics.areEqual(this.c, cVar.c) && Intrinsics.areEqual(this.d, cVar.d) && this.e == cVar.e;
        }

        public int hashCode() {
            d dVar = this.a;
            return ((((((((dVar == null ? 0 : dVar.hashCode()) * 31) + Integer.hashCode(this.b)) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + Boolean.hashCode(this.e);
        }

        public String toString() {
            return "ManifestInfo(applicationClass=" + this.a + ", applicationLine=" + this.b + ", components=" + this.c + ", activityClasses=" + this.d + ", hasLauncher=" + this.e + ")";
        }
    }

    public static final class d {
        public final String a;
        public final int b;
        public final int c;

        public d(String str, int i, int i2) {
            str.getClass();
            this.a = str;
            this.b = i;
            this.c = i2;
        }

        public final String a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final int c() {
            return this.c;
        }

        public final String d() {
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
            return Intrinsics.areEqual(this.a, dVar.a) && this.b == dVar.b && this.c == dVar.c;
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c);
        }

        public String toString() {
            return "NamedAt(fqn=" + this.a + ", line=" + this.b + ", column=" + this.c + ")";
        }
    }

    public enum e {
        ERROR,
        WARNING;

        public static final /* synthetic */ EnumEntries e = EnumEntriesKt.enumEntries(b());
    }

    public static final class f {
        public final String a;
        public final File b;
        public final int c;
        public final int d;

        public f(String str, File file, int i, int i2) {
            str.getClass();
            file.getClass();
            this.a = str;
            this.b = file;
            this.c = i;
            this.d = i2;
        }

        public final int a() {
            return this.d;
        }

        public final File b() {
            return this.b;
        }

        public final String c() {
            return this.a;
        }

        public final int d() {
            return this.c;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof f)) {
                return false;
            }
            f fVar = (f) obj;
            return Intrinsics.areEqual(this.a, fVar.a) && Intrinsics.areEqual(this.b, fVar.b) && this.c == fVar.c && this.d == fVar.d;
        }

        public int hashCode() {
            return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d);
        }

        public String toString() {
            return "SourceActivity(fqn=" + this.a + ", file=" + this.b + ", line=" + this.c + ", column=" + this.d + ")";
        }
    }

    public static boolean a(File file) {
        file.getClass();
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "class");
    }

    public static boolean b(File file) {
        file.getClass();
        if (file.isFile()) {
            return Intrinsics.areEqual(FilesKt.getExtension(file), "java") || Intrinsics.areEqual(FilesKt.getExtension(file), "kt");
        }
        return false;
    }

    public static String c(File file, File file2) {
        file2.getClass();
        String path = FilesKt.relativeTo(file2, file).getPath();
        path.getClass();
        return StringsKt.replace$default(StringsKt.removeSuffix(StringsKt.replace$default(path, File.separatorChar, '/', false, 4, (Object) null), JavaClass.EXTENSION), '/', '.', false, 4, (Object) null);
    }

    public static final String h(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str);
    }

    public static final Pair i(XmlPullParser xmlPullParser) {
        return TuplesKt.to(Integer.valueOf(RangesKt.coerceAtLeast(xmlPullParser.getLineNumber(), 1)), Integer.valueOf(RangesKt.coerceAtLeast(xmlPullParser.getColumnNumber(), 1)));
    }

    public final List d(File file, File file2, File file3, String str) {
        file.getClass();
        file2.getClass();
        file3.getClass();
        str.getClass();
        ArrayList arrayList = new ArrayList();
        if (file3.isFile()) {
            Set setE = e(file2);
            c cVarG = g(file3, str);
            if (cVarG != null) {
                String absolutePath = file3.getAbsolutePath();
                d dVarB = cVarG.b();
                if (dVarB != null) {
                    String strA = dVarB.a();
                    int iB = dVarB.b();
                    int iC = dVarB.c();
                    if (!setE.contains(strA)) {
                        arrayList.add(new b(e.ERROR, "application android:name 指向类 " + strA + "，但编译产物里找不到该类，运行时会崩溃。请确认类存在、包名/类名拼写正确。", absolutePath, iB, iC));
                    }
                }
                for (a aVar : cVarG.d()) {
                    if (!setE.contains(aVar.a())) {
                        arrayList.add(new b(e.ERROR, "<" + aVar.d() + "> 指向类 " + aVar.a() + "，但编译产物里找不到该类（多为拼写/包名错或文件未创建），运行时会 ClassNotFoundException 崩溃。", absolutePath, aVar.c(), aVar.b()));
                    }
                }
                if (!cVarG.e()) {
                    arrayList.add(new b(e.WARNING, "没有 LAUNCHER 入口 Activity（缺少 MAIN + LAUNCHER 的 intent-filter），应用装上后无法从桌面启动。", absolutePath, RangesKt.coerceAtLeast(cVarG.c(), 1), 1));
                }
                Set setA = cVarG.a();
                for (f fVar : f(new File(file, "src"))) {
                    String strC = fVar.c();
                    d dVarB2 = cVarG.b();
                    if (!Intrinsics.areEqual(strC, dVarB2 != null ? dVarB2.d() : null) && !setA.contains(fVar.c())) {
                        arrayList.add(new b(e.WARNING, "Activity 类 " + fVar.c() + " 没有在 AndroidManifest.xml 注册，启动它会 ActivityNotFoundException 崩溃；若它是抽象基类可忽略。", fVar.b().getAbsolutePath(), fVar.d(), fVar.a()));
                    }
                }
            }
        }
        return arrayList;
    }

    public final Set e(final File file) {
        return !file.isDirectory() ? SetsKt.emptySet() : SequencesKt.toSet(SequencesKt.map(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: p6b
            public final Object invoke(Object obj) {
                return Boolean.valueOf(s6b.a((File) obj));
            }
        }), new Function1() { // from class: q6b
            public final Object invoke(Object obj) {
                return s6b.c(file, (File) obj);
            }
        }));
    }

    public final List f(File file) {
        Object obj;
        String str;
        List groupValues;
        if (!file.isDirectory()) {
            return CollectionsKt.emptyList();
        }
        Set of = SetsKt.setOf(new String[]{"CrashApp.java", "CrashActivity.java"});
        ArrayList arrayList = new ArrayList();
        for (File file2 : SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: r6b
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(s6b.b((File) obj2));
            }
        })) {
            if (!of.contains(file2.getName())) {
                int i = 1;
                try {
                    Result.Companion companion = Result.Companion;
                    obj = Result.constructor-impl(FilesKt.readText$default(file2, (Charset) null, 1, (Object) null));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (Result.isFailure-impl(obj)) {
                    obj = null;
                }
                String str2 = (String) obj;
                if (str2 != null) {
                    MatchResult matchResultFind$default = Regex.find$default(b, str2, 0, 2, (Object) null);
                    if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = (String) groupValues.get(1)) == null) {
                        str = "";
                    }
                    for (MatchResult matchResult : Regex.findAll$default(Intrinsics.areEqual(FilesKt.getExtension(file2), "java") ? c : d, str2, 0, 2, (Object) null)) {
                        Object obj2 = matchResult.getGroupValues().get(i);
                        obj2.getClass();
                        String str3 = (String) obj2;
                        Object obj3 = matchResult.getGroupValues().get(2);
                        obj3.getClass();
                        if (StringsKt.endsWith$default(StringsKt.substringAfterLast$default((String) obj3, '.', (String) null, 2, (Object) null), "Activity", false, 2, (Object) null)) {
                            String str4 = str.length() == 0 ? str3 : str + Constants.ATTRVAL_THIS + str3;
                            Integer numValueOf = Integer.valueOf(StringsKt.indexOf$default(str2, "class " + str3, RangesKt.coerceAtMost(matchResult.getRange().getFirst(), StringsKt.getLastIndex(str2)), false, 4, (Object) null));
                            if (numValueOf.intValue() < 0) {
                                numValueOf = null;
                            }
                            Pair pairG = t92.a.g(str2, numValueOf != null ? numValueOf.intValue() : matchResult.getRange().getFirst());
                            arrayList.add(new f(str4, file2, ((Number) pairG.component1()).intValue(), ((Number) pairG.component2()).intValue()));
                            i = 1;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:61:0x0105 A[Catch: all -> 0x0055, TryCatch #2 {all -> 0x0055, blocks: (B:4:0x0018, B:87:0x0176, B:13:0x0042, B:21:0x0059, B:23:0x0079, B:27:0x0086, B:61:0x0105, B:63:0x010b, B:66:0x0115, B:30:0x0090, B:33:0x0099, B:35:0x009f, B:38:0x00af, B:41:0x00b6, B:43:0x00bf, B:46:0x00c9, B:47:0x00d5, B:51:0x00e0, B:55:0x00f3, B:58:0x00fc, B:67:0x0125, B:71:0x0135, B:75:0x0140, B:78:0x0152, B:81:0x0159, B:83:0x015f, B:86:0x0169, B:88:0x017e), top: B:104:0x0018 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x010b A[Catch: all -> 0x0055, TryCatch #2 {all -> 0x0055, blocks: (B:4:0x0018, B:87:0x0176, B:13:0x0042, B:21:0x0059, B:23:0x0079, B:27:0x0086, B:61:0x0105, B:63:0x010b, B:66:0x0115, B:30:0x0090, B:33:0x0099, B:35:0x009f, B:38:0x00af, B:41:0x00b6, B:43:0x00bf, B:46:0x00c9, B:47:0x00d5, B:51:0x00e0, B:55:0x00f3, B:58:0x00fc, B:67:0x0125, B:71:0x0135, B:75:0x0140, B:78:0x0152, B:81:0x0159, B:83:0x015f, B:86:0x0169, B:88:0x017e), top: B:104:0x0018 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x0112  */
    /* JADX WARN: Code duplicated, block: B:66:0x0115 A[Catch: all -> 0x0055, TryCatch #2 {all -> 0x0055, blocks: (B:4:0x0018, B:87:0x0176, B:13:0x0042, B:21:0x0059, B:23:0x0079, B:27:0x0086, B:61:0x0105, B:63:0x010b, B:66:0x0115, B:30:0x0090, B:33:0x0099, B:35:0x009f, B:38:0x00af, B:41:0x00b6, B:43:0x00bf, B:46:0x00c9, B:47:0x00d5, B:51:0x00e0, B:55:0x00f3, B:58:0x00fc, B:67:0x0125, B:71:0x0135, B:75:0x0140, B:78:0x0152, B:81:0x0159, B:83:0x015f, B:86:0x0169, B:88:0x017e), top: B:104:0x0018 }] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final c g(File file, String str) {
        d dVar;
        int i;
        String strH;
        String strJ;
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                xmlPullParserNewPullParser.setInput(fileInputStream, null);
                ArrayList arrayList = new ArrayList();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                int eventType = xmlPullParserNewPullParser.getEventType();
                int i2 = 1;
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                boolean z4 = false;
                d dVar2 = null;
                for (int i3 = 1; eventType != i3; i3 = 1) {
                    if (eventType == 2) {
                        Pair pairI = i(xmlPullParserNewPullParser);
                        int iIntValue = ((Number) pairI.component1()).intValue();
                        int iIntValue2 = ((Number) pairI.component2()).intValue();
                        dVar = dVar2;
                        String name = xmlPullParserNewPullParser.getName();
                        if (name != null) {
                            i = i2;
                            switch (name.hashCode()) {
                                case -1655966961:
                                    if (name.equals("activity")) {
                                        String strH2 = h(xmlPullParserNewPullParser, "name");
                                        String strJ2 = strH2 != null ? a.j(strH2, str) : null;
                                        if (strJ2 != null) {
                                            arrayList.add(new a("activity", strJ2, iIntValue, iIntValue2));
                                            linkedHashSet.add(strJ2);
                                        }
                                    }
                                    break;
                                case -1422950858:
                                    if (name.equals("action") && z4 && Intrinsics.areEqual(h(xmlPullParserNewPullParser, "name"), "android.intent.action.MAIN")) {
                                        dVar2 = dVar;
                                        i2 = i;
                                        z2 = true;
                                    }
                                    break;
                                case -1029793847:
                                    if (name.equals("intent-filter")) {
                                        dVar2 = dVar;
                                        z2 = false;
                                        z3 = false;
                                        i2 = i;
                                        z4 = true;
                                    }
                                    break;
                                case -987494927:
                                    if (name.equals("provider")) {
                                        strH = h(xmlPullParserNewPullParser, "name");
                                        if (strH != null) {
                                            strJ = a.j(strH, str);
                                        } else {
                                            strJ = null;
                                        }
                                        if (strJ != null) {
                                            String name2 = xmlPullParserNewPullParser.getName();
                                            name2.getClass();
                                            arrayList.add(new a(name2, strJ, iIntValue, iIntValue2));
                                        }
                                    }
                                    break;
                                case -808719889:
                                    if (name.equals("receiver")) {
                                        strH = h(xmlPullParserNewPullParser, "name");
                                        if (strH != null) {
                                            strJ = a.j(strH, str);
                                        } else {
                                            strJ = null;
                                        }
                                        if (strJ != null) {
                                            String name3 = xmlPullParserNewPullParser.getName();
                                            name3.getClass();
                                            arrayList.add(new a(name3, strJ, iIntValue, iIntValue2));
                                        }
                                    }
                                    break;
                                case 50511102:
                                    if (name.equals("category") && z4 && Intrinsics.areEqual(h(xmlPullParserNewPullParser, "name"), "android.intent.category.LAUNCHER")) {
                                        z3 = true;
                                    }
                                    break;
                                case 790287890:
                                    if (name.equals("activity-alias")) {
                                        String strH3 = h(xmlPullParserNewPullParser, "targetActivity");
                                        String strJ3 = strH3 != null ? a.j(strH3, str) : null;
                                        if (strJ3 != null) {
                                            arrayList.add(new a("activity-alias", strJ3, iIntValue, iIntValue2));
                                            linkedHashSet.add(strJ3);
                                        }
                                    }
                                    break;
                                case 1554253136:
                                    if (name.equals("application")) {
                                        String strH4 = h(xmlPullParserNewPullParser, "name");
                                        dVar2 = strH4 != null ? new d(a.j(strH4, str), iIntValue, iIntValue2) : null;
                                        i2 = iIntValue;
                                    }
                                    break;
                                case 1984153269:
                                    if (name.equals("service")) {
                                        strH = h(xmlPullParserNewPullParser, "name");
                                        if (strH != null) {
                                            strJ = a.j(strH, str);
                                        } else {
                                            strJ = null;
                                        }
                                        if (strJ != null) {
                                            String name4 = xmlPullParserNewPullParser.getName();
                                            name4.getClass();
                                            arrayList.add(new a(name4, strJ, iIntValue, iIntValue2));
                                        }
                                    }
                                    break;
                            }
                        } else {
                            i = i2;
                        }
                        dVar2 = dVar;
                        i2 = i;
                    } else if (eventType == 3 && Intrinsics.areEqual(xmlPullParserNewPullParser.getName(), "intent-filter")) {
                        if (z2 && z3) {
                            z = true;
                        }
                        z4 = false;
                    } else {
                        dVar = dVar2;
                        i = i2;
                        dVar2 = dVar;
                        i2 = i;
                    }
                    eventType = xmlPullParserNewPullParser.next();
                }
                c cVar = new c(dVar2, i2, arrayList, linkedHashSet, z);
                try {
                    CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                    return cVar;
                } catch (Throwable unused) {
                    return null;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        CloseableKt.closeFinally(fileInputStream, th);
                        throw th2;
                    } catch (Throwable unused2) {
                        return null;
                    }
                }
            }
        } catch (Throwable unused3) {
            return null;
        }
    }

    public final String j(String str, String str2) {
        if (StringsKt.startsWith$default(str, Constants.ATTRVAL_THIS, false, 2, (Object) null)) {
            return str2 + str;
        }
        if (StringsKt.contains$default(str, Constants.ATTRVAL_THIS, false, 2, (Object) null)) {
            return str;
        }
        return str2 + Constants.ATTRVAL_THIS + str;
    }
}
