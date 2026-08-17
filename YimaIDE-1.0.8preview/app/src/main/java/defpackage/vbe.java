package defpackage;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class vbe {
    public static final vbe a = new vbe();

    public enum c {
        Exact,
        WhitespaceFlexible;

        public static final /* synthetic */ EnumEntries e = EnumEntriesKt.enumEntries(b());
    }

    public static final class d {
        public final int a;
        public final int b;
        public final c c;

        public d(int i, int i2, c cVar) {
            cVar.getClass();
            this.a = i;
            this.b = i2;
            this.c = cVar;
        }

        public final int a() {
            return this.b;
        }

        public final c b() {
            return this.c;
        }

        public final int c() {
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
            return this.a == dVar.a && this.b == dVar.b && this.c == dVar.c;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.a) * 31) + Integer.hashCode(this.b)) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "Span(start=" + this.a + ", endExclusive=" + this.b + ", mode=" + this.c + ")";
        }
    }

    public final a a(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        if (str2.length() == 0) {
            return a.b.a;
        }
        if (str2.length() > 20000) {
            return a.c.a;
        }
        b bVarE = e(str, str2);
        if (!(bVarE instanceof b.c)) {
            if (bVarE instanceof b.a) {
                b.a aVar = (b.a) bVarE;
                return new a.C0014a(aVar.a(), f(str, aVar.b(), aVar.a()));
            }
            if (Intrinsics.areEqual(bVarE, b.C0015b.a)) {
                return a.d.a;
            }
            bu8.a();
            return null;
        }
        b.c cVar = (b.c) bVarE;
        return new a.e(str.substring(0, cVar.a().c()) + str3 + str.substring(cVar.a().a()), cVar.a().b());
    }

    public final String b(String str) {
        str.getClass();
        if (str.length() == 0) {
            return null;
        }
        for (int i = 0; i < str.length(); i++) {
            if (CharsKt.isWhitespace(str.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                int i2 = 0;
                boolean z = true;
                while (i2 < str.length()) {
                    if (CharsKt.isWhitespace(str.charAt(i2))) {
                        while (i2 < str.length() && CharsKt.isWhitespace(str.charAt(i2))) {
                            i2++;
                        }
                        boolean z2 = i2 >= str.length();
                        if (z) {
                            sb.append("\\s*");
                        } else if (z2) {
                            sb.append("\\s*");
                        } else {
                            sb.append("\\s+");
                        }
                    } else {
                        sb.append(Regex.Companion.escape(String.valueOf(str.charAt(i2))));
                        i2++;
                    }
                    z = false;
                }
                String string = sb.toString();
                if (StringsKt.replace$default(StringsKt.replace$default(string, "\\s*", "", false, 4, (Object) null), "\\s+", "", false, 4, (Object) null).length() == 0) {
                    return null;
                }
                return string;
            }
        }
        return null;
    }

    public final List c(String str, String str2) {
        String str3;
        String str4;
        int iIndexOf$default;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length() - str2.length() && (iIndexOf$default = StringsKt.indexOf$default((str3 = str), (str4 = str2), i, false, 4, (Object) null)) >= 0) {
            arrayList.add(new d(iIndexOf$default, str4.length() + iIndexOf$default, c.Exact));
            i = iIndexOf$default + 1;
            if (arrayList.size() > 50) {
                break;
            }
            str = str3;
            str2 = str4;
        }
        return arrayList;
    }

    public final List d(String str, String str2) {
        String strB = b(str2);
        if (strB == null) {
            return CollectionsKt.emptyList();
        }
        try {
            Regex regex = new Regex(strB, SetsKt.setOf(RegexOption.DOT_MATCHES_ALL));
            ArrayList arrayList = new ArrayList();
            for (MatchResult matchResult : Regex.findAll$default(regex, str, 0, 2, (Object) null)) {
                arrayList.add(new d(matchResult.getRange().getFirst(), matchResult.getRange().getLast() + 1, c.WhitespaceFlexible));
                if (arrayList.size() > 50) {
                    break;
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return CollectionsKt.emptyList();
        }
    }

    public final b e(String str, String str2) {
        str.getClass();
        str2.getClass();
        if (str2.length() != 0 && str2.length() <= 20000) {
            List listC = c(str, str2);
            if (listC.size() == 1) {
                return new b.c((d) listC.get(0));
            }
            if (listC.size() > 1) {
                return new b.a(listC.size(), CollectionsKt.take(listC, 3));
            }
            List listD = d(str, str2);
            if (listD.isEmpty()) {
                return b.C0015b.a;
            }
            return listD.size() == 1 ? new b.c((d) listD.get(0)) : new b.a(listD.size(), CollectionsKt.take(listD, 3));
        }
        return b.C0015b.a;
    }

    public final String f(String str, List list, int i) {
        str.getClass();
        list.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("共 " + i + " 处匹配，拒绝修改以免改错。请扩大 old_text 上下文使唯一。示意：");
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            d dVar = (d) obj;
            String strSubstring = str.substring(0, dVar.c());
            int i4 = 0;
            for (int i5 = 0; i5 < strSubstring.length(); i5++) {
                if (strSubstring.charAt(i5) == '\n') {
                    i4++;
                }
            }
            sb.append("\n  [" + i3 + "] 约第 " + (i4 + 1) + " 行: 「" + StringsKt.replace$default(str.substring(dVar.c(), Math.min(dVar.a(), dVar.c() + 60)), "\n", "\\n", false, 4, (Object) null) + "」");
            i2 = i3;
        }
        if (i > list.size()) {
            sb.append("\n  …另有 " + (i - list.size()) + " 处未列出");
        }
        return sb.toString();
    }

    public static abstract class a {

        /* JADX INFO: renamed from: vbe$a$a, reason: collision with other inner class name */
        public static final class C0014a extends a {
            public final int a;
            public final String b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0014a(int i, String str) {
                super(null);
                str.getClass();
                this.a = i;
                this.b = str;
            }

            public final int a() {
                return this.a;
            }

            public final String b() {
                return this.b;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof C0014a)) {
                    return false;
                }
                C0014a c0014a = (C0014a) obj;
                return this.a == c0014a.a && Intrinsics.areEqual(this.b, c0014a.b);
            }

            public int hashCode() {
                return (Integer.hashCode(this.a) * 31) + this.b.hashCode();
            }

            public String toString() {
                return "Ambiguous(count=" + this.a + ", hint=" + this.b + ")";
            }
        }

        public static final class b extends a {
            public static final b a = new b();

            public b() {
                super(null);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof b);
            }

            public int hashCode() {
                return -129526202;
            }

            public String toString() {
                return "EmptyOld";
            }
        }

        public static final class c extends a {
            public static final c a = new c();

            public c() {
                super(null);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof c);
            }

            public int hashCode() {
                return 1060093858;
            }

            public String toString() {
                return "NeedleTooLarge";
            }
        }

        public static final class d extends a {
            public static final d a = new d();

            public d() {
                super(null);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof d);
            }

            public int hashCode() {
                return 760986749;
            }

            public String toString() {
                return "NotMatched";
            }
        }

        public static final class e extends a {
            public final String a;
            public final c b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public e(String str, c cVar) {
                super(null);
                str.getClass();
                cVar.getClass();
                this.a = str;
                this.b = cVar;
            }

            public final c a() {
                return this.b;
            }

            public final String b() {
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
                return Intrinsics.areEqual(this.a, eVar.a) && this.b == eVar.b;
            }

            public int hashCode() {
                return (this.a.hashCode() * 31) + this.b.hashCode();
            }

            public String toString() {
                return "Success(updated=" + this.a + ", mode=" + this.b + ")";
            }
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public static abstract class b {

        public static final class a extends b {
            public final int a;
            public final List b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(int i, List list) {
                super(null);
                list.getClass();
                this.a = i;
                this.b = list;
            }

            public final int a() {
                return this.a;
            }

            public final List b() {
                return this.b;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof a)) {
                    return false;
                }
                a aVar = (a) obj;
                return this.a == aVar.a && Intrinsics.areEqual(this.b, aVar.b);
            }

            public int hashCode() {
                return (Integer.hashCode(this.a) * 31) + this.b.hashCode();
            }

            public String toString() {
                return "Ambiguous(count=" + this.a + ", samples=" + this.b + ")";
            }
        }

        /* JADX INFO: renamed from: vbe$b$b, reason: collision with other inner class name */
        public static final class C0015b extends b {
            public static final C0015b a = new C0015b();

            public C0015b() {
                super(null);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof C0015b);
            }

            public int hashCode() {
                return -1820745145;
            }

            public String toString() {
                return "None";
            }
        }

        public static final class c extends b {
            public final d a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(d dVar) {
                super(null);
                dVar.getClass();
                this.a = dVar;
            }

            public final d a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof c) && Intrinsics.areEqual(this.a, ((c) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Unique(span=" + this.a + ")";
            }
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public b() {
        }
    }
}
