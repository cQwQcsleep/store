package androidx.compose.compiler.plugins.kotlin.analysis;

import androidx.compose.compiler.plugins.kotlin.lower.KeyInfo;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression;
import org.jetbrains.kotlin.util.slicedMap.BasicWritableSlice;
import org.jetbrains.kotlin.util.slicedMap.RewritePolicy;
import org.jetbrains.kotlin.util.slicedMap.WritableSlice;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\tR\u001d\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00150\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\tR\u001d\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\tR\u001d\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\t¨\u0006\u001b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/ComposeWritableSlices;", "", "<init>", "()V", "IS_SYNTHETIC_COMPOSABLE_CALL", "Lorg/jetbrains/kotlin/util/slicedMap/WritableSlice;", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "", "getIS_SYNTHETIC_COMPOSABLE_CALL", "()Lorg/jetbrains/kotlin/util/slicedMap/WritableSlice;", "IS_STATIC_FUNCTION_EXPRESSION", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "getIS_STATIC_FUNCTION_EXPRESSION", "IS_STATIC_EXPRESSION", "getIS_STATIC_EXPRESSION", "IS_COMPOSABLE_SINGLETON", "Lorg/jetbrains/kotlin/ir/IrElement;", "getIS_COMPOSABLE_SINGLETON", "IS_COMPOSABLE_SINGLETON_CLASS", "getIS_COMPOSABLE_SINGLETON_CLASS", "DURABLE_FUNCTION_KEY", "Landroidx/compose/compiler/plugins/kotlin/lower/KeyInfo;", "getDURABLE_FUNCTION_KEY", "HAS_TRANSFORMED_LAMBDA", "getHAS_TRANSFORMED_LAMBDA", "IS_TRANSFORMED_LAMBDA", "getIS_TRANSFORMED_LAMBDA", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeWritableSlices {
    private static final WritableSlice<IrElement, KeyInfo> DURABLE_FUNCTION_KEY;
    private static final WritableSlice<IrElement, Boolean> HAS_TRANSFORMED_LAMBDA;
    public static final ComposeWritableSlices INSTANCE = new ComposeWritableSlices();
    private static final WritableSlice<IrElement, Boolean> IS_COMPOSABLE_SINGLETON;
    private static final WritableSlice<IrElement, Boolean> IS_COMPOSABLE_SINGLETON_CLASS;
    private static final WritableSlice<IrExpression, Boolean> IS_STATIC_EXPRESSION;
    private static final WritableSlice<IrExpression, Boolean> IS_STATIC_FUNCTION_EXPRESSION;
    private static final WritableSlice<IrFunctionAccessExpression, Boolean> IS_SYNTHETIC_COMPOSABLE_CALL;
    private static final WritableSlice<IrElement, Boolean> IS_TRANSFORMED_LAMBDA;

    static {
        RewritePolicy rewritePolicy = RewritePolicy.DO_NOTHING;
        IS_SYNTHETIC_COMPOSABLE_CALL = new BasicWritableSlice(rewritePolicy);
        IS_STATIC_FUNCTION_EXPRESSION = new BasicWritableSlice(rewritePolicy);
        IS_STATIC_EXPRESSION = new BasicWritableSlice(rewritePolicy);
        IS_COMPOSABLE_SINGLETON = new BasicWritableSlice(rewritePolicy);
        IS_COMPOSABLE_SINGLETON_CLASS = new BasicWritableSlice(rewritePolicy);
        DURABLE_FUNCTION_KEY = new BasicWritableSlice(rewritePolicy);
        HAS_TRANSFORMED_LAMBDA = new BasicWritableSlice(rewritePolicy);
        IS_TRANSFORMED_LAMBDA = new BasicWritableSlice(rewritePolicy);
    }

    private ComposeWritableSlices() {
    }

    public final WritableSlice<IrElement, KeyInfo> getDURABLE_FUNCTION_KEY() {
        return DURABLE_FUNCTION_KEY;
    }

    public final WritableSlice<IrElement, Boolean> getHAS_TRANSFORMED_LAMBDA() {
        return HAS_TRANSFORMED_LAMBDA;
    }

    public final WritableSlice<IrElement, Boolean> getIS_COMPOSABLE_SINGLETON() {
        return IS_COMPOSABLE_SINGLETON;
    }

    public final WritableSlice<IrElement, Boolean> getIS_COMPOSABLE_SINGLETON_CLASS() {
        return IS_COMPOSABLE_SINGLETON_CLASS;
    }

    public final WritableSlice<IrExpression, Boolean> getIS_STATIC_EXPRESSION() {
        return IS_STATIC_EXPRESSION;
    }

    public final WritableSlice<IrExpression, Boolean> getIS_STATIC_FUNCTION_EXPRESSION() {
        return IS_STATIC_FUNCTION_EXPRESSION;
    }

    public final WritableSlice<IrFunctionAccessExpression, Boolean> getIS_SYNTHETIC_COMPOSABLE_CALL() {
        return IS_SYNTHETIC_COMPOSABLE_CALL;
    }

    public final WritableSlice<IrElement, Boolean> getIS_TRANSFORMED_LAMBDA() {
        return IS_TRANSFORMED_LAMBDA;
    }
}
