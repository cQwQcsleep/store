package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001BG\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ModuleReferencedDeclarations;", "", "functions", "", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "globalVTable", "globalClassITable", "rttiGlobal", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;)V", "getFunctions", "()Ljava/util/Set;", "getGlobalVTable", "getGlobalClassITable", "getRttiGlobal", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ModuleReferencedDeclarations {
    private final Set<IdSignature> functions;
    private final Set<IdSignature> globalClassITable;
    private final Set<IdSignature> globalVTable;
    private final Set<IdSignature> rttiGlobal;

    public /* synthetic */ ModuleReferencedDeclarations(Set set, Set set2, Set set3, Set set4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashSet() : set, (i & 2) != 0 ? new LinkedHashSet() : set2, (i & 4) != 0 ? new LinkedHashSet() : set3, (i & 8) != 0 ? new LinkedHashSet() : set4);
    }

    public final Set<IdSignature> getFunctions() {
        return this.functions;
    }

    public final Set<IdSignature> getGlobalClassITable() {
        return this.globalClassITable;
    }

    public final Set<IdSignature> getGlobalVTable() {
        return this.globalVTable;
    }

    public final Set<IdSignature> getRttiGlobal() {
        return this.rttiGlobal;
    }

    public ModuleReferencedDeclarations(Set<IdSignature> set, Set<IdSignature> set2, Set<IdSignature> set3, Set<IdSignature> set4) {
        set.getClass();
        set2.getClass();
        set3.getClass();
        set4.getClass();
        this.functions = set;
        this.globalVTable = set2;
        this.globalClassITable = set3;
        this.rttiGlobal = set4;
    }

    public ModuleReferencedDeclarations() {
        this(null, null, null, null, 15, null);
    }
}
