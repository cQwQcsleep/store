package com.android.tools.r8.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class WC extends AbstractC2895vu {
    public final transient AbstractC0551Hu f;
    public final Map g;
    public final Map h;
    public transient WC i;

    public WC(AbstractC0551Hu abstractC0551Hu, Map map, Map map2) {
        this.f = abstractC0551Hu;
        this.g = map;
        this.h = map2;
    }

    public static WC a(int i, Map.Entry[] entryArr) {
        HashMap map = new HashMap(AbstractC1739iN.a(i));
        HashMap map2 = new HashMap(AbstractC1739iN.a(i));
        for (int i2 = 0; i2 < i; i2++) {
            Map.Entry entry = entryArr[i2];
            Objects.requireNonNull(entry);
            Map.Entry entry2 = entry;
            T40 t40 = T40.i;
            C0784Qu c0784QuA = T40.a(entry2, entry2.getKey(), entry2.getValue());
            entryArr[i2] = c0784QuA;
            Object objPutIfAbsent = map.putIfAbsent(c0784QuA.b, c0784QuA.c);
            if (objPutIfAbsent != null) {
                throw AbstractC0706Nu.a("key", c0784QuA.b + "=" + objPutIfAbsent, entryArr[i2]);
            }
            Object objPutIfAbsent2 = map2.putIfAbsent(c0784QuA.c, c0784QuA.b);
            if (objPutIfAbsent2 != null) {
                throw AbstractC0706Nu.a("value", objPutIfAbsent2 + "=" + c0784QuA.c, entryArr[i2]);
            }
        }
        return new WC(AbstractC0551Hu.b(i, entryArr), map, map2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Object get(Object obj) {
        return this.g.get(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv i() {
        return new C0810Ru(this, this.f);
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
        WC wc = this.i;
        if (wc != null) {
            return wc;
        }
        WC wc2 = new WC(new VC(this), this.h, this.g);
        this.i = wc2;
        wc2.i = this;
        return wc2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f.size();
    }
}
