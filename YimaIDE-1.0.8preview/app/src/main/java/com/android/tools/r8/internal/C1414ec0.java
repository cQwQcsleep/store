package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ec0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1414ec0 extends JR implements Comparable, InterfaceC2705th {
    public static final /* synthetic */ boolean e = true;
    public C1414ec0 c;
    public final TreeSet d;

    public C1414ec0(com.android.tools.r8.graph.B5 b5) {
        super(b5);
        this.c = null;
        this.d = new TreeSet();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final boolean a(InterfaceC2705th interfaceC2705th) {
        C1414ec0 c1414ec0 = (C1414ec0) interfaceC2705th;
        C1414ec0 c1414ec1 = this.c;
        return c1414ec1 != null && c1414ec1 == c1414ec0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final boolean b(InterfaceC2705th interfaceC2705th) {
        return this.d.contains((C1414ec0) interfaceC2705th);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final void c(InterfaceC2705th interfaceC2705th) {
        throw new Kk0();
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return a().getReference().compareTo(((C1414ec0) obj).a().getReference());
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final /* bridge */ /* synthetic */ boolean d(InterfaceC2705th interfaceC2705th) {
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final void e(InterfaceC2705th interfaceC2705th) {
        C1414ec0 c1414ec0 = (C1414ec0) interfaceC2705th;
        boolean z = e;
        if (!z && this.c == null) {
            x1f.a();
            return;
        }
        if (!z && this.c != c1414ec0) {
            x1f.a();
            return;
        }
        boolean zRemove = c1414ec0.d.remove(this);
        if (z || zRemove) {
            this.c = null;
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final /* bridge */ /* synthetic */ boolean f(InterfaceC2705th interfaceC2705th) {
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final TreeSet c() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final Set b() {
        return Collections.EMPTY_SET;
    }

    @Override // com.android.tools.r8.internal.JR
    public final void a(JR jr) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.JR
    public final void a(JR jr, boolean z) {
        throw new Kk0();
    }

    public final void e() {
        if (!e && !this.d.isEmpty()) {
            x1f.a();
            return;
        }
        C1414ec0 c1414ec0 = this.c;
        if (c1414ec0 != null) {
            c1414ec0.d.remove(this);
            this.c = null;
        }
    }
}
