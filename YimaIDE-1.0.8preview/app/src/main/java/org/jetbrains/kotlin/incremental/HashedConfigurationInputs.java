package org.jetbrains.kotlin.incremental;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.build.report.metrics.BuildAttribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bR!\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/HashedConfigurationInputs;", "", "inputs", "", "Lorg/jetbrains/kotlin/build/report/metrics/BuildAttribute;", "Lorg/jetbrains/kotlin/incremental/RebuildReason;", "", "<init>", "(Ljava/util/Map;)V", "getInputs", "()Ljava/util/Map;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class HashedConfigurationInputs {
    private final Map<BuildAttribute, byte[]> inputs;

    public HashedConfigurationInputs(Map<BuildAttribute, byte[]> map) {
        map.getClass();
        this.inputs = map;
    }

    public final Map<BuildAttribute, byte[]> getInputs() {
        return this.inputs;
    }
}
