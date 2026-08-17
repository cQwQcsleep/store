package androidx.compose.compiler.plugins.kotlin;

import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.IrSourcePrinterVisitor;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010(\u001a\u00020)H&J\u001e\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.H&J:\u00100\u001a\u00020)2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020\u00032\u0006\u0010:\u001a\u00020\u0003H&J@\u0010;\u001a\u00020)2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H&J\u0010\u0010<\u001a\u00020)2\u0006\u0010&\u001a\u00020\nH&J\u001c\u0010=\u001a\u00020)2\n\u0010>\u001a\u00060?j\u0002`@2\u0006\u0010A\u001a\u00020BH&R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0004R\u0012\u0010\u000f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0004R\u0012\u0010\u0011\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0004R\u0012\u0010\u0013\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0004R\u0012\u0010\u0015\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0004R\u0012\u0010\u0017\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0004R\u0012\u0010\u0019\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0004R\u0012\u0010\u001b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0004R\u0012\u0010\u001c\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0004R\u0012\u0010\u001e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0004R\u0012\u0010 \u001a\u00020!X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0012\u0010$\u001a\u00020!X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010#R\u0014\u0010&\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006CÀ\u0006\u0003"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/FunctionMetrics;", "", "isEmpty", "", "()Z", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "name", "", "getName", "()Ljava/lang/String;", "composable", "getComposable", "skippable", "getSkippable", "restartable", "getRestartable", "readonly", "getReadonly", "inline", "getInline", "open", "getOpen", "abstract", "getAbstract", "isLambda", "hasDefaults", "getHasDefaults", "defaultsGroup", "getDefaultsGroup", "groups", "", "getGroups", "()I", "calls", "getCalls", "scheme", "getScheme", "recordGroup", "", "recordComposableCall", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "paramMeta", "", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "recordParameter", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "stability", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "default", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "defaultStatic", "used", "recordFunction", "recordScheme", "print", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "src", "Landroidx/compose/compiler/plugins/kotlin/lower/IrSourcePrinterVisitor;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface FunctionMetrics {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean isEmpty(FunctionMetrics functionMetrics) {
            return FunctionMetrics.super.isEmpty();
        }
    }

    boolean getAbstract();

    int getCalls();

    boolean getComposable();

    boolean getDefaultsGroup();

    FqName getFqName();

    int getGroups();

    boolean getHasDefaults();

    boolean getInline();

    String getName();

    boolean getOpen();

    boolean getReadonly();

    boolean getRestartable();

    String getScheme();

    boolean getSkippable();

    default boolean isEmpty() {
        return false;
    }

    boolean isLambda();

    void print(Appendable out, IrSourcePrinterVisitor src);

    void recordComposableCall(IrCall expression, List<ComposableFunctionBodyTransformer.CallArgumentMeta> paramMeta);

    void recordFunction(boolean composable, boolean restartable, boolean skippable, boolean isLambda, boolean inline, boolean hasDefaults, boolean readonly);

    void recordGroup();

    void recordParameter(IrValueParameter declaration, IrType type, Stability stability, IrExpression irExpression, boolean defaultStatic, boolean used);

    void recordScheme(String scheme);
}
