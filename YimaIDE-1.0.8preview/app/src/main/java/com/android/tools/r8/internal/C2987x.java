package com.android.tools.r8.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2987x extends AbstractC2559s {
    public final AtomicReferenceFieldUpdater a;
    public final AtomicReferenceFieldUpdater b;
    public final AtomicReferenceFieldUpdater c;
    public final AtomicReferenceFieldUpdater d;
    public final AtomicReferenceFieldUpdater e;

    public C2987x(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.a = atomicReferenceFieldUpdater;
        this.b = atomicReferenceFieldUpdater2;
        this.c = atomicReferenceFieldUpdater3;
        this.d = atomicReferenceFieldUpdater4;
        this.e = atomicReferenceFieldUpdater5;
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final C2901w a(C c) {
        return (C2901w) this.d.getAndSet(c, C2901w.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final B b(C c) {
        return (B) this.c.getAndSet(c, B.c);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final void a(B b, B b2) {
        this.b.lazySet(b, b2);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final boolean a(C c, B b, B b2) {
        return this.c.compareAndSet(c, b, b2);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final void a(B b, Thread thread) {
        this.a.lazySet(b, thread);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final boolean a(C c, Object obj, Object obj2) {
        return this.e.compareAndSet(c, obj, obj2);
    }
}
