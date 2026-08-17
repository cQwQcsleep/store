package org.jetbrains.kotlin.backend.wasm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/WasmModuleDependencyImport;", "", "name", "", "fileName", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getFileName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class WasmModuleDependencyImport {
    private final String fileName;
    private final String name;

    public WasmModuleDependencyImport(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.name = str;
        this.fileName = str2;
    }

    public static /* synthetic */ WasmModuleDependencyImport copy$default(WasmModuleDependencyImport wasmModuleDependencyImport, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = wasmModuleDependencyImport.name;
        }
        if ((i & 2) != 0) {
            str2 = wasmModuleDependencyImport.fileName;
        }
        return wasmModuleDependencyImport.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    public final WasmModuleDependencyImport copy(String name, String fileName) {
        name.getClass();
        fileName.getClass();
        return new WasmModuleDependencyImport(name, fileName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WasmModuleDependencyImport)) {
            return false;
        }
        WasmModuleDependencyImport wasmModuleDependencyImport = (WasmModuleDependencyImport) other;
        return Intrinsics.areEqual(this.name, wasmModuleDependencyImport.name) && Intrinsics.areEqual(this.fileName, wasmModuleDependencyImport.fileName);
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.fileName.hashCode();
    }

    public String toString() {
        return "WasmModuleDependencyImport(name=" + this.name + ", fileName=" + this.fileName + Util.C_PARAM_END;
    }
}
