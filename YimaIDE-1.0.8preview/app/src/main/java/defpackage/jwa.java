package defpackage;

import java.util.List;
import java.util.Locale;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class jwa {
    public static final jwa a = new jwa();
    public static final List b = CollectionsKt.listOf((Object[]) new String[]{"com.android", "com.google", "android", "java", "javax"});
    public static final Regex c = new Regex("^[a-z][a-z0-9_]*$");
    public static final int d = 8;

    public final String a(String str, String str2) {
        str.getClass();
        str2.getClass();
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return "com.example." + b(str2, "p" + StringsKt.take(new Regex("[^a-z0-9]").replace(lowerCase, ""), 6));
    }

    public final String b(String str, String str2) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        String strTake = StringsKt.take(new Regex("[^a-z0-9_]").replace(lowerCase, ""), 24);
        if (strTake.length() >= 2) {
            str2 = strTake;
        }
        if (str2.length() == 0) {
            str2 = "app";
        }
        char cCharAt = str2.charAt(0);
        return ('a' > cCharAt || cCharAt >= '{') ? "a".concat(str2) : str2;
    }

    public final a c(String str) {
        str.getClass();
        if (StringsKt.isBlank(str)) {
            return new a.C0084a("包名不能为空");
        }
        if (StringsKt.contains$default(str, " ", false, 2, (Object) null)) {
            return new a.C0084a("包名不能包含空格");
        }
        List listSplit$default = StringsKt.split$default(str, new String[]{"."}, false, 0, 6, (Object) null);
        if (listSplit$default.size() < 3) {
            return new a.C0084a("包名至少需要3级，如 com.example.app");
        }
        for (Object obj : listSplit$default) {
            obj.getClass();
            String str2 = (String) obj;
            if (str2.length() == 0) {
                return new a.C0084a("包名中不能有连续的点号");
            }
            if (!c.matches(str2)) {
                return new a.C0084a("包名段「" + str2 + "」格式错误：只能包含小写字母、数字、下划线，且以小写字母开头");
            }
        }
        for (Object obj2 : b) {
            obj2.getClass();
            String str3 = (String) obj2;
            if (StringsKt.startsWith$default(str, str3, false, 2, (Object) null)) {
                return new a.C0084a("不能使用保留前缀: " + str3);
            }
        }
        return a.b.a;
    }

    public static abstract class a {

        /* JADX INFO: renamed from: jwa$a$a, reason: collision with other inner class name */
        public static final class C0084a extends a {
            public final String a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0084a(String str) {
                super(null);
                str.getClass();
                this.a = str;
            }

            public final String b() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof C0084a) && Intrinsics.areEqual(this.a, ((C0084a) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Invalid(message=" + this.a + ")";
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
                return 1374151260;
            }

            public String toString() {
                return "Valid";
            }
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean a() {
            return this instanceof b;
        }

        public a() {
        }
    }
}
