package org.jetbrains.kotlin.backend.wasm.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/serialization/NullableTags;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "NULL", "Lkotlin/UInt;", "I", "NOT_NULL", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NullableTags {
    public static final NullableTags INSTANCE = new NullableTags();
    public static final int NOT_NULL = 1;
    public static final int NULL = 0;

    private NullableTags() {
    }
}
