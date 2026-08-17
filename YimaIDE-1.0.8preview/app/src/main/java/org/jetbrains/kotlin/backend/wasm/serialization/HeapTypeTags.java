package org.jetbrains.kotlin.backend.wasm.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/serialization/HeapTypeTags;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "ANY", "Lkotlin/UInt;", "I", "EQ", "EXTERN", "FUNC", "NO_EXTERN", "NONE", "NO_FUNC", "STRUCT", "HEAP_GC_TYPE", "HEAP_VT_TYPE", "HEAP_FUNC_TYPE", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class HeapTypeTags {
    public static final int ANY = 0;
    public static final int EQ = 1;
    public static final int EXTERN = 2;
    public static final int FUNC = 3;
    public static final int HEAP_FUNC_TYPE = 9;
    public static final int HEAP_GC_TYPE = 7;
    public static final int HEAP_VT_TYPE = 8;
    public static final HeapTypeTags INSTANCE = new HeapTypeTags();
    public static final int NONE = 5;
    public static final int NO_EXTERN = 4;
    public static final int NO_FUNC = 5;
    public static final int STRUCT = 6;

    private HeapTypeTags() {
    }
}
