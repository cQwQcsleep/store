package org.jetbrains.kotlin.wasm.ir;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmStructFieldDeclaration;", "", "name", "", "type", "Lorg/jetbrains/kotlin/wasm/ir/WasmType;", "isMutable", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Lorg/jetbrains/kotlin/wasm/ir/WasmType;Z)V", "getName", "()Ljava/lang/String;", "getType", "()Lorg/jetbrains/kotlin/wasm/ir/WasmType;", "()Z", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmStructFieldDeclaration {
    private final boolean isMutable;
    private final String name;
    private final WasmType type;

    public WasmStructFieldDeclaration(String str, WasmType wasmType, boolean z) {
        str.getClass();
        wasmType.getClass();
        this.name = str;
        this.type = wasmType;
        this.isMutable = z;
    }

    public final String getName() {
        return this.name;
    }

    public final WasmType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: isMutable, reason: from getter */
    public final boolean getIsMutable() {
        return this.isMutable;
    }
}
