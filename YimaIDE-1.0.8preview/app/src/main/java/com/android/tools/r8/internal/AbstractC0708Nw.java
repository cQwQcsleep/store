package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0708Nw {
    public static final /* synthetic */ boolean c = true;
    public C2543rl0 a;
    public AbstractC2004lX b;

    public abstract AbstractC0708Nw a();

    public final AbstractC0708Nw a(AbstractC2004lX abstractC2004lX, C2752uB c2752uB) {
        if (!c && !b()) {
            x1f.a();
            return null;
        }
        if (c2752uB.Z0) {
            this.b = abstractC2004lX;
            return a();
        }
        this.b = AbstractC2004lX.r();
        return a();
    }

    public final AbstractC0708Nw b(AbstractC0890Uw abstractC0890Uw) {
        this.b = abstractC0890Uw.getPosition();
        return a();
    }

    public boolean b() {
        throw new Kk0();
    }

    public final AbstractC0890Uw a(AbstractC0890Uw abstractC0890Uw) {
        AbstractC2004lX abstractC2004lX = this.b;
        if (abstractC2004lX != null) {
            abstractC0890Uw.b(abstractC2004lX);
        }
        return abstractC0890Uw;
    }

    public final AbstractC0708Nw a(C2543rl0 c2543rl0) {
        this.a = c2543rl0;
        return (C0402Cb) this;
    }

    public final AbstractC0708Nw a(InterfaceC2714tl0 interfaceC2714tl0, AbstractC2624sj0 abstractC2624sj0) {
        this.a = interfaceC2714tl0.a(abstractC2624sj0, null);
        return a();
    }

    public final AbstractC0708Nw a(InterfaceC2714tl0 interfaceC2714tl0, AbstractC2624sj0 abstractC2624sj0, C0230j0 c0230j0) {
        this.a = interfaceC2714tl0.a(abstractC2624sj0, c0230j0);
        return a();
    }
}
