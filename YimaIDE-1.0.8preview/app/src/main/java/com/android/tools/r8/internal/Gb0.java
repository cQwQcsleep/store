package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Gb0 {
    public static final /* synthetic */ boolean a = true;

    public abstract Gb0 a(C0333y c0333y, com.android.tools.r8.graph.proto.c cVar, Lb0 lb0);

    public final Gb0 a(Gb0 gb0) {
        if (this instanceof C1624h2) {
            return gb0;
        }
        gb0.getClass();
        if (gb0 instanceof C1624h2) {
            return this;
        }
        if ((this instanceof EQ) || (gb0 instanceof EQ)) {
            return EQ.b;
        }
        if (this instanceof Jb0) {
            return ((Jb0) this).b(gb0);
        }
        if (gb0 instanceof Jb0) {
            return gb0.a().b(this);
        }
        boolean z = a;
        if (!z && !d() && !(this instanceof Kb0)) {
            x1f.a();
            return null;
        }
        if (z || gb0.d() || (gb0 instanceof Kb0)) {
            return new Jb0(AbstractC0551Hu.a(this, gb0));
        }
        x1f.a();
        return null;
    }

    public abstract boolean a(AbstractC1047aC abstractC1047aC);

    public Kb0 b() {
        return null;
    }

    public boolean c() {
        return this instanceof C1624h2;
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return this instanceof Jb0;
    }

    public boolean f() {
        return this instanceof Kb0;
    }

    public boolean g() {
        return this instanceof EQ;
    }

    public Jb0 a() {
        return null;
    }

    public final Mb0 a(Supplier supplier) {
        if (this instanceof EQ) {
            return Mb0.c;
        }
        Mb0 mb0 = (Mb0) supplier.get();
        return new Mb0(mb0.a.a(this), mb0.b);
    }
}
