package com.android.tools.r8.dex;

import com.android.tools.r8.internal.CJ;
import com.android.tools.r8.origin.Origin;

/* JADX INFO: renamed from: com.android.tools.r8.dex.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0150m {
    public static final /* synthetic */ boolean c = true;
    public final Origin a;
    public final C0155s b;

    public AbstractC0150m(Origin origin, byte[] bArr) {
        if (!c && origin == null) {
            x1f.a();
            throw null;
        }
        this.a = origin;
        this.b = C0155s.b(bArr);
    }

    public final int a(int i) {
        int iB = this.b.b(i);
        if (c || iB >= 0) {
            return iB;
        }
        x1f.a();
        return 0;
    }

    public final byte b() {
        return this.b.c().get();
    }

    public final int c() {
        return this.b.f();
    }

    public final int d() {
        int iF = this.b.f();
        if (c || iF >= 0) {
            return iF;
        }
        x1f.a();
        return 0;
    }

    public final int e() {
        return CJ.b(this);
    }

    public final int f() {
        return CJ.b(this) - 1;
    }

    public final int g() {
        int iG = this.b.g() & 65535;
        if (c || iG >= 0) {
            return iG;
        }
        x1f.a();
        return 0;
    }

    public final int h() {
        return this.b.c().position();
    }

    public void b(int i) {
        this.b.c(i);
    }

    public final int a() {
        return this.b.d();
    }
}
