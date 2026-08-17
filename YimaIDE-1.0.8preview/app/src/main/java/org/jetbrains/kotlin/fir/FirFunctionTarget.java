package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\tR\u0012\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bX\u0082.¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00028T@TX\u0094\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirFunctionTarget;", "Lorg/jetbrains/kotlin/fir/FirAbstractTarget;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "labelName", Argument.Delimiters.none, "isLambda", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Z)V", "()Z", "targetSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "value", "_labeledElement", "get_labeledElement", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "set_labeledElement", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFunctionTarget extends FirAbstractTarget<FirFunction> {
    private final boolean isLambda;
    private FirFunctionSymbol<?> targetSymbol;

    public FirFunctionTarget(String str, boolean z) {
        super(str);
        this.isLambda = z;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.FirAbstractTarget
    public FirFunction get_labeledElement() throws UninitializedPropertyAccessException {
        FirFunctionSymbol<?> firFunctionSymbol = this.targetSymbol;
        if (firFunctionSymbol == null) {
            Intrinsics.throwUninitializedPropertyAccessException("targetSymbol");
            firFunctionSymbol = null;
        }
        return (FirFunction) firFunctionSymbol.getFir();
    }

    /* JADX INFO: renamed from: isLambda, reason: from getter */
    public final boolean getIsLambda() {
        return this.isLambda;
    }

    @Override // org.jetbrains.kotlin.fir.FirAbstractTarget
    public void set_labeledElement(FirFunction firFunction) {
        firFunction.getClass();
        this.targetSymbol = firFunction.getSymbol();
    }
}
