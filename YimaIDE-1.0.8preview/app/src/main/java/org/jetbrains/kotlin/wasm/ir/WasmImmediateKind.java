package org.jetbrains.kotlin.wasm.ir;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u001c\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmImmediateKind;", "", "<init>", "(Ljava/lang/String;I)V", "CONST_U8", "CONST_I32", "CONST_I64", "CONST_F32", "CONST_F64", "MEM_ARG", "BLOCK_TYPE", "LOCAL_DEFS", "FUNC_IDX", "LOCAL_IDX", "GLOBAL_IDX", "TYPE_IDX", "VAL_TYPE_VECTOR", "MEMORY_IDX", "DATA_IDX", "TABLE_IDX", "LABEL_IDX", "TAG_IDX", "LABEL_IDX_VECTOR", "ELEM_IDX", "STRUCT_TYPE_IDX", "STRUCT_FIELD_IDX", "TYPE_IMM", "HEAP_TYPE", "CATCH_VECTOR", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum WasmImmediateKind {
    CONST_U8,
    CONST_I32,
    CONST_I64,
    CONST_F32,
    CONST_F64,
    MEM_ARG,
    BLOCK_TYPE,
    LOCAL_DEFS,
    FUNC_IDX,
    LOCAL_IDX,
    GLOBAL_IDX,
    TYPE_IDX,
    VAL_TYPE_VECTOR,
    MEMORY_IDX,
    DATA_IDX,
    TABLE_IDX,
    LABEL_IDX,
    TAG_IDX,
    LABEL_IDX_VECTOR,
    ELEM_IDX,
    STRUCT_TYPE_IDX,
    STRUCT_FIELD_IDX,
    TYPE_IMM,
    HEAP_TYPE,
    CATCH_VECTOR;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<WasmImmediateKind> getEntries() {
        return $ENTRIES;
    }
}
