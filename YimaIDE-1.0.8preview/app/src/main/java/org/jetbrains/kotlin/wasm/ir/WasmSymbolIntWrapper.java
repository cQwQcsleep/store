package org.jetbrains.kotlin.wasm.ir;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmSymbolIntWrapper;", "Lorg/jetbrains/kotlin/wasm/ir/WasmSymbolReadOnly;", "", "symbol", "Lorg/jetbrains/kotlin/wasm/ir/WasmSymbol;", "Lorg/jetbrains/kotlin/wasm/ir/WasmNamedModuleField;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/wasm/ir/WasmSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/wasm/ir/WasmSymbol;", "owner", "getOwner", "()Ljava/lang/Integer;", "toString", "", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmSymbolIntWrapper implements WasmSymbolReadOnly<Integer> {
    private final WasmSymbol<WasmNamedModuleField> symbol;

    public WasmSymbolIntWrapper(WasmSymbol<? extends WasmNamedModuleField> wasmSymbol) {
        wasmSymbol.getClass();
        this.symbol = wasmSymbol;
    }

    /* JADX INFO: renamed from: getOwner, reason: merged with bridge method [inline-methods] */
    public Integer m2191getOwner() {
        Integer id = this.symbol.getOwner().getId();
        id.getClass();
        return id;
    }

    public final WasmSymbol<WasmNamedModuleField> getSymbol() {
        return this.symbol;
    }

    public String toString() {
        return String.valueOf(m2191getOwner().intValue());
    }
}
