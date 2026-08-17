package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AF extends VF {
    public final /* synthetic */ BF j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AF(BF bf, AbstractC3114yW abstractC3114yW) {
        super(abstractC3114yW);
        this.j = bf;
    }

    @Override // com.android.tools.r8.internal.VF
    public final C1475fG b() {
        C2516rW c2516rW = this.j.c;
        c2516rW.getClass();
        throw new C3096yE(c2516rW, "bindings not supported");
    }
}
