package org.jetbrains.kotlin.backend.common.linkage.issues;

import kotlin.Metadata;
import org.jetbrains.kotlin.analyzer.CompilationErrorException;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0012\u0010\u0004\u001a\u00020\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/issues/KotlinIrLinkerIssue;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "errorMessage", "", "getErrorMessage", "()Ljava/lang/String;", "raiseIssue", "", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class KotlinIrLinkerIssue {
    public abstract String getErrorMessage();

    public final Void raiseIssue(MessageCollector messageCollector) {
        messageCollector.getClass();
        messageCollector.report(CompilerMessageSeverity.ERROR, getErrorMessage(), (CompilerMessageSourceLocation) null);
        throw new CompilationErrorException(getErrorMessage());
    }
}
