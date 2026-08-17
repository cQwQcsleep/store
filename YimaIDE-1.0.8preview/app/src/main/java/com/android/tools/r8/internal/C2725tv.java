package com.android.tools.r8.internal;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.IntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2725tv extends AbstractC2724tu {
    public final /* synthetic */ C2810uv d;

    public C2725tv(C2810uv c2810uv) {
        this.d = c2810uv;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final Map.Entry get(int i) {
        return new AbstractMap.SimpleImmutableEntry(this.d.e.f.a().get(i), this.d.e.g.get(i));
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return AbstractC1165be.a(((C2896vv) this.d.m()).size(), 1297, new IntFunction() { // from class: tdi
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.get(i);
            }
        }, (Comparator) null);
    }

    @Override // com.android.tools.r8.internal.AbstractC2724tu
    public final AbstractC3066xu k() {
        return this.d;
    }
}
