package org.jetbrains.kotlin.fir.util;

import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u001e\n\u0000\u001a\u001e\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\u001a\u001e\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\u001aC\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0002\b\u00030\b2\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u000b0\nH\u0086\u0002¨\u0006\f"}, d2 = {"setMultimapOf", "Lorg/jetbrains/kotlin/fir/util/SetMultimap;", "K", "V", "listMultimapOf", "Lorg/jetbrains/kotlin/fir/util/ListMultimap;", "plusAssign", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/util/MutableMultimap;", "map", Argument.Delimiters.none, Argument.Delimiters.none, "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MultimapKt {
    public static final <K, V> ListMultimap<K, V> listMultimapOf() {
        return new ListMultimap<>();
    }

    public static final <K, V> void plusAssign(MutableMultimap<K, V, ?> mutableMultimap, Map<K, ? extends Collection<? extends V>> map) {
        mutableMultimap.getClass();
        map.getClass();
        for (Map.Entry<K, ? extends Collection<? extends V>> entry : map.entrySet()) {
            mutableMultimap.putAll(entry.getKey(), entry.getValue());
        }
    }

    public static final <K, V> SetMultimap<K, V> setMultimapOf() {
        return new SetMultimap<>();
    }
}
