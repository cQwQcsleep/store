package org.jetbrains.kotlin.analysis.utils.caches;

import com.intellij.openapi.util.ModificationTracker;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"strongCachedValue", "Lorg/jetbrains/kotlin/analysis/utils/caches/StrongRefModificationTrackerBasedCache;", "T", "dependencies", "", "Lcom/intellij/openapi/util/ModificationTracker;", "compute", "Lkotlin/Function0;", "([Lcom/intellij/openapi/util/ModificationTracker;Lkotlin/jvm/functions/Function0;)Lorg/jetbrains/kotlin/analysis/utils/caches/StrongRefModificationTrackerBasedCache;", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class StrongCachedValueKt {
    public static final <T> StrongRefModificationTrackerBasedCache<T> strongCachedValue(ModificationTracker[] modificationTrackerArr, Function0<? extends T> function0) {
        modificationTrackerArr.getClass();
        function0.getClass();
        return new StrongRefModificationTrackerBasedCache<>(ArraysKt.toList(modificationTrackerArr), function0);
    }
}
