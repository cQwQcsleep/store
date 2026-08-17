package defpackage;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ubc {
    public static volatile a b;
    public static final ubc a = new ubc();
    public static final int c = 8;

    public static final class a {
        public final String a;
        public final String b;
        public final tbc$a c;

        public a(String str, String str2, tbc$a tbc_a) {
            str.getClass();
            str2.getClass();
            tbc_a.getClass();
            this.a = str;
            this.b = str2;
            this.c = tbc_a;
        }

        public final tbc$a a() {
            return this.c;
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
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

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "Session(unlockId=" + this.a + ", key=" + this.b + ", grant=" + this.c + ")";
        }
    }

    public final void a() {
        b = null;
    }

    public final a b() {
        return b;
    }

    public final boolean c(String str) {
        str.getClass();
        a aVar = b;
        return aVar != null && Intrinsics.areEqual(aVar.a().b(), aVar.c()) && Intrinsics.areEqual(aVar.a().a(), str) && !StringsKt.isBlank(aVar.b());
    }

    public final void d(a aVar) {
        aVar.getClass();
        b = aVar;
    }
}
