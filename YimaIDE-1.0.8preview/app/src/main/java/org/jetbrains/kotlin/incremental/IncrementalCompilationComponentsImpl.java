package org.jetbrains.kotlin.incremental;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCache;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCompilationComponents;
import org.jetbrains.kotlin.modules.TargetId;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0004H\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/incremental/IncrementalCompilationComponentsImpl;", "Lorg/jetbrains/kotlin/load/kotlin/incremental/components/IncrementalCompilationComponents;", "caches", "", "Lorg/jetbrains/kotlin/modules/TargetId;", "Lorg/jetbrains/kotlin/load/kotlin/incremental/components/IncrementalCache;", "<init>", "(Ljava/util/Map;)V", "getIncrementalCache", "target", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IncrementalCompilationComponentsImpl implements IncrementalCompilationComponents {
    private final Map<TargetId, IncrementalCache> caches;

    /* JADX WARN: Multi-variable type inference failed */
    public IncrementalCompilationComponentsImpl(Map<TargetId, ? extends IncrementalCache> map) {
        map.getClass();
        this.caches = map;
    }

    public IncrementalCache getIncrementalCache(TargetId target) throws Exception {
        target.getClass();
        IncrementalCache incrementalCache = this.caches.get(target);
        if (incrementalCache != null) {
            return incrementalCache;
        }
        throw new Exception("Incremental cache for target " + target.getName() + " not found");
    }
}
