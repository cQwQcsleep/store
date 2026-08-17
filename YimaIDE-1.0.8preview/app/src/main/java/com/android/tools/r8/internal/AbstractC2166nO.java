package com.android.tools.r8.internal;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2166nO {
    public static final /* synthetic */ boolean a = true;

    /* JADX INFO: renamed from: com.android.tools.r8.internal.nO$a */
    public static class a extends AbstractC2166nO {
        public static final /* synthetic */ boolean e = true;
        public final int b;
        public boolean c = true;
        public boolean d;

        public a(int i) {
            this.b = i;
        }

        @Override // com.android.tools.r8.internal.AbstractC2166nO
        public boolean a() {
            return this.b == 1;
        }

        @Override // com.android.tools.r8.internal.AbstractC2166nO
        public boolean b() {
            return this.b == 2;
        }

        @Override // com.android.tools.r8.internal.AbstractC2166nO
        public boolean c() {
            return this.b == 3;
        }

        @Override // com.android.tools.r8.internal.AbstractC2166nO
        public boolean d() {
            return this.c;
        }

        @Override // com.android.tools.r8.internal.AbstractC2166nO
        public final boolean f() {
            return this.d;
        }
    }

    public static int a(C0333y c0333y) {
        c0333y.Q().getClass();
        if (c0333y.o()) {
            return 3;
        }
        if (c0333y.M().j instanceof ClassFileConsumer) {
            return 1;
        }
        if (a || c0333y.M().Z()) {
            return 2;
        }
        x1f.a();
        return 0;
    }

    public static a b(C0333y<?> c0333y) {
        if (a || !c0333y.o()) {
            return new a(a(c0333y));
        }
        x1f.a();
        return null;
    }

    public static a c(C0333y c0333y) {
        if (!c0333y.o()) {
            return b(c0333y);
        }
        if (a || c0333y.Q().d()) {
            return new a(a(c0333y));
        }
        x1f.a();
        return null;
    }

    public static a d(C0333y<?> c0333y) {
        if (!c0333y.o()) {
            return b(c0333y);
        }
        if (a || c0333y.Q().a1 == 3) {
            return new a(c0333y.M().j instanceof ClassFileConsumer ? 1 : 2);
        }
        x1f.a();
        return null;
    }

    public static a e() {
        return new C2252oO();
    }

    public abstract boolean a();

    public abstract boolean b();

    public abstract boolean c();

    public abstract boolean d();

    public abstract boolean f();

    public final AbstractC0861Tt a(C0333y c0333y, com.android.tools.r8.ir.optimize.F f) {
        if (c()) {
            return new C1272cu(c0333y);
        }
        if (a()) {
            return new C1104au(c0333y, f);
        }
        if (a || b()) {
            return new C1190bu(c0333y, f);
        }
        x1f.a();
        return null;
    }
}
