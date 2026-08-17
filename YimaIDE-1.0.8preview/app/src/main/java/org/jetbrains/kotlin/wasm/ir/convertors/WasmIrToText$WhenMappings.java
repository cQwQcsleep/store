package org.jetbrains.kotlin.wasm.ir.convertors;

import kotlin.Metadata;
import org.jetbrains.kotlin.wasm.ir.WasmOp;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class WasmIrToText$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        int[] iArr = new int[WasmOp.values().length];
        try {
            iArr[WasmOp.PSEUDO_COMMENT_PREVIOUS_INSTR.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[WasmOp.PSEUDO_COMMENT_GROUP_START.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[WasmOp.PSEUDO_COMMENT_GROUP_END.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        $EnumSwitchMapping$0 = iArr;
    }
}
