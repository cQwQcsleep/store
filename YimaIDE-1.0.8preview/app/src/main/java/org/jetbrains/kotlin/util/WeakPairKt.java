package org.jetbrains.kotlin.util;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a.\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0003H\u0086\u0002¢\u0006\u0002\u0010\u0004\u001a.\u0010\u0005\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0003H\u0086\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"component1", "K", "V", "Lorg/jetbrains/kotlin/util/WeakPair;", "(Lorg/jetbrains/kotlin/util/WeakPair;)Ljava/lang/Object;", "component2", "org.jetbrains.kotlin:compiler.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WeakPairKt {
    public static final <K, V> K component1(WeakPair<K, V> weakPair) {
        if (weakPair != null) {
            return weakPair.getFirst();
        }
        return null;
    }

    public static final <K, V> V component2(WeakPair<K, V> weakPair) {
        if (weakPair != null) {
            return weakPair.getSecond();
        }
        return null;
    }
}
