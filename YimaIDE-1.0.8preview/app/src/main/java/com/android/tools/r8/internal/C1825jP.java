package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jP, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1825jP extends AbstractC2167nP {
    public ArrayList b;
    public ArrayList c;
    public ArrayList d;
    public ArrayList e;
    public ArrayList f;
    public ArrayList g;

    public C1825jP() {
        super(589824, null);
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(String str, int i, String... strArr) {
        if (this.d == null) {
            this.d = new ArrayList(5);
        }
        ArrayList arrayList = this.d;
        AbstractC2287ol0.a(strArr);
        arrayList.add(new C1569gP());
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void b(String str, int i, String... strArr) {
        if (this.e == null) {
            this.e = new ArrayList(5);
        }
        ArrayList arrayList = this.e;
        AbstractC2287ol0.a(strArr);
        arrayList.add(new C1910kP());
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void c(String str) {
        if (this.f == null) {
            this.f = new ArrayList(5);
        }
        this.f.add(str);
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(String str) {
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void b(String str) {
        if (this.b == null) {
            this.b = new ArrayList(5);
        }
        this.b.add(str);
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(int i, String str, String str2) {
        if (this.c == null) {
            this.c = new ArrayList(5);
        }
        this.c.add(new C2082mP());
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a() {
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(String str, String... strArr) {
        if (this.g == null) {
            this.g = new ArrayList(5);
        }
        ArrayList arrayList = this.g;
        AbstractC2287ol0.a(strArr);
        arrayList.add(new C1996lP());
    }
}
