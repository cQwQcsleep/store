package org.jetbrains.kotlin.backend.wasm.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/serialization/TypeDeclarationTags;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "FUNCTION", "Lkotlin/UInt;", "I", "STRUCT", "ARRAY", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class TypeDeclarationTags {
    public static final int ARRAY = 2;
    public static final int FUNCTION = 0;
    public static final TypeDeclarationTags INSTANCE = new TypeDeclarationTags();
    public static final int STRUCT = 1;

    private TypeDeclarationTags() {
    }
}
