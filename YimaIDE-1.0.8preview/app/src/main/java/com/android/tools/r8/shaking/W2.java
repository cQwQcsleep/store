package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.Kg0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class W2 {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final boolean d;

    public W2(boolean z, boolean z2, boolean z3, boolean z4) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
    }

    public static a a() {
        return new a();
    }

    public static class a {
        public boolean a;
        public boolean b;
        public boolean c;
        public boolean d;

        public W2 a() {
            return new W2(this.a, this.b, this.c, this.d);
        }

        public final a b() {
            this.a = Kg0.a("com.android.tools.r8.experimental.enablecheckenumunboxed", System.getProperty("com.android.tools.r8.experimental.enablecheckenumunboxed"), false);
            this.b = Kg0.a("com.android.tools.r8.experimental.enableconvertchecknotnull", System.getProperty("com.android.tools.r8.experimental.enableconvertchecknotnull"), false);
            this.c = Kg0.a("com.android.tools.r8.experimental.enablewhyareyounotinlining", System.getProperty("com.android.tools.r8.experimental.enablewhyareyounotinlining"), false);
            this.d = Kg0.a("com.android.tools.r8.allowTestProguardOptions", System.getProperty("com.android.tools.r8.allowTestProguardOptions"), false);
            return this;
        }

        public a c(boolean z) {
            this.c = z;
            return this;
        }

        public a d(boolean z) {
            this.d = z;
            return this;
        }

        public a a(boolean z) {
            this.a = z;
            return this;
        }

        public a b(boolean z) {
            this.b = z;
            return this;
        }
    }
}
