package org.jetbrains.kotlin.backend.common.linkage.issues;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0003\u001a\u00020\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/issues/IrDeserializationException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "Lorg/jetbrains/kotlin/backend/common/linkage/issues/IrDisallowedErrorNode;", "Lorg/jetbrains/kotlin/backend/common/linkage/issues/IrSymbolTypeMismatchException;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class IrDeserializationException extends Exception {
    public /* synthetic */ IrDeserializationException(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        message.getClass();
        return message;
    }

    private IrDeserializationException(String str) {
        super(str);
    }
}
