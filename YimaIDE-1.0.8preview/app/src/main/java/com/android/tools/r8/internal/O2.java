package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class O2 {
    public static final /* synthetic */ boolean c = true;
    public final EnumC3077y2 a;
    public final EnumC3077y2 b;

    public O2(EnumC3077y2 enumC3077y2, EnumC3077y2 enumC3077y3) {
        if (!c && enumC3077y3 != null && !enumC3077y2.a(enumC3077y3)) {
            x1f.a();
            throw null;
        }
        this.a = enumC3077y2;
        this.b = enumC3077y3;
    }

    public boolean a(O2 o2) {
        EnumC3077y2 enumC3077y2 = this.b;
        if (enumC3077y2 == null) {
            enumC3077y2 = EnumC3077y2.c;
        }
        EnumC3077y2 enumC3077y3 = o2.b;
        if (enumC3077y3 == null) {
            enumC3077y3 = EnumC3077y2.c;
        }
        return enumC3077y2.b(o2.a) && enumC3077y3.b(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof O2)) {
            return false;
        }
        O2 o2 = (O2) obj;
        return this.a.equals(o2.a) && Objects.equals(this.b, o2.b);
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }

    public final String toString() {
        Object obj = this.b;
        if (obj == null) {
            obj = "B";
        }
        return "[ " + obj + " ; " + this.a + " ]";
    }
}
