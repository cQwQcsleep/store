package com.android.tools.r8.internal;

import defpackage.dh6;
import defpackage.jfh;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3149yt {
    public final ArrayList a = new ArrayList();
    public final ArrayList b = new ArrayList();
    public boolean c = false;

    public final void a(com.android.tools.r8.graph.I2 i2) {
        this.b.add(i2);
    }

    public final void b() {
        this.c = true;
    }

    public final Tm0 c() {
        this.a.sort(new dh6());
        this.b.sort(new jfh());
        return new Tm0(AbstractC0551Hu.a(this.a), AbstractC0551Hu.a(this.b), this.c);
    }

    public final ArrayList a() {
        return this.a;
    }
}
