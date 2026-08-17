package androidx.compose.compiler.plugins.kotlin;

import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.IrSourcePrinterVisitor;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0002J\b\u0010,\u001a\u00020-H\u0016J\u001e\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u00020302H\u0016J:\u00104\u001a\u00020-2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010=\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u0007H\u0016J@\u0010?\u001a\u00020-2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007H\u0016J\u0010\u0010@\u001a\u00020-2\u0006\u0010&\u001a\u00020\u000eH\u0016J\u001c\u0010A\u001a\u00020-2\n\u0010B\u001a\u00060Cj\u0002`D2\u0006\u0010E\u001a\u00020FH\u0016R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\bR\u0014\u0010\u0013\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\bR\u0014\u0010\u0015\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\bR\u0014\u0010\u0017\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\bR\u0014\u0010\u0019\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\bR\u0014\u0010\u001b\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\bR\u0014\u0010\u001c\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\bR\u0014\u0010\u001e\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\bR\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010#R\u0014\u0010&\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0010R\u0014\u0010(\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\bR\u0014\u0010*\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\b¨\u0006G"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/EmptyFunctionMetrics;", "Landroidx/compose/compiler/plugins/kotlin/FunctionMetrics;", "<init>", "()V", "emptyMetricsAccessed", "", "isEmpty", "", "()Z", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "name", "", "getName", "()Ljava/lang/String;", "composable", "getComposable", "skippable", "getSkippable", "restartable", "getRestartable", "readonly", "getReadonly", "inline", "getInline", "isLambda", "hasDefaults", "getHasDefaults", "defaultsGroup", "getDefaultsGroup", "groups", "", "getGroups", "()I", "calls", "getCalls", "scheme", "getScheme", "abstract", "getAbstract", "open", "getOpen", "recordGroup", "", "recordComposableCall", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "paramMeta", "", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "recordParameter", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "stability", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "default", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "defaultStatic", "used", "recordFunction", "recordScheme", "print", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "src", "Landroidx/compose/compiler/plugins/kotlin/lower/IrSourcePrinterVisitor;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class EmptyFunctionMetrics implements FunctionMetrics {
    public static final EmptyFunctionMetrics INSTANCE = new EmptyFunctionMetrics();

    private EmptyFunctionMetrics() {
    }

    private final Void emptyMetricsAccessed() {
        throw new IllegalStateException("Empty metrics accessed");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getAbstract() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public int getCalls() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getComposable() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getDefaultsGroup() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public FqName getFqName() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public int getGroups() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getHasDefaults() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getInline() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public String getName() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getOpen() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getReadonly() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getRestartable() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public String getScheme() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getSkippable() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean isEmpty() {
        return true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean isLambda() throws KotlinNothingValueException {
        emptyMetricsAccessed();
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void print(Appendable out, IrSourcePrinterVisitor src) {
        out.getClass();
        src.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordComposableCall(IrCall expression, List<ComposableFunctionBodyTransformer.CallArgumentMeta> paramMeta) {
        expression.getClass();
        paramMeta.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordFunction(boolean composable, boolean restartable, boolean skippable, boolean isLambda, boolean inline, boolean hasDefaults, boolean readonly) {
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordGroup() {
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordParameter(IrValueParameter declaration, IrType type, Stability stability, IrExpression irExpression, boolean defaultStatic, boolean used) {
        declaration.getClass();
        type.getClass();
        stability.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordScheme(String scheme) {
        scheme.getClass();
    }
}
