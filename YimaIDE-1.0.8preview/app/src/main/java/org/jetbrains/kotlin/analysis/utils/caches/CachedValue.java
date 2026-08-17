package org.jetbrains.kotlin.analysis.utils.caches;

import com.intellij.openapi.util.ModificationTracker;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0005R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/analysis/utils/caches/CachedValue;", "T", "", "value", "timestamps", "", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/Object;Ljava/util/List;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTimestamps", "()Ljava/util/List;", "isUpToDate", "", "dependencies", "Lcom/intellij/openapi/util/ModificationTracker;", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class CachedValue<T> {
    private final List<Long> timestamps;
    private final T value;

    public CachedValue(T t, List<Long> list) {
        list.getClass();
        this.value = t;
        this.timestamps = list;
    }

    public final List<Long> getTimestamps() {
        return this.timestamps;
    }

    public final T getValue() {
        return this.value;
    }

    public final boolean isUpToDate(List<? extends ModificationTracker> dependencies) {
        dependencies.getClass();
        if (this.timestamps.size() != dependencies.size()) {
            k2d.a("Check failed.");
            return false;
        }
        int size = this.timestamps.size();
        for (int i = 0; i < size; i++) {
            if (dependencies.get(i).getModificationCount() != this.timestamps.get(i).longValue()) {
                return false;
            }
        }
        return true;
    }
}
