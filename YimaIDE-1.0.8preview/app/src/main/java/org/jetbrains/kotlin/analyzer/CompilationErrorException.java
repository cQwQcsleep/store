package org.jetbrains.kotlin.analyzer;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0003\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/CompilationErrorException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "message", "", "(Ljava/lang/String;)V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CompilationErrorException extends RuntimeException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompilationErrorException(String str) {
        super(str);
        str.getClass();
    }

    public CompilationErrorException() {
    }
}
