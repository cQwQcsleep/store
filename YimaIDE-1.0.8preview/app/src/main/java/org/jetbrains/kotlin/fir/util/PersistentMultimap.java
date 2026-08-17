package org.jetbrains.kotlin.fir.util;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.ImmutableSet;
import kotlinx.collections.immutable.PersistentList;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B#\b\u0002\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bB\t\b\u0016¢\u0006\u0004\b\u0007\u0010\tJ'\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001¢\u0006\u0002\u0010\rJ'\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001¢\u0006\u0002\u0010\rJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00102\u0006\u0010\u000b\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0011R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/util/PersistentMultimap;", "K", "V", Argument.Delimiters.none, "map", "Lkotlinx/collections/immutable/PersistentMap;", "Lkotlinx/collections/immutable/PersistentList;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;)V", "()V", "put", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/util/PersistentMultimap;", "remove", "get", Argument.Delimiters.none, "(Ljava/lang/Object;)Ljava/util/List;", "keys", "Lkotlinx/collections/immutable/ImmutableSet;", "getKeys", "()Lkotlinx/collections/immutable/ImmutableSet;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PersistentMultimap<K, V> {
    private final PersistentMap<K, PersistentList<V>> map;

    public PersistentMultimap() {
        this(ExtensionsKt.persistentMapOf());
    }

    public final List<V> get(K key) {
        PersistentList persistentList = (PersistentList) this.map.get(key);
        return persistentList != null ? persistentList : CollectionsKt.emptyList();
    }

    public final ImmutableSet<K> getKeys() {
        return this.map.keySet();
    }

    public final PersistentMultimap<K, V> put(K key, V value) {
        PersistentList persistentListPersistentListOf = (PersistentList) this.map.get(key);
        if (persistentListPersistentListOf == null) {
            persistentListPersistentListOf = ExtensionsKt.persistentListOf();
        }
        PersistentList persistentListAdd = persistentListPersistentListOf.add(value);
        return persistentListAdd == persistentListPersistentListOf ? this : new PersistentMultimap<>(this.map.put(key, persistentListAdd));
    }

    public final PersistentMultimap<K, V> remove(K key, V value) {
        PersistentList persistentListRemove;
        PersistentList persistentList = (PersistentList) this.map.get(key);
        if (persistentList == null || persistentList == (persistentListRemove = persistentList.remove(value))) {
            return this;
        }
        boolean zIsEmpty = persistentListRemove.isEmpty();
        PersistentMap<K, PersistentList<V>> persistentMap = this.map;
        return new PersistentMultimap<>(zIsEmpty ? persistentMap.remove(key) : persistentMap.put(key, persistentListRemove));
    }

    private PersistentMultimap(PersistentMap<K, ? extends PersistentList<? extends V>> persistentMap) {
        this.map = persistentMap;
    }
}
