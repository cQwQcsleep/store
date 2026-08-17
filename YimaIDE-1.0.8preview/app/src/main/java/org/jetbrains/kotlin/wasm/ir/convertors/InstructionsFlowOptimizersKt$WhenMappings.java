package org.jetbrains.kotlin.wasm.ir.convertors;

import kotlin.Metadata;
import org.jetbrains.kotlin.wasm.ir.WasmOp;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class InstructionsFlowOptimizersKt$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        int[] iArr = new int[WasmOp.values().length];
        try {
            iArr[WasmOp.REF_NULL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[WasmOp.I32_CONST.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[WasmOp.I64_CONST.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[WasmOp.F32_CONST.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[WasmOp.F64_CONST.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[WasmOp.LOCAL_GET.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr[WasmOp.GLOBAL_GET.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr[WasmOp.CALL_PURE.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            iArr[WasmOp.UNREACHABLE.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            iArr[WasmOp.RETURN.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            iArr[WasmOp.THROW.ordinal()] = 11;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            iArr[WasmOp.THROW_REF.ordinal()] = 12;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            iArr[WasmOp.RETHROW.ordinal()] = 13;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            iArr[WasmOp.BR.ordinal()] = 14;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            iArr[WasmOp.BR_TABLE.ordinal()] = 15;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            iArr[WasmOp.ELSE.ordinal()] = 16;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            iArr[WasmOp.CATCH.ordinal()] = 17;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            iArr[WasmOp.CATCH_ALL.ordinal()] = 18;
        } catch (NoSuchFieldError unused18) {
        }
        $EnumSwitchMapping$0 = iArr;
    }
}
