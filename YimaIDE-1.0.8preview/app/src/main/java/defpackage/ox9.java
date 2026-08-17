package defpackage;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ox9 {

    public static final class a implements ox9 {
        public final List a;

        public a(List list) {
            list.getClass();
            this.a = list;
        }

        public final List a() {
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
            return "BulletList(items=" + this.a + ")";
        }
    }

    public static final class b implements ox9 {
        public final String a;
        public final String b;

        public b(String str, String str2) {
            str.getClass();
            str2.getClass();
            this.a = str;
            this.b = str2;
        }

        public final String a() {
            return this.a;
        }

        public final String b() {
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
            return Intrinsics.areEqual(this.a, bVar.a) && Intrinsics.areEqual(this.b, bVar.b);
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }

        public String toString() {
            return "Code(code=" + this.a + ", lang=" + this.b + ")";
        }
    }

    public static final class c implements ox9 {
        public final int a;
        public final String b;

        public c(int i, String str) {
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
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.a == cVar.a && Intrinsics.areEqual(this.b, cVar.b);
        }

        public int hashCode() {
            return (Integer.hashCode(this.a) * 31) + this.b.hashCode();
        }

        public String toString() {
            return "Heading(level=" + this.a + ", text=" + this.b + ")";
        }
    }

    public static final class d implements ox9 {
        public final List a;
        public final int b;

        public d(List list, int i) {
            list.getClass();
            this.a = list;
            this.b = i;
        }

        public final List a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            return Intrinsics.areEqual(this.a, dVar.a) && this.b == dVar.b;
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + Integer.hashCode(this.b);
        }

        public String toString() {
            return "OrderedList(items=" + this.a + ", start=" + this.b + ")";
        }
    }

    public static final class e implements ox9 {
        public final String a;

        public e(String str) {
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
            return (obj instanceof e) && Intrinsics.areEqual(this.a, ((e) obj).a);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return "Paragraph(text=" + this.a + ")";
        }
    }

    public static final class f implements ox9 {
        public final String a;

        public f(String str) {
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
            return (obj instanceof f) && Intrinsics.areEqual(this.a, ((f) obj).a);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return "Quote(text=" + this.a + ")";
        }
    }

    public static final class g implements ox9 {
        public final List a;
        public final List b;

        public g(List list, List list2) {
            list.getClass();
            list2.getClass();
            this.a = list;
            this.b = list2;
        }

        public final List a() {
            return this.a;
        }

        public final List b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof g)) {
                return false;
            }
            g gVar = (g) obj;
            return Intrinsics.areEqual(this.a, gVar.a) && Intrinsics.areEqual(this.b, gVar.b);
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }

        public String toString() {
            return "Table(headers=" + this.a + ", rows=" + this.b + ")";
        }
    }
}
