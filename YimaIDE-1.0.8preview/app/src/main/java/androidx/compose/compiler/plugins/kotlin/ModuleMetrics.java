package androidx.compose.compiler.plugins.kotlin;

import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.expressions.IrCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J \u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J \u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H&J\u001e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H&J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0010\u0010\u001c\u001a\u00020\u0006*\u00060\u001dj\u0002`\u001eH&J\u0010\u0010\u001f\u001a\u00020\u0006*\u00060\u001dj\u0002`\u001eH&J\u0010\u0010 \u001a\u00020\u0006*\u00060\u001dj\u0002`\u001eH&J\u0010\u0010!\u001a\u00020\u0006*\u00060\u001dj\u0002`\u001eH&J\u0010\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u001bH&J\u0010\u0010$\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u001bH&J\u0010\u0010%\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020&H&R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006'À\u0006\u0003"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "", "isEmpty", "", "()Z", "recordFunction", "", "function", "Landroidx/compose/compiler/plugins/kotlin/FunctionMetrics;", "recordClass", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "marked", "stability", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "recordLambda", "composable", "memoized", "singleton", "recordComposableCall", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "paramMeta", "", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "log", "message", "", "appendModuleJson", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "appendComposablesCsv", "appendComposablesTxt", "appendClassesTxt", "saveMetricsTo", "directory", "saveReportsTo", "makeFunctionMetrics", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ModuleMetrics {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean isEmpty(ModuleMetrics moduleMetrics) {
            return ModuleMetrics.super.isEmpty();
        }
    }

    void appendClassesTxt(Appendable appendable);

    void appendComposablesCsv(Appendable appendable);

    void appendComposablesTxt(Appendable appendable);

    void appendModuleJson(Appendable appendable);

    default boolean isEmpty() {
        return false;
    }

    void log(String message);

    FunctionMetrics makeFunctionMetrics(IrFunction function);

    void recordClass(IrClass declaration, boolean marked, Stability stability);

    void recordComposableCall(IrCall expression, List<ComposableFunctionBodyTransformer.CallArgumentMeta> paramMeta);

    void recordFunction(FunctionMetrics function);

    void recordLambda(boolean composable, boolean memoized, boolean singleton);

    void saveMetricsTo(String directory);

    void saveReportsTo(String directory);
}
