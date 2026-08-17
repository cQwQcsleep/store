package com.android.tools.r8.internal;

import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N40 extends AbstractC2895vu {
    public final /* synthetic */ O40 f;

    public N40(O40 o40) {
        this.f = o40;
    }

    @Override // com.android.tools.r8.internal.AbstractC2895vu, com.android.tools.r8.internal.R5
    public final R5 f() {
        return this.f;
    }

    @Override // java.util.Map
    public final void forEach(final BiConsumer biConsumer) {
        biConsumer.getClass();
        this.f.forEach(new BiConsumer() { // from class: zaa
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                biConsumer.accept(obj2, obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Object get(Object obj) {
        if (obj != null && this.f.g != null) {
            int iA = AbstractC1189bt.a(obj.hashCode());
            O40 o40 = this.f;
            for (C0784Qu c0784QuC = o40.g[iA & o40.i]; c0784QuC != null; c0784QuC = c0784QuC.c()) {
                if (obj.equals(c0784QuC.c)) {
                    return c0784QuC.b;
                }
            }
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv i() {
        return new M40(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv j() {
        return new C0862Tu(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final boolean m() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2895vu
    /* JADX INFO: renamed from: s */
    public final AbstractC2895vu f() {
        return this.f;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f.h.length;
    }
}
