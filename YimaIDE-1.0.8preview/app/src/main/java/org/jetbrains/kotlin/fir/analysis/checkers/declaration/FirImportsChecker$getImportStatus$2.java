package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirImportsChecker$getImportStatus$2 implements Function1<FirVariableSymbol<?>, Unit> {
    final /* synthetic */ CheckerContext $context;
    final /* synthetic */ Ref.BooleanRef $found;
    final /* synthetic */ Function1<FirCallableSymbol<?>, Boolean> $isApplicable;
    final /* synthetic */ Ref.ObjectRef<FirCallableSymbol<?>> $symbol;

    /* JADX WARN: Multi-variable type inference failed */
    public FirImportsChecker$getImportStatus$2(CheckerContext checkerContext, Function1<? super FirCallableSymbol<?>, Boolean> function1, Ref.BooleanRef booleanRef, Ref.ObjectRef<FirCallableSymbol<?>> objectRef) {
        this.$context = checkerContext;
        this.$isApplicable = function1;
        this.$found = booleanRef;
        this.$symbol = objectRef;
    }

    public final void invoke(FirVariableSymbol<?> firVariableSymbol) {
        firVariableSymbol.getClass();
        if (FirImportsChecker.INSTANCE.isVisible(this.$context, firVariableSymbol) && ((Boolean) this.$isApplicable.invoke(firVariableSymbol)).booleanValue()) {
            this.$found.element = true;
        }
        this.$symbol.element = firVariableSymbol;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FirVariableSymbol<?>) obj);
        return Unit.INSTANCE;
    }
}
