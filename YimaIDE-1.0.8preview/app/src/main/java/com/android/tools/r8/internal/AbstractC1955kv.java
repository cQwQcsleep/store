package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1955kv extends AbstractC2554rv {
    public transient AbstractC0551Hu d;

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final AbstractC0551Hu a() {
        AbstractC0551Hu abstractC0551Hu = this.d;
        if (abstractC0551Hu != null) {
            return abstractC0551Hu;
        }
        AbstractC0551Hu abstractC0551HuL = l();
        this.d = abstractC0551HuL;
        return abstractC0551HuL;
    }

    public AbstractC0551Hu l() {
        return new K40(this, toArray(AbstractC3066xu.b));
    }
}
