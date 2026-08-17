package org.jetbrains.kotlin.wasm.ir;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"SIMPLE_IDX_CACHE_SIZE", "", "WASM_OP_PSEUDO_OPCODE", "opcodesToOp", "", "Lorg/jetbrains/kotlin/wasm/ir/WasmOp;", "getOpcodesToOp", "()Ljava/util/Map;", "org.jetbrains.kotlin:wasm.ir"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class OperatorsKt {
    public static final int WASM_OP_PSEUDO_OPCODE = 65535;
    private static final Map<Integer, WasmOp> opcodesToOp;

    static {
        WasmOp[] wasmOpArrValues = WasmOp.values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(wasmOpArrValues.length), 16));
        for (WasmOp wasmOp : wasmOpArrValues) {
            linkedHashMap.put(Integer.valueOf(wasmOp.getOpcode()), wasmOp);
        }
        opcodesToOp = linkedHashMap;
    }

    public static final Map<Integer, WasmOp> getOpcodesToOp() {
        return opcodesToOp;
    }
}
