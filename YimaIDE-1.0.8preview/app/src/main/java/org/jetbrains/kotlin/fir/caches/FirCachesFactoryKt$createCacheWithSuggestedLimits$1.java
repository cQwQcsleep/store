package org.jetbrains.kotlin.fir.caches;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 176)
public final class FirCachesFactoryKt$createCacheWithSuggestedLimits$1 implements Function2 {
    final /* synthetic */ Function1<K, V> $createValue;

    /* JADX WARN: Multi-variable type inference failed */
    public FirCachesFactoryKt$createCacheWithSuggestedLimits$1(Function1<? super K, ? extends V> function1) {
        this.$createValue = function1;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [V, java.lang.Object] */
    public final V invoke(K k, Void r2) {
        k.getClass();
        return this.$createValue.invoke(k);
    }
}
