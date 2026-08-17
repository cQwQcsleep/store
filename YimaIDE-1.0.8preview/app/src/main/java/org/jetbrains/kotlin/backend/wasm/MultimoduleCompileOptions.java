package org.jetbrains.kotlin.backend.wasm;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/MultimoduleCompileOptions;", "", "stdlibModuleNameForImport", "", "dependencyModules", "", "Lorg/jetbrains/kotlin/backend/wasm/WasmModuleDependencyImport;", "initializeUnit", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/util/Set;Z)V", "getStdlibModuleNameForImport", "()Ljava/lang/String;", "getDependencyModules", "()Ljava/util/Set;", "getInitializeUnit", "()Z", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class MultimoduleCompileOptions {
    private final Set<WasmModuleDependencyImport> dependencyModules;
    private final boolean initializeUnit;
    private final String stdlibModuleNameForImport;

    public MultimoduleCompileOptions(String str, Set<WasmModuleDependencyImport> set, boolean z) {
        set.getClass();
        this.stdlibModuleNameForImport = str;
        this.dependencyModules = set;
        this.initializeUnit = z;
    }

    public final Set<WasmModuleDependencyImport> getDependencyModules() {
        return this.dependencyModules;
    }

    public final boolean getInitializeUnit() {
        return this.initializeUnit;
    }

    public final String getStdlibModuleNameForImport() {
        return this.stdlibModuleNameForImport;
    }
}
