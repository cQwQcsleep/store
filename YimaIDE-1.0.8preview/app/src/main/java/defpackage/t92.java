package defpackage;

import java.io.File;
import java.nio.charset.Charset;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class t92 {
    public static final t92 a = new t92();

    public static final class b {
        public final String a;
        public final int b;
        public final int c;
        public final String d;

        public b(String str, int i, int i2, String str2) {
            str.getClass();
            str2.getClass();
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = str2;
        }

        public final int a() {
            return this.c;
        }

        public final int b() {
            return this.b;
        }

        public final String c() {
            return this.d;
        }

        public final String d() {
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
            return Intrinsics.areEqual(this.a, bVar.a) && this.b == bVar.b && this.c == bVar.c && Intrinsics.areEqual(this.d, bVar.d);
        }

        public int hashCode() {
            return (((((this.a.hashCode() * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + this.d.hashCode();
        }

        public String toString() {
            return "Hit(path=" + this.a + ", line=" + this.b + ", col=" + this.c + ", msg=" + this.d + ")";
        }
    }

    public static /* synthetic */ String c(t92 t92Var, String str, String str2, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str2 = null;
        }
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return t92Var.b(str, str2, i, i2);
    }

    public static final String j(File file, String str) {
        String strTrim = StringsKt.trim(StringsKt.trim(str).toString(), new char[]{'\"', '\'', '`'});
        if (file != null && !new File(strTrim).isAbsolute()) {
            File file2 = new File(file, strTrim);
            if (file2.exists()) {
                String absolutePath = file2.getAbsolutePath();
                absolutePath.getClass();
                return absolutePath;
            }
        }
        return strTrim;
    }

    public final int a(String str, int i) {
        Object obj;
        int iCoerceAtLeast;
        if (str == null || StringsKt.isBlank(str) || i < 0) {
            return 1;
        }
        try {
            Result.Companion companion = Result.Companion;
            String text$default = FilesKt.readText$default(new File(str), (Charset) null, 1, (Object) null);
            if (i > text$default.length()) {
                iCoerceAtLeast = 1;
            } else {
                int iCoerceIn = RangesKt.coerceIn(i, 0, text$default.length());
                while (iCoerceIn > 0) {
                    int i2 = iCoerceIn - 1;
                    if (text$default.charAt(i2) == '\n' || text$default.charAt(i2) == '\r') {
                        break;
                    }
                    iCoerceIn--;
                }
                iCoerceAtLeast = RangesKt.coerceAtLeast((i - iCoerceIn) + 1, 1);
            }
            obj = Result.constructor-impl(Integer.valueOf(iCoerceAtLeast));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = 1;
        }
        return ((Number) obj).intValue();
    }

    public final String b(String str, String str2, int i, int i2) {
        str.getClass();
        return d('e', str, str2, i, i2);
    }

    public final String d(char c, String str, String str2, int i, int i2) {
        str.getClass();
        String string = StringsKt.trim(str).toString();
        String strH = h(str2, i, i2);
        if (strH.length() == 0) {
            return c + ": " + string;
        }
        return c + ": " + strH + " " + string;
    }

    public final boolean e(String str) {
        str.getClass();
        String string = StringsKt.trimStart(str).toString();
        return StringsKt.startsWith$default(string, "e:", false, 2, (Object) null) || StringsKt.contains$default(string, "[Error]", false, 2, (Object) null) || StringsKt.contains$default(string, "[错误]", false, 2, (Object) null);
    }

    public final boolean f(String str) {
        str.getClass();
        String string = StringsKt.trimStart(str).toString();
        return StringsKt.startsWith$default(string, "w:", false, 2, (Object) null) || StringsKt.contains$default(string, "[Warning]", false, 2, (Object) null) || StringsKt.contains$default(string, "[警告]", false, 2, (Object) null);
    }

    public final Pair g(String str, int i) {
        str.getClass();
        if (str.length() == 0 || i <= 0) {
            return TuplesKt.to(1, 1);
        }
        int iCoerceIn = RangesKt.coerceIn(i, 0, str.length());
        int i2 = 1;
        int i3 = 0;
        for (int i4 = 0; i4 < iCoerceIn; i4++) {
            if (str.charAt(i4) == '\n') {
                i2++;
                i3 = i4 + 1;
            }
        }
        return TuplesKt.to(Integer.valueOf(i2), Integer.valueOf(RangesKt.coerceAtLeast((iCoerceIn - i3) + 1, 1)));
    }

    public final String h(String str, int i, int i2) {
        Object objReplace$default;
        if (str == null || StringsKt.isBlank(str)) {
            return "";
        }
        try {
            Result.Companion companion = Result.Companion;
            String string = new File(str).getAbsoluteFile().toURI().toString();
            string.getClass();
            if (StringsKt.startsWith$default(string, "file:/", false, 2, (Object) null) && !StringsKt.startsWith$default(string, "file://", false, 2, (Object) null)) {
                string = "file://" + StringsKt.removePrefix(string, "file:");
            }
            objReplace$default = Result.constructor-impl(string);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objReplace$default = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(objReplace$default) != null) {
            objReplace$default = StringsKt.replace$default(str, '\\', '/', false, 4, (Object) null);
        }
        String str2 = (String) objReplace$default;
        if (i > 0 && i2 > 0) {
            return str2 + ":" + i + ":" + i2;
        }
        if (i <= 0) {
            return str2;
        }
        return str2 + ":" + i + ":1";
    }

    /* JADX WARN: Code duplicated, block: B:36:0x019e  */
    /* JADX WARN: Code duplicated, block: B:52:0x020a  */
    /* JADX WARN: Code duplicated, block: B:55:0x0221 A[PHI: r10
      0x0221: PHI (r10v4 t92$b) = (r10v3 t92$b), (r10v7 t92$b) binds: [B:20:0x00fd, B:25:0x014f] A[DONT_GENERATE, DONT_INLINE]] */
    public final a i(String str, File file) {
        b bVar;
        b bVar2;
        int iIntValue;
        int iIntValue2;
        str.getClass();
        String string = StringsKt.trim(str).toString();
        b bVar3 = null;
        if (string.length() == 0) {
            return null;
        }
        if (StringsKt.startsWith$default(string, "e:", false, 2, (Object) null) || StringsKt.startsWith$default(string, "w:", false, 2, (Object) null)) {
            return new a(StringsKt.startsWith$default(string, "w:", false, 2, (Object) null) ? 'w' : 'e', StringsKt.trim(StringsKt.removePrefix(StringsKt.removePrefix(string, "e:"), "w:")).toString(), null, 0, 0, true, string);
        }
        MatchResult matchResultFind$default = Regex.find$default(new Regex("(?i)((?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\./)?[\\w.\\\\/\\- ()]+?\\.(?:xml|png|webp|jpg|jpeg|gif|json|txt|flat)):(\\d+):(\\d+):\\s*(?:error|warning)\\s*:\\s*(.*)$"), string, 0, 2, (Object) null);
        if (matchResultFind$default != null) {
            Object obj = matchResultFind$default.getGroupValues().get(1);
            obj.getClass();
            Object obj2 = matchResultFind$default.getGroupValues().get(2);
            obj2.getClass();
            int i = Integer.parseInt((String) obj2);
            Object obj3 = matchResultFind$default.getGroupValues().get(3);
            obj3.getClass();
            int i2 = Integer.parseInt((String) obj3);
            Object obj4 = matchResultFind$default.getGroupValues().get(4);
            obj4.getClass();
            bVar3 = new b((String) obj, i, i2, (String) obj4);
        } else {
            MatchResult matchResultFind$default2 = Regex.find$default(new Regex("(?i)((?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\./)?[\\w.\\\\/\\- ()]+?\\.(?:xml|png|webp|jpg|jpeg|gif|json|txt|flat)):(\\d+):\\s*(?:error|warning)\\s*:\\s*(.*)$"), string, 0, 2, (Object) null);
            if (matchResultFind$default2 != null) {
                Object obj5 = matchResultFind$default2.getGroupValues().get(1);
                obj5.getClass();
                Object obj6 = matchResultFind$default2.getGroupValues().get(2);
                obj6.getClass();
                int i3 = Integer.parseInt((String) obj6);
                Object obj7 = matchResultFind$default2.getGroupValues().get(3);
                obj7.getClass();
                bVar3 = new b((String) obj5, i3, 1, (String) obj7);
            } else {
                MatchResult matchResultFind$default3 = Regex.find$default(new Regex("(?i)(?:error|warning)\\s*:\\s*((?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\./)?[\\w.\\\\/\\- ()]+?\\.(?:xml|png|webp|jpg|jpeg|gif|json|txt|flat)):(\\d+):\\s*(.*)$"), string, 0, 2, (Object) null);
                if (matchResultFind$default3 != null) {
                    Object obj8 = matchResultFind$default3.getGroupValues().get(1);
                    obj8.getClass();
                    Object obj9 = matchResultFind$default3.getGroupValues().get(2);
                    obj9.getClass();
                    int i4 = Integer.parseInt((String) obj9);
                    Object obj10 = matchResultFind$default3.getGroupValues().get(3);
                    obj10.getClass();
                    bVar = new b((String) obj8, i4, 1, (String) obj10);
                } else {
                    bVar = null;
                }
                if (bVar != null) {
                    bVar3 = bVar;
                } else {
                    MatchResult matchResultFind$default4 = Regex.find$default(new Regex("(?i)((?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\./)?[\\w.\\\\/\\- ()]+?\\.(?:xml|png|webp|jpg|jpeg|gif|json|txt|flat))\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s*:\\s*(?:error|warning)\\s*:\\s*(.*)$"), string, 0, 2, (Object) null);
                    if (matchResultFind$default4 != null) {
                        Object obj11 = matchResultFind$default4.getGroupValues().get(1);
                        obj11.getClass();
                        Object obj12 = matchResultFind$default4.getGroupValues().get(2);
                        obj12.getClass();
                        int i5 = Integer.parseInt((String) obj12);
                        Object obj13 = matchResultFind$default4.getGroupValues().get(3);
                        obj13.getClass();
                        int i6 = Integer.parseInt((String) obj13);
                        Object obj14 = matchResultFind$default4.getGroupValues().get(4);
                        obj14.getClass();
                        bVar = new b((String) obj11, i5, i6, (String) obj14);
                    } else {
                        bVar = null;
                    }
                    if (bVar == null) {
                        MatchResult matchResultFind$default5 = Regex.find$default(new Regex("(?i)((?:[a-zA-Z]:[\\\\/]|[\\\\/])[\\w.\\\\/\\- ()]*?[\\\\/]res[\\\\/][\\w.\\\\/\\- ()]+?\\.(?:xml|png|webp|jpg|jpeg|gif)):(\\d+)(?::(\\d+))?\\s*:\\s*(.*)$"), string, 0, 2, (Object) null);
                        if (matchResultFind$default5 != null) {
                            Object obj15 = matchResultFind$default5.getGroupValues().get(1);
                            obj15.getClass();
                            String str2 = (String) obj15;
                            Object obj16 = matchResultFind$default5.getGroupValues().get(2);
                            obj16.getClass();
                            int i7 = Integer.parseInt((String) obj16);
                            Object obj17 = matchResultFind$default5.getGroupValues().get(3);
                            obj17.getClass();
                            Integer intOrNull = StringsKt.toIntOrNull((String) obj17);
                            if (intOrNull == null) {
                                iIntValue2 = 1;
                            } else {
                                if (intOrNull.intValue() <= 0) {
                                    intOrNull = null;
                                }
                                if (intOrNull != null) {
                                    iIntValue2 = intOrNull.intValue();
                                } else {
                                    iIntValue2 = 1;
                                }
                            }
                            CharSequence charSequence = (CharSequence) matchResultFind$default5.getGroupValues().get(4);
                            if (StringsKt.isBlank(charSequence)) {
                                charSequence = string;
                            }
                            charSequence.getClass();
                            bVar2 = new b(str2, i7, iIntValue2, (String) charSequence);
                        } else {
                            bVar2 = null;
                        }
                        if (bVar2 == null) {
                            MatchResult matchResultFind$default6 = Regex.find$default(new Regex("(?i)((?:res|\\.)[\\\\/][\\w.\\\\/\\- ()]+?\\.(?:xml|png|webp|jpg|jpeg|gif|json|txt|flat)):(\\d+)(?::(\\d+))?:\\s*(?:error|warning)\\s*:\\s*(.*)$"), string, 0, 2, (Object) null);
                            if (matchResultFind$default6 != null) {
                                Object obj18 = matchResultFind$default6.getGroupValues().get(1);
                                obj18.getClass();
                                String str3 = (String) obj18;
                                Object obj19 = matchResultFind$default6.getGroupValues().get(2);
                                obj19.getClass();
                                int i8 = Integer.parseInt((String) obj19);
                                Object obj20 = matchResultFind$default6.getGroupValues().get(3);
                                obj20.getClass();
                                Integer intOrNull2 = StringsKt.toIntOrNull((String) obj20);
                                if (intOrNull2 == null) {
                                    iIntValue = 1;
                                } else {
                                    Integer num = intOrNull2.intValue() > 0 ? intOrNull2 : null;
                                    if (num != null) {
                                        iIntValue = num.intValue();
                                    } else {
                                        iIntValue = 1;
                                    }
                                }
                                Object obj21 = matchResultFind$default6.getGroupValues().get(4);
                                obj21.getClass();
                                bVar3 = new b(str3, i8, iIntValue, (String) obj21);
                            }
                        } else {
                            bVar3 = bVar2;
                        }
                    } else {
                        bVar3 = bVar;
                    }
                }
            }
        }
        char c = StringsKt.contains(string, "warning", true) ? 'w' : 'e';
        if (bVar3 == null) {
            return new a(c, string, null, 0, 0, false, null, 96, null);
        }
        String strC = bVar3.c();
        return new a(c, StringsKt.isBlank(strC) ? string : strC, j(file, bVar3.d()), bVar3.b(), RangesKt.coerceAtLeast(bVar3.a(), 1), false, null, 96, null);
    }

    public final String k(String str, String str2, int i, int i2) {
        str.getClass();
        return d('w', str, str2, i, i2);
    }

    public static final class a {
        public final char a;
        public final String b;
        public final String c;
        public final int d;
        public final int e;
        public final boolean f;
        public final String g;

        public /* synthetic */ a(char c, String str, String str2, int i, int i2, boolean z, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(c, str, str2, i, i2, (i3 & 32) != 0 ? false : z, (i3 & 64) != 0 ? "" : str3);
        }

        public final String a() {
            if (!this.f || StringsKt.isBlank(this.g)) {
                return this.a == 'w' ? t92.a.k(this.b, this.c, this.d, this.e) : t92.a.b(this.b, this.c, this.d, this.e);
            }
            return this.g;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c) && this.d == aVar.d && this.e == aVar.e && this.f == aVar.f && Intrinsics.areEqual(this.g, aVar.g);
        }

        public int hashCode() {
            int iHashCode = ((Character.hashCode(this.a) * 31) + this.b.hashCode()) * 31;
            String str = this.c;
            return ((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Boolean.hashCode(this.f)) * 31) + this.g.hashCode();
        }

        public String toString() {
            return "ParsedDiag(severity=" + this.a + ", message=" + this.b + ", path=" + this.c + ", line=" + this.d + ", column=" + this.e + ", alreadyFormatted=" + this.f + ", raw=" + this.g + ")";
        }

        public a(char c, String str, String str2, int i, int i2, boolean z, String str3) {
            str.getClass();
            str3.getClass();
            this.a = c;
            this.b = str;
            this.c = str2;
            this.d = i;
            this.e = i2;
            this.f = z;
            this.g = str3;
        }
    }
}
