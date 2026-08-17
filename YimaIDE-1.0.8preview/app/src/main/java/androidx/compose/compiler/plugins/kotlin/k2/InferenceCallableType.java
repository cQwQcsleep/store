package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\tH\u0016R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u00020\u000e2\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\u0014\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0082\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/InferenceCallableType;", "Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "callable", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "getCallable", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "toScheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "isTypeFor", "", "hashCode", "", "equals", "other", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class InferenceCallableType extends InferenceNodeType {
    private final FirCallableSymbol<?> callable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferenceCallableType(FirCallableSymbol<?> firCallableSymbol) {
        super(null);
        firCallableSymbol.getClass();
        this.callable = firCallableSymbol;
    }

    public boolean equals(Object other) {
        return (other instanceof InferenceCallableType) && Intrinsics.areEqual(((InferenceCallableType) other).callable.getCallableId(), this.callable.getCallableId());
    }

    public final FirCallableSymbol<?> getCallable() {
        return this.callable;
    }

    public int hashCode() {
        CallableId callableId = this.callable.getCallableId();
        return (callableId != null ? callableId.hashCode() : 0) * 31;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.InferenceNodeType
    public boolean isTypeFor(FirCallableSymbol<?> callable) {
        callable.getClass();
        return Intrinsics.areEqual(this.callable.getCallableId(), callable.getCallableId());
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.InferenceNodeType
    public Scheme toScheme(CheckerContext checkerContext) {
        checkerContext.getClass();
        return ComposableTargetCheckerKt.toScheme(checkerContext, this.callable);
    }
}
