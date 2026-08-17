package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u0005H&R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH&\u0082\u0001\u0001\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "", "<init>", "()V", "toScheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "isTypeFor", "", "callable", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Landroidx/compose/compiler/plugins/kotlin/k2/InferenceCallableType;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class InferenceNodeType {
    public /* synthetic */ InferenceNodeType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract boolean isTypeFor(FirCallableSymbol<?> callable);

    public abstract Scheme toScheme(CheckerContext checkerContext);

    private InferenceNodeType() {
    }
}
