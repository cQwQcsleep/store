package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.NormalPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aR\u0010\n\u001a:\u0012\u0004\u0012\u00020\u0005\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002`\u00060\u0003j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002`\t\"\b\b\u0000\u0010\u0001*\u00020\u000b\"\b\b\u0001\u0010\u0002*\u00020\u000b\u001ab\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\u000fH\u0086\bø\u0001\u0000\u001aH\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\u0011H\u0086\bø\u0001\u0000\"F\u0010\u0007\u001a:\u0012\u0004\u0012\u00020\u0005\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0003j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b`\u00060\u0003j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000*.\u0010\u0000\u001a\u0004\b\u0000\u0010\u0001\u001a\u0004\b\u0001\u0010\u0002\"\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003*V\u0010\u0004\u001a\u0004\b\u0000\u0010\u0001\u001a\u0004\b\u0001\u0010\u0002\"\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002`\u00060\u00032*\u0012\u0004\u0012\u00020\u0005\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002`\u00060\u0003\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"ControlFlowInfo", "K", "V", "Lkotlinx/collections/immutable/PersistentMap;", "PathAwareControlFlowInfo", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "EMPTY_NORMAL_PATH_INFO", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", "emptyNormalPathInfo", Argument.Delimiters.none, "merge", "other", "block", "Lkotlin/Function2;", "transformValues", "Lkotlin/Function1;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PathAwareControlFlowGraphVisitorKt {
    private static final PersistentMap<EdgeLabel, PersistentMap> EMPTY_NORMAL_PATH_INFO = ExtensionsKt.persistentMapOf(new Pair[]{TuplesKt.to(NormalPath.INSTANCE, ExtensionsKt.persistentMapOf())});

    public static final <K, V> PersistentMap<EdgeLabel, PersistentMap<K, V>> emptyNormalPathInfo() {
        PersistentMap<EdgeLabel, PersistentMap<K, V>> persistentMap = EMPTY_NORMAL_PATH_INFO;
        persistentMap.getClass();
        return persistentMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> PersistentMap<K, V> merge(PersistentMap<K, ? extends V> persistentMap, PersistentMap<K, ? extends V> persistentMap2, Function2<? super V, ? super V, ? extends V> function2) {
        Object objInvoke;
        persistentMap.getClass();
        persistentMap2.getClass();
        function2.getClass();
        PersistentMap.Builder builder = persistentMap.builder();
        Iterator<T> it = persistentMap2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object key2 = entry.getKey();
            Object value = entry.getValue();
            Object obj = persistentMap.get(key2);
            if (obj != null && (objInvoke = function2.invoke(obj, value)) != null) {
                value = objInvoke;
            }
            builder.put(key, value);
        }
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> PersistentMap<K, V> transformValues(PersistentMap<K, ? extends V> persistentMap, Function1<? super V, ? extends V> function1) {
        persistentMap.getClass();
        function1.getClass();
        PersistentMap.Builder builder = persistentMap.builder();
        Iterator<T> it = persistentMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            builder.put(entry.getKey(), function1.invoke(entry.getValue()));
        }
        return builder.build();
    }
}
