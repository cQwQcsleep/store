package com.android.tools.r8.shaking;

import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.o3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3437o3 {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;

    /* JADX INFO: renamed from: com.android.tools.r8.shaking.o3$a */
    public static class a {
        public boolean a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
        public boolean h;

        private a() {
            this.a = false;
            this.b = false;
            this.c = false;
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = false;
            this.h = false;
        }
    }

    public C3437o3(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
        this.e = z5;
        this.f = z6;
        this.g = z7;
        this.h = z8;
    }

    public static void a(StringBuilder sb, boolean z, String str) {
        if (z) {
            if (sb.length() != 0) {
                sb.append(',');
            }
            sb.append(str);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C3437o3)) {
            return false;
        }
        C3437o3 c3437o3 = (C3437o3) obj;
        return this.a == c3437o3.a && this.b == c3437o3.b && this.c == c3437o3.c && this.d == c3437o3.d && this.e == c3437o3.e && this.f == c3437o3.f && this.g == c3437o3.g && this.h == c3437o3.h;
    }

    public final int hashCode() {
        return Objects.hash(Boolean.valueOf(this.a), Boolean.valueOf(this.b), Boolean.valueOf(this.c), Boolean.valueOf(this.d), Boolean.valueOf(this.e), Boolean.valueOf(this.f), Boolean.valueOf(this.g), Boolean.valueOf(this.h));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        a(sb, this.a, "allowaccessmodification");
        a(sb, this.b, "allowannotationremoval");
        a(sb, this.c, "allowrepackaging");
        a(sb, this.f, "allowobfuscation");
        a(sb, this.d, "allowshrinking");
        a(sb, this.e, "allowoptimization");
        a(sb, this.g, "includedescriptorclasses");
        a(sb, this.h, "allowpermittedsubclassesremoval");
        return sb.toString();
    }

    public static a a() {
        return new a();
    }
}
