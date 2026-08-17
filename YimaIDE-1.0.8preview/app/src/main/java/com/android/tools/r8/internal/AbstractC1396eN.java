package com.android.tools.r8.internal;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1396eN extends AbstractMap {
    public final void a(Consumer consumer) {
        e().forEachRemaining(consumer);
    }

    public abstract Iterator e();

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return new C1311dN(this);
    }

    public final Spliterator i() {
        return Spliterators.spliterator(e(), size(), 65);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public abstract int size();
}
