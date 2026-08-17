package defpackage;

import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class jw {

    public static final class a extends jw {
        public final Map a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Map map) {
            super(null);
            map.getClass();
            this.a = map;
        }

        public final Map a() {
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
            return "Answered(answers=" + this.a + ")";
        }
    }

    public static final class b extends jw {
        public static final b a = new b();

        public b() {
            super(null);
        }

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof b);
        }

        public int hashCode() {
            return -950503508;
        }

        public String toString() {
            return "Cancelled";
        }
    }

    public static final class c extends jw {
        public static final c a = new c();

        public c() {
            super(null);
        }

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof c);
        }

        public int hashCode() {
            return -942305205;
        }

        public String toString() {
            return "Skipped";
        }
    }

    public /* synthetic */ jw(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public jw() {
    }
}
