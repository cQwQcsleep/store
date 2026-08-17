package org.jetbrains.kotlin.wasm.ir;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmArrayDeclaration;", "Lorg/jetbrains/kotlin/wasm/ir/WasmTypeDeclaration;", "name", "", "field", "Lorg/jetbrains/kotlin/wasm/ir/WasmStructFieldDeclaration;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Lorg/jetbrains/kotlin/wasm/ir/WasmStructFieldDeclaration;)V", "getField", "()Lorg/jetbrains/kotlin/wasm/ir/WasmStructFieldDeclaration;", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmArrayDeclaration extends WasmTypeDeclaration {
    private final WasmStructFieldDeclaration field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WasmArrayDeclaration(String str, WasmStructFieldDeclaration wasmStructFieldDeclaration) {
        super(str, (DefaultConstructorMarker) null);
        str.getClass();
        wasmStructFieldDeclaration.getClass();
        this.field = wasmStructFieldDeclaration;
    }

    public final WasmStructFieldDeclaration getField() {
        return this.field;
    }
}
