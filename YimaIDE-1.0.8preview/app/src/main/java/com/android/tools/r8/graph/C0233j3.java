package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0245l1;
import defpackage.x0g;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.j3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0233j3 implements InterfaceC0212g3 {
    public static final C0233j3 e;
    public static final /* synthetic */ boolean f = true;
    public final C0245l1 a;
    public int b;
    public AbstractC0187d c;
    public AbstractC0187d d;

    static {
        C0173b c0173b = C0173b.a;
        e = new C0233j3(null, 0, c0173b, c0173b);
    }

    public C0233j3(C0245l1 c0245l1, int i, AbstractC0187d abstractC0187d, AbstractC0187d abstractC0187d2) {
        this.a = c0245l1;
        this.b = i;
        this.c = abstractC0187d;
        this.d = abstractC0187d2;
    }

    public final void a() {
        boolean z = f;
        if (!z && d()) {
            x1f.a();
            return;
        }
        if (!z && (this.b & 1) != 0) {
            x1f.a();
        } else if (!z && (this.b & 2) != 0) {
            x1f.a();
        } else {
            this.c = C0173b.a;
            this.b &= -33;
        }
    }

    public final void b() {
        C0180c c0180c = C0180c.a;
        this.c = c0180c;
        this.d = c0180c;
    }

    public final void c() {
        this.c.a(this.a);
        this.d.a(this.a);
    }

    public final boolean d() {
        return (this.b & 8) != 0 || e();
    }

    public final boolean e() {
        return (this.b & 16) != 0;
    }

    public final boolean f() {
        if (!this.c.c()) {
            return true;
        }
        int i = this.b;
        return ((i & 8) == 0 && (i & 1) == 0 && (i & 2) == 0 && (i & 32) == 0) ? false : true;
    }

    public final boolean g() {
        return !this.d.c() || e() || h();
    }

    public final boolean h() {
        return (this.b & 4) != 0;
    }

    public final void i() {
        this.b |= 2;
    }

    public final void j() {
        this.b |= 32;
    }

    public final void k() {
        this.b |= 4;
    }

    public final /* synthetic */ boolean a(Set set, C0245l1 c0245l1) {
        return c0245l1 != this.a && set.add(c0245l1);
    }

    public final boolean a(Predicate predicate) {
        return (!this.d.b(predicate) || e() || h()) ? false : true;
    }

    public final boolean a(C0231j1 c0231j1) {
        return this.d.a(c0231j1) || e() || h();
    }

    public final void a(Consumer consumer, AbstractC0187d abstractC0187d, final Set set) {
        abstractC0187d.getClass();
        if (abstractC0187d instanceof C0173b) {
            return;
        }
        if (abstractC0187d instanceof C0166a) {
            abstractC0187d.a().a(consumer, new Predicate() { // from class: qbh
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.a(set, (C0245l1) obj);
                }
            });
        } else {
            x0g.a("Should never be iterating the indirect accesses when they are unknown");
        }
    }
}
