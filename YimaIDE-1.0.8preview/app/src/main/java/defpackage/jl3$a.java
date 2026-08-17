package defpackage;

import io.github.rosemoe.sora.widget.CodeEditor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class jl3$a {
    public final int a;
    public final String b;
    public final int c;
    public final int d;
    public final String e;
    public final String f;
    public final boolean g;
    public final boolean h;
    public final String i;

    public jl3$a(int i, String str, int i2, int i3, String str2, String str3, boolean z, boolean z2, String str4) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        this.a = i;
        this.b = str;
        this.c = i2;
        this.d = i3;
        this.e = str2;
        this.f = str3;
        this.g = z;
        this.h = z2;
        this.i = str4;
    }

    public final String a() {
        return this.b;
    }

    public final int b() {
        return this.c;
    }

    public final String c() {
        return this.i;
    }

    public final int d() {
        return this.a;
    }

    public final String e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jl3$a)) {
            return false;
        }
        jl3$a jl3_a = (jl3$a) obj;
        return this.a == jl3_a.a && Intrinsics.areEqual(this.b, jl3_a.b) && this.c == jl3_a.c && this.d == jl3_a.d && Intrinsics.areEqual(this.e, jl3_a.e) && Intrinsics.areEqual(this.f, jl3_a.f) && this.g == jl3_a.g && this.h == jl3_a.h && Intrinsics.areEqual(this.i, jl3_a.i);
    }

    public final String f() {
        return this.e;
    }

    public final int g() {
        return this.d;
    }

    public int hashCode() {
        return (((((((((((((((Integer.hashCode(this.a) * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + Boolean.hashCode(this.g)) * 31) + Boolean.hashCode(this.h)) * 31) + this.i.hashCode();
    }

    public String toString() {
        return "UiProgress(percent=" + this.a + ", detail=" + this.b + ", done=" + this.c + ", total=" + this.d + ", sizeLabel=" + this.e + ", phase=" + this.f + ", finished=" + this.g + ", failed=" + this.h + ", message=" + this.i + ")";
    }

    public /* synthetic */ jl3$a(int i, String str, int i2, int i3, String str2, String str3, boolean z, boolean z2, String str4, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, i2, i3, (i4 & 16) != 0 ? "" : str2, (i4 & 32) != 0 ? "download" : str3, (i4 & 64) != 0 ? false : z, (i4 & CodeEditor.FLAG_DRAW_SOFT_WRAP) != 0 ? false : z2, (i4 & 256) != 0 ? "" : str4);
    }
}
