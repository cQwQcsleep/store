package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Iu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0577Iu extends AbstractC1105av implements InterfaceC2676tL {
    public C0577Iu(int i, AbstractC0706Nu abstractC0706Nu) {
        super(i, abstractC0706Nu);
    }

    @Override // com.android.tools.r8.internal.WP
    public final Collection a(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.WP
    public final Collection get(Object obj) {
        AbstractC0551Hu abstractC0551Hu = (AbstractC0551Hu) this.f.get(obj);
        if (abstractC0551Hu != null) {
            return abstractC0551Hu;
        }
        int i = AbstractC0551Hu.c;
        return P40.e;
    }

    @Override // com.android.tools.r8.internal.WP
    public final List get(Object obj) {
        AbstractC0551Hu abstractC0551Hu = (AbstractC0551Hu) this.f.get(obj);
        if (abstractC0551Hu != null) {
            return abstractC0551Hu;
        }
        int i = AbstractC0551Hu.c;
        return P40.e;
    }
}
