package androidx.compose.compiler.plugins.kotlin;

import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.expressions.IrCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J \u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J\u001e\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\b*\u00060\u001fj\u0002` H\u0016J\u0010\u0010!\u001a\u00020\b*\u00060\u001fj\u0002` H\u0016J\u0010\u0010\"\u001a\u00020\b*\u00060\u001fj\u0002` H\u0016J\u0010\u0010#\u001a\u00020\b*\u00060\u001fj\u0002` H\u0016J\u0010\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\u001dH\u0016J\u0010\u0010&\u001a\u00020\b2\u0006\u0010%\u001a\u00020\u001dH\u0016J\u0010\u0010'\u001a\u00020\n2\u0006\u0010\t\u001a\u00020(H\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006¨\u0006)"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/EmptyModuleMetrics;", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "<init>", "()V", "isEmpty", "", "()Z", "recordFunction", "", "function", "Landroidx/compose/compiler/plugins/kotlin/FunctionMetrics;", "recordClass", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "marked", "stability", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "recordLambda", "composable", "memoized", "singleton", "recordComposableCall", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "paramMeta", "", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "log", "message", "", "appendModuleJson", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "appendComposablesCsv", "appendComposablesTxt", "appendClassesTxt", "saveMetricsTo", "directory", "saveReportsTo", "makeFunctionMetrics", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class EmptyModuleMetrics implements ModuleMetrics {
    public static final EmptyModuleMetrics INSTANCE = new EmptyModuleMetrics();

    private EmptyModuleMetrics() {
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void appendClassesTxt(Appendable appendable) {
        appendable.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void appendComposablesCsv(Appendable appendable) {
        appendable.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void appendComposablesTxt(Appendable appendable) {
        appendable.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void appendModuleJson(Appendable appendable) {
        appendable.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public boolean isEmpty() {
        return true;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void log(String message) {
        message.getClass();
        System.out.println((Object) message);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public FunctionMetrics makeFunctionMetrics(IrFunction function) {
        function.getClass();
        return EmptyFunctionMetrics.INSTANCE;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void recordClass(IrClass declaration, boolean marked, Stability stability) {
        declaration.getClass();
        stability.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void recordComposableCall(IrCall expression, List<ComposableFunctionBodyTransformer.CallArgumentMeta> paramMeta) {
        expression.getClass();
        paramMeta.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void recordFunction(FunctionMetrics function) {
        function.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void recordLambda(boolean composable, boolean memoized, boolean singleton) {
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void saveMetricsTo(String directory) {
        directory.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void saveReportsTo(String directory) {
        directory.getClass();
    }
}
