package org.jetbrains.kotlin.backend.wasm.serialization;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b!\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u001b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u001c\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u001d\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u001f\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010 \u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010!\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\"\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010#\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010$\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010%\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/serialization/ImmediateTags;", "", "<init>", "()V", "BLOCK_TYPE_FUNCTION", "Lkotlin/UInt;", "I", "BLOCK_TYPE_VALUE", "CATCH", "CONST_F32", "CONST_F64", "CONST_I32", "CONST_I64", "CONST_STRING", "CONST_U8", "DATA_INDEX", "ELEMENT_INDEX", "FUNC_INDEX", "GC_TYPE", "VT_TYPE", "FUNC_TYPE", "GLOBAL_FIELD", "GLOBAL_VTABLE", "GLOBAL_CLASSITABLE", "GLOBAL_RTTI", "GLOBAL_STRING", "HEAP_TYPE", "LABEL_INDEX", "LABEL_INDEX_VECTOR", "LOCAL_INDEX", "MEM_ARG", "MEMORY_INDEX", "STRUCT_FIELD_INDEX", "SYMBOL_I32", "TABLE_INDEX", "TAG_INDEX", "VALUE_TYPE_VECTOR", "BLOCK_TYPE_NULL_VALUE", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ImmediateTags {
    public static final int BLOCK_TYPE_FUNCTION = 0;
    public static final int BLOCK_TYPE_NULL_VALUE = 129;
    public static final int BLOCK_TYPE_VALUE = 1;
    public static final int CATCH = 2;
    public static final int CONST_F32 = 3;
    public static final int CONST_F64 = 4;
    public static final int CONST_I32 = 5;
    public static final int CONST_I64 = 6;
    public static final int CONST_STRING = 7;
    public static final int CONST_U8 = 8;
    public static final int DATA_INDEX = 9;
    public static final int ELEMENT_INDEX = 10;
    public static final int FUNC_INDEX = 11;
    public static final int FUNC_TYPE = 14;
    public static final int GC_TYPE = 12;
    public static final int GLOBAL_CLASSITABLE = 17;
    public static final int GLOBAL_FIELD = 15;
    public static final int GLOBAL_RTTI = 18;
    public static final int GLOBAL_STRING = 19;
    public static final int GLOBAL_VTABLE = 16;
    public static final int HEAP_TYPE = 20;
    public static final ImmediateTags INSTANCE = new ImmediateTags();
    public static final int LABEL_INDEX = 21;
    public static final int LABEL_INDEX_VECTOR = 22;
    public static final int LOCAL_INDEX = 23;
    public static final int MEMORY_INDEX = 25;
    public static final int MEM_ARG = 24;
    public static final int STRUCT_FIELD_INDEX = 26;
    public static final int SYMBOL_I32 = 27;
    public static final int TABLE_INDEX = 28;
    public static final int TAG_INDEX = 29;
    public static final int VALUE_TYPE_VECTOR = 30;
    public static final int VT_TYPE = 13;

    private ImmediateTags() {
    }
}
