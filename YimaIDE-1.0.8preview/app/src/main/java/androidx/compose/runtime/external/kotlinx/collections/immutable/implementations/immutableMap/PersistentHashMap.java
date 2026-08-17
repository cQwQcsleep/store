package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableCollection;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMap;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0011\u0018\u0000 -*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004:\u0001-B#\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00180\u0010H\u0002J\u001a\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00180\u001bH\u0001J\u0015\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u0004\u0018\u00018\u00012\u0006\u0010\u001e\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010!J)\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00028\u00002\u0006\u0010#\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010$J!\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010&J)\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00028\u00002\u0006\u0010#\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010$J*\u0010'\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u0014\u0010(\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010)H\u0016J\u0014\u0010*\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016J\u0014\u0010+\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010,H\u0016R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R&\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00180\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0012¨\u0006."}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;", "K", "V", "Lkotlin/collections/AbstractMap;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentMap;", "node", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "size", "", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;I)V", "getNode$runtime", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "getSize", "()I", "keys", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/ImmutableSet;", "getKeys", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/ImmutableSet;", "values", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/ImmutableCollection;", "getValues", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/ImmutableCollection;", "entries", "", "getEntries", "createEntries", "", "containsKey", "", "key", "(Ljava/lang/Object;)Z", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;", "remove", "(Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;", "putAll", "m", "", "clear", "builder", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public class PersistentHashMap<K, V> extends AbstractMap<K, V> implements PersistentMap<K, V> {
    private final TrieNode<K, V> node;
    private final int size;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final PersistentHashMap EMPTY = new PersistentHashMap(TrieNode.INSTANCE.getEMPTY$runtime(), 0);

    public PersistentHashMap(TrieNode<K, V> trieNode, int i) {
        this.node = trieNode;
        this.size = i;
    }

    private final ImmutableSet<Map.Entry<K, V>> createEntries() {
        return new PersistentHashMapEntries(this);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap
    /* JADX INFO: renamed from: builder */
    public PersistentHashMapBuilder<K, V> builder2() {
        return new PersistentHashMapBuilder<>(this);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap, java.util.Map
    public PersistentMap<K, V> clear() {
        return INSTANCE.emptyOf$runtime();
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return this.node.containsKey(key != null ? key.hashCode() : 0, key, 0);
    }

    @Override // java.util.Map
    public final /* bridge */ ImmutableSet<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public V get(Object key) {
        return this.node.get(key != null ? key.hashCode() : 0, key, 0);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> getEntries() {
        return createEntries();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableMap
    /* JADX INFO: renamed from: getKeys, reason: merged with bridge method [inline-methods] */
    public ImmutableSet<K> m2505getKeys() {
        return new PersistentHashMapKeys(this);
    }

    public final TrieNode<K, V> getNode$runtime() {
        return this.node;
    }

    public int getSize() {
        return this.size;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableMap
    /* JADX INFO: renamed from: getValues, reason: merged with bridge method [inline-methods] */
    public ImmutableCollection<V> m2506getValues() {
        return new PersistentHashMapValues(this);
    }

    @Override // java.util.Map
    public final /* bridge */ ImmutableSet<K> keySet() {
        return m2509getKeys();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap, java.util.Map
    public PersistentHashMap<K, V> put(K key, V value) {
        TrieNode.ModificationResult<K, V> modificationResultPut = this.node.put(key != null ? key.hashCode() : 0, key, value, 0);
        return modificationResultPut == null ? this : new PersistentHashMap<>(modificationResultPut.getNode(), size() + modificationResultPut.getSizeDelta());
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap, java.util.Map
    public PersistentMap<K, V> putAll(Map<? extends K, ? extends V> m) {
        PersistentMap.Builder<K, V> builder = builder2();
        builder.putAll(m);
        return builder.build2();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap, java.util.Map
    public PersistentHashMap<K, V> remove(K key) {
        TrieNode<K, V> trieNodeRemove = this.node.remove(key != null ? key.hashCode() : 0, key, 0);
        if (this.node == trieNodeRemove) {
            return this;
        }
        return trieNodeRemove == null ? INSTANCE.emptyOf$runtime() : new PersistentHashMap<>(trieNodeRemove, size() - 1);
    }

    @Override // java.util.Map
    public final /* bridge */ ImmutableCollection<V> values() {
        return m2510getValues();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u0005\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\tH\u0000¢\u0006\u0002\b\nR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap$Companion;", "", "<init>", "()V", "EMPTY", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;", "", "emptyOf", "K", "V", "emptyOf$runtime", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <K, V> PersistentHashMap<K, V> emptyOf$runtime() {
            PersistentHashMap<K, V> persistentHashMap = PersistentHashMap.EMPTY;
            persistentHashMap.getClass();
            return persistentHashMap;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: getEntries, reason: collision with other method in class */
    public final Set<Map.Entry<K, V>> m2504getEntries() {
        return createEntries();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap, java.util.Map
    public PersistentHashMap<K, V> remove(K key, V value) {
        TrieNode<K, V> trieNodeRemove = this.node.remove(key != null ? key.hashCode() : 0, key, value, 0);
        if (this.node == trieNodeRemove) {
            return this;
        }
        if (trieNodeRemove == null) {
            return INSTANCE.emptyOf$runtime();
        }
        return new PersistentHashMap<>(trieNodeRemove, size() - 1);
    }
}
