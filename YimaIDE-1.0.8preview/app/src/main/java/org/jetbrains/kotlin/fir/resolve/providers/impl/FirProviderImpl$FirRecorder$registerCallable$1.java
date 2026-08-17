package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirProviderImpl$FirRecorder$registerCallable$1 implements Function2 {
    public static final FirProviderImpl$FirRecorder$registerCallable$1 INSTANCE = new FirProviderImpl$FirRecorder$registerCallable$1();

    public final List invoke(List list, List list2) {
        list.getClass();
        list2.getClass();
        return CollectionsKt.plus(list, list2);
    }
}
