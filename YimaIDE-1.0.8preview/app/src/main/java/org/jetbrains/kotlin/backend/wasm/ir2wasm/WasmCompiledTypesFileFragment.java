package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.wasm.ir.WasmFunctionType;
import org.jetbrains.kotlin.wasm.ir.WasmStructDeclaration;
import org.jetbrains.kotlin.wasm.ir.WasmTypeDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001BI\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\u0004\b\n\u0010\u000bR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/WasmCompiledTypesFileFragment;", "", "definedGcTypes", "", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "Lorg/jetbrains/kotlin/wasm/ir/WasmTypeDeclaration;", "definedVTableGcTypes", "Lorg/jetbrains/kotlin/wasm/ir/WasmStructDeclaration;", "definedFunctionTypes", "Lorg/jetbrains/kotlin/wasm/ir/WasmFunctionType;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "getDefinedGcTypes", "()Ljava/util/Map;", "getDefinedVTableGcTypes", "getDefinedFunctionTypes", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmCompiledTypesFileFragment {
    private final Map<IdSignature, WasmFunctionType> definedFunctionTypes;
    private final Map<IdSignature, WasmTypeDeclaration> definedGcTypes;
    private final Map<IdSignature, WasmStructDeclaration> definedVTableGcTypes;

    public /* synthetic */ WasmCompiledTypesFileFragment(Map map, Map map2, Map map3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashMap() : map, (i & 2) != 0 ? new LinkedHashMap() : map2, (i & 4) != 0 ? new LinkedHashMap() : map3);
    }

    public final Map<IdSignature, WasmFunctionType> getDefinedFunctionTypes() {
        return this.definedFunctionTypes;
    }

    public final Map<IdSignature, WasmTypeDeclaration> getDefinedGcTypes() {
        return this.definedGcTypes;
    }

    public final Map<IdSignature, WasmStructDeclaration> getDefinedVTableGcTypes() {
        return this.definedVTableGcTypes;
    }

    public WasmCompiledTypesFileFragment(Map<IdSignature, WasmTypeDeclaration> map, Map<IdSignature, WasmStructDeclaration> map2, Map<IdSignature, WasmFunctionType> map3) {
        map.getClass();
        map2.getClass();
        map3.getClass();
        this.definedGcTypes = map;
        this.definedVTableGcTypes = map2;
        this.definedFunctionTypes = map3;
    }

    public WasmCompiledTypesFileFragment() {
        this(null, null, null, 7, null);
    }
}
