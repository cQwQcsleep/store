package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ModuleReferencedTypes;", "", "gcTypes", "", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "functionTypes", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Set;Ljava/util/Set;)V", "getGcTypes", "()Ljava/util/Set;", "getFunctionTypes", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ModuleReferencedTypes {
    private final Set<IdSignature> functionTypes;
    private final Set<IdSignature> gcTypes;

    public /* synthetic */ ModuleReferencedTypes(Set set, Set set2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashSet() : set, (i & 2) != 0 ? new LinkedHashSet() : set2);
    }

    public final Set<IdSignature> getFunctionTypes() {
        return this.functionTypes;
    }

    public final Set<IdSignature> getGcTypes() {
        return this.gcTypes;
    }

    public ModuleReferencedTypes(Set<IdSignature> set, Set<IdSignature> set2) {
        set.getClass();
        set2.getClass();
        this.gcTypes = set;
        this.functionTypes = set2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ModuleReferencedTypes() {
        Set set = null;
        this(set, set, 3, set);
    }
}
