package org.jetbrains.kotlin.backend.wasm;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.WasmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"wasmCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "", "getWasmCallableId", "(Ljava/lang/String;)Lorg/jetbrains/kotlin/name/CallableId;", "org.jetbrains.kotlin:backend.wasm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class BackendWasmSymbolsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final CallableId getWasmCallableId(String str) {
        FqName base_wasm_internal_package = WasmStandardClassIds.INSTANCE.getBASE_WASM_INTERNAL_PACKAGE();
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return new CallableId(base_wasm_internal_package, nameIdentifier);
    }
}
