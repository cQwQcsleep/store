package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0002¨\u0006\u0004"}, d2 = {"emptyPersistentHashMapBuilder", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "K", "V", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FlowKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> PersistentMap.Builder<K, V> emptyPersistentHashMapBuilder() {
        return ExtensionsKt.persistentHashMapOf().builder();
    }
}
