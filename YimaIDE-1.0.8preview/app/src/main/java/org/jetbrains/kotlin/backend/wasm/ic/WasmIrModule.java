package org.jetbrains.kotlin.backend.wasm.ic;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.backend.wasm.ir2wasm.WasmCompiledCodeFileFragment;
import org.jetbrains.kotlin.ir.backend.js.ic.IrICModule;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ic/WasmIrModule;", "Lorg/jetbrains/kotlin/ir/backend/js/ic/IrICModule;", "moduleName", "", "fragments", "", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/WasmCompiledCodeFileFragment;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getModuleName", "()Ljava/lang/String;", "getFragments", "()Ljava/util/List;", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmIrModule extends IrICModule {
    private final List<WasmCompiledCodeFileFragment> fragments;
    private final String moduleName;

    public WasmIrModule(String str, List<WasmCompiledCodeFileFragment> list) {
        str.getClass();
        list.getClass();
        this.moduleName = str;
        this.fragments = list;
    }

    @Override // org.jetbrains.kotlin.ir.backend.js.ic.IrICModule
    public List<WasmCompiledCodeFileFragment> getFragments() {
        return this.fragments;
    }

    @Override // org.jetbrains.kotlin.ir.backend.js.ic.IrICModule
    public String getModuleName() {
        return this.moduleName;
    }
}
