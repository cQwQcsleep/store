package com.android.tools.r8.internal;

import java.util.function.BooleanSupplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class E6 {
    public boolean a;
    public boolean b = false;

    public E6(boolean z) {
        b(z);
    }

    public final void a(BooleanSupplier booleanSupplier) {
        if (d()) {
            b(booleanSupplier.getAsBoolean());
        }
    }

    public final Boolean b() {
        boolean zA = a();
        f();
        return Boolean.valueOf(zA);
    }

    public final boolean c() {
        return this.b;
    }

    public boolean d() {
        return !a();
    }

    public boolean e() {
        return a();
    }

    public void f() {
        b(true);
    }

    public void g() {
        b(false);
    }

    public E6() {
    }

    public void b(boolean z) {
        this.b = true;
        this.a = z;
    }

    public boolean a() {
        return this.a;
    }

    public final void a(boolean z) {
        b(z || this.a);
    }
}
