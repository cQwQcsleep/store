package org.jetbrains.kotlin.analysis.utils.caches;

import com.intellij.openapi.util.ModificationTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B%\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ$\u0010\r\u001a\u00028\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0086\u0002¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/analysis/utils/caches/StrongRefModificationTrackerBasedCache;", "T", "", "dependencies", "", "Lcom/intellij/openapi/util/ModificationTracker;", "compute", "Lkotlin/Function0;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "cached", "Ljava/util/concurrent/atomic/AtomicReference;", "Lorg/jetbrains/kotlin/analysis/utils/caches/CachedValue;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "createNewCachedValue", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class StrongRefModificationTrackerBasedCache<T> {
    private final AtomicReference<CachedValue<T>> cached;
    private final Function0<T> compute;
    private final List<ModificationTracker> dependencies;

    /* JADX WARN: Multi-variable type inference failed */
    public StrongRefModificationTrackerBasedCache(List<? extends ModificationTracker> list, Function0<? extends T> function0) {
        list.getClass();
        function0.getClass();
        this.dependencies = list;
        this.compute = function0;
        this.cached = new AtomicReference<>(null);
    }

    public static CachedValue a(StrongRefModificationTrackerBasedCache strongRefModificationTrackerBasedCache, CachedValue cachedValue) {
        return (cachedValue != null && cachedValue.isUpToDate(strongRefModificationTrackerBasedCache.dependencies)) ? cachedValue : strongRefModificationTrackerBasedCache.createNewCachedValue();
    }

    private final CachedValue<T> createNewCachedValue() {
        Object objInvoke = this.compute.invoke();
        List<ModificationTracker> list = this.dependencies;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((ModificationTracker) it.next()).getModificationCount()));
        }
        return new CachedValue<>(objInvoke, arrayList);
    }

    public final T getValue(Object thisRef, KProperty<?> property) {
        property.getClass();
        CachedValue<T> cachedValueUpdateAndGet = this.cached.updateAndGet(new UnaryOperator() { // from class: org.jetbrains.kotlin.analysis.utils.caches.b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return StrongRefModificationTrackerBasedCache.a(this.b, (CachedValue) obj);
            }
        });
        cachedValueUpdateAndGet.getClass();
        return cachedValueUpdateAndGet.getValue();
    }
}
