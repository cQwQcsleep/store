package com.android.tools.r8.graph;

import defpackage.jfh;
import java.util.AbstractCollection;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R5 {
    public static final /* synthetic */ boolean d = true;
    public final I2 a;
    public int b = -1;
    public AbstractCollection c = S5.e;

    public R5(I2 i2) {
        this.a = i2;
    }

    public final void a(int i) {
        int i2 = this.b;
        if (i == i2) {
            return;
        }
        if (i2 == -2) {
            if (d || i == 1) {
                return;
            }
            x1f.a();
            return;
        }
        if (i != -2) {
            if (d || i2 == -1) {
                this.b = i;
                return;
            } else {
                x1f.a();
                return;
            }
        }
        if (d || i2 == 1 || i2 == -1) {
            this.b = -2;
        } else {
            x1f.a();
        }
    }

    public final boolean b() {
        boolean z = d;
        if (!z && this.b == -1) {
            s22.a("Program class missing: ", this);
            return false;
        }
        if (z || this.a.M0()) {
            return this.b == -2;
        }
        x1f.a();
        return false;
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.AbstractCollection, java.util.Set] */
    public final boolean equals(Object obj) {
        if (!(obj instanceof R5)) {
            return false;
        }
        R5 r5 = (R5) obj;
        return r5.a == this.a && r5.c.equals(this.c);
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.c);
    }

    public final String toString() {
        return "TypeInfo{" + this.a + ", level:" + this.b + "}";
    }

    public final void a() {
        if (this.c == S5.e) {
            this.c = new ConcurrentSkipListSet(new jfh());
        }
    }
}
