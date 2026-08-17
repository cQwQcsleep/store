package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.wasm.ir.WasmFunction;
import org.jetbrains.kotlin.wasm.ir.WasmGlobal;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0004\b\f\u0010\rR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001f\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/WasmCompiledDeclarationsFileFragment;", "", "definedFunctions", "", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "Lorg/jetbrains/kotlin/wasm/ir/WasmFunction;", "definedGlobalFields", "Lorg/jetbrains/kotlin/wasm/ir/WasmGlobal;", "definedGlobalVTables", "definedGlobalClassITables", "definedRttiGlobal", "definedRttiSuperType", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "getDefinedFunctions", "()Ljava/util/Map;", "getDefinedGlobalFields", "getDefinedGlobalVTables", "getDefinedGlobalClassITables", "getDefinedRttiGlobal", "getDefinedRttiSuperType", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmCompiledDeclarationsFileFragment {
    private final Map<IdSignature, WasmFunction> definedFunctions;
    private final Map<IdSignature, WasmGlobal> definedGlobalClassITables;
    private final Map<IdSignature, WasmGlobal> definedGlobalFields;
    private final Map<IdSignature, WasmGlobal> definedGlobalVTables;
    private final Map<IdSignature, WasmGlobal> definedRttiGlobal;
    private final Map<IdSignature, IdSignature> definedRttiSuperType;

    public /* synthetic */ WasmCompiledDeclarationsFileFragment(Map map, Map map2, Map map3, Map map4, Map map5, Map map6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashMap() : map, (i & 2) != 0 ? new LinkedHashMap() : map2, (i & 4) != 0 ? new LinkedHashMap() : map3, (i & 8) != 0 ? new LinkedHashMap() : map4, (i & 16) != 0 ? new LinkedHashMap() : map5, (i & 32) != 0 ? new LinkedHashMap() : map6);
    }

    public final Map<IdSignature, WasmFunction> getDefinedFunctions() {
        return this.definedFunctions;
    }

    public final Map<IdSignature, WasmGlobal> getDefinedGlobalClassITables() {
        return this.definedGlobalClassITables;
    }

    public final Map<IdSignature, WasmGlobal> getDefinedGlobalFields() {
        return this.definedGlobalFields;
    }

    public final Map<IdSignature, WasmGlobal> getDefinedGlobalVTables() {
        return this.definedGlobalVTables;
    }

    public final Map<IdSignature, WasmGlobal> getDefinedRttiGlobal() {
        return this.definedRttiGlobal;
    }

    public final Map<IdSignature, IdSignature> getDefinedRttiSuperType() {
        return this.definedRttiSuperType;
    }

    public WasmCompiledDeclarationsFileFragment(Map<IdSignature, WasmFunction> map, Map<IdSignature, WasmGlobal> map2, Map<IdSignature, WasmGlobal> map3, Map<IdSignature, WasmGlobal> map4, Map<IdSignature, WasmGlobal> map5, Map<IdSignature, IdSignature> map6) {
        map.getClass();
        map2.getClass();
        map3.getClass();
        map4.getClass();
        map5.getClass();
        map6.getClass();
        this.definedFunctions = map;
        this.definedGlobalFields = map2;
        this.definedGlobalVTables = map3;
        this.definedGlobalClassITables = map4;
        this.definedRttiGlobal = map5;
        this.definedRttiSuperType = map6;
    }

    public WasmCompiledDeclarationsFileFragment() {
        this(null, null, null, null, null, null, 63, null);
    }
}
