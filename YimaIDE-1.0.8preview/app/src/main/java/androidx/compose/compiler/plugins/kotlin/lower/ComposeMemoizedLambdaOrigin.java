package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposeMemoizedLambdaOrigin;", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "<init>", "()V", "debugName", "", "getDebugName", "()Ljava/lang/String;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeMemoizedLambdaOrigin implements IrStatementOrigin {
    public static final ComposeMemoizedLambdaOrigin INSTANCE = new ComposeMemoizedLambdaOrigin();

    private ComposeMemoizedLambdaOrigin() {
    }

    public String getDebugName() {
        return "ComposeMemoizedLambdaOrigin";
    }
}
