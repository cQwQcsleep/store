package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.t1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0300t1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;
    public final C0245l1 c;
    public final C0245l1 d;
    public final C0245l1 e;
    public final C0245l1 f;
    public final C0245l1 g;
    public final C0245l1 h;
    public final C0245l1 i;
    public final C0245l1 j;
    public final C0245l1 k;
    public final C0245l1 l;
    public final C0245l1 m;
    public final C0245l1 n;
    public final C0245l1 o;
    public final C0245l1 p;
    public final C0245l1 q;

    public C0300t1(B1 b1) {
        this.a = b1.a(b1.N3, b1.Y1, "BOOTLOADER");
        this.b = b1.a(b1.N3, b1.Y1, "BRAND");
        this.c = b1.a(b1.N3, b1.Y1, "CPU_ABI");
        this.d = b1.a(b1.N3, b1.Y1, "CPU_ABI2");
        this.e = b1.a(b1.N3, b1.Y1, "DEVICE");
        this.f = b1.a(b1.N3, b1.Y1, "DISPLAY");
        this.g = b1.a(b1.N3, b1.Y1, "FINGERPRINT");
        this.h = b1.a(b1.N3, b1.Y1, "HARDWARE");
        this.i = b1.a(b1.N3, b1.Y1, "MANUFACTURER");
        this.j = b1.a(b1.N3, b1.Y1, "MODEL");
        this.k = b1.a(b1.N3, b1.Y1, "PRODUCT");
        this.l = b1.a(b1.N3, b1.Y1, "SERIAL");
        this.m = b1.a(b1.N3, b1.Z1, "SUPPORTED_32_BIT_ABIS");
        this.n = b1.a(b1.N3, b1.Z1, "SUPPORTED_64_BIT_ABIS");
        this.o = b1.a(b1.N3, b1.Z1, "SUPPORTED_ABIS");
        this.p = b1.a(b1.N3, b1.C1, "TIME");
        this.q = b1.a(b1.N3, b1.Y1, "TYPE");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
        consumer.accept(this.c);
        consumer.accept(this.d);
        consumer.accept(this.e);
        consumer.accept(this.f);
        consumer.accept(this.g);
        consumer.accept(this.h);
        consumer.accept(this.i);
        consumer.accept(this.j);
        consumer.accept(this.k);
        consumer.accept(this.l);
        consumer.accept(this.m);
        consumer.accept(this.n);
        consumer.accept(this.o);
        consumer.accept(this.p);
        consumer.accept(this.q);
    }
}
