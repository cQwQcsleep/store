package com.android.tools.r8.ir.regalloc;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.internal.C2543rl0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class a implements Comparable {
    public static final /* synthetic */ boolean g = true;
    public final C2543rl0 b;
    public final C0230j0 c;
    public final int d;
    public final int e;
    public final int f;

    public a(C2543rl0 c2543rl0, int i, int i2, int i3) {
        if (!g && !c2543rl0.y()) {
            x1f.a();
            throw null;
        }
        this.b = c2543rl0;
        this.c = c2543rl0.r();
        this.d = i;
        this.e = i2;
        this.f = i3;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(a aVar) {
        int i = this.e;
        int i2 = aVar.e;
        return i != i2 ? Integer.compare(i, i2) : Integer.compare(this.f, aVar.f);
    }

    public final String toString() {
        return this.c + " @ r" + this.d + ": " + new e(this.e, this.f);
    }
}
