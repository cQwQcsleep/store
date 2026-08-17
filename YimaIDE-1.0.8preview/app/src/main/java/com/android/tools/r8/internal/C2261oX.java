package com.android.tools.r8.internal;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2261oX implements InterfaceC2347pX {
    public static final /* synthetic */ boolean e = true;
    public final int a;
    public C0322w2 b = null;
    public int c = -1;
    public int d = 1;

    public C2261oX(C2752uB c2752uB) {
        this.a = c2752uB.j instanceof ClassFileConsumer ? Integer.MAX_VALUE : 1;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2347pX
    public final C1405eW a(AbstractC2004lX abstractC2004lX) {
        boolean z = e;
        if (!z && abstractC2004lX.c == null) {
            x1f.a();
            return null;
        }
        if (abstractC2004lX.c.a(this.b)) {
            if (!z && this.c < 0) {
                x1f.a();
                return null;
            }
            if (abstractC2004lX.f() > this.c && abstractC2004lX.f() - this.c <= this.a) {
                this.d = ((abstractC2004lX.f() - this.c) - 1) + this.d;
            }
        }
        AbstractC2004lX.a aVarB = abstractC2004lX.b();
        int i = this.d;
        this.d = i + 1;
        AbstractC2004lX.a aVarA = aVarB.a(i);
        aVarA.c = null;
        AbstractC2004lX abstractC2004lXA = aVarA.c().a();
        this.c = abstractC2004lX.f();
        this.b = abstractC2004lX.c;
        return new C1405eW(abstractC2004lX, abstractC2004lXA);
    }
}
