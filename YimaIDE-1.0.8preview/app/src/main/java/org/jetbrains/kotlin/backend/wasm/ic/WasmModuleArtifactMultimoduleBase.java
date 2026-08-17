package org.jetbrains.kotlin.backend.wasm.ic;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.backend.js.ic.ModuleArtifact;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ic/WasmModuleArtifactMultimoduleBase;", "Lorg/jetbrains/kotlin/ir/backend/js/ic/ModuleArtifact;", "moduleName", "", "externalModuleName", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/lang/String;)V", "getModuleName", "()Ljava/lang/String;", "getExternalModuleName", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class WasmModuleArtifactMultimoduleBase extends ModuleArtifact {
    private final String externalModuleName;
    private final String moduleName;

    public WasmModuleArtifactMultimoduleBase(String str, String str2) {
        str.getClass();
        this.moduleName = str;
        this.externalModuleName = str2;
    }

    public final String getExternalModuleName() {
        return this.externalModuleName;
    }

    public final String getModuleName() {
        return this.moduleName;
    }
}
