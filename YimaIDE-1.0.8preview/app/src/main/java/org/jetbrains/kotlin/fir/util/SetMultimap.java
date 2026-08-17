package org.jetbrains.kotlin.fir.util;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010#\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022&\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0003B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0014J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004H\u0014¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/util/SetMultimap;", "K", "V", "Lorg/jetbrains/kotlin/fir/util/BaseMultimap;", "", "", "<init>", "()V", "createContainer", "createEmptyContainer", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SetMultimap<K, V> extends BaseMultimap<K, V, Set<? extends V>, Set<V>> {
    @Override // org.jetbrains.kotlin.fir.util.BaseMultimap
    public Set<V> createContainer() {
        return new LinkedHashSet();
    }

    @Override // org.jetbrains.kotlin.fir.util.BaseMultimap
    public Set<V> createEmptyContainer() {
        return SetsKt.emptySet();
    }
}
