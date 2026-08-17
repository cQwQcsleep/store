package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.startup.StartupMethodBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Zd0 implements InterfaceC1963l1, StartupMethodBuilder {
    public static final /* synthetic */ boolean c = true;
    public final com.android.tools.r8.graph.B1 a;
    public C0322w2 b;

    public Zd0() {
        this.a = null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1963l1
    public final InterfaceC1963l1 a(InterfaceC1963l1 interfaceC1963l1) {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1963l1
    /* JADX INFO: renamed from: build */
    public final InterfaceC2049m1 c() {
        return new C1080ae0(this.b);
    }

    @Override // com.android.tools.r8.startup.StartupMethodBuilder
    public final StartupMethodBuilder setMethodReference(MethodReference methodReference) {
        if (c || this.a != null) {
            this.b = MO.a(methodReference, this.a);
            return this;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1963l1
    public final InterfaceC1963l1 a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1963l1
    public final InterfaceC1963l1 a(InterfaceC1963l1 interfaceC1963l1, Runnable runnable) {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1963l1
    public final InterfaceC1963l1 a(InterfaceC2049m1 interfaceC2049m1) {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1963l1
    public final InterfaceC1963l1 a(C0322w2 c0322w2) {
        this.b = c0322w2;
        return this;
    }

    public Zd0(com.android.tools.r8.graph.B1 b1) {
        this.a = b1;
    }
}
