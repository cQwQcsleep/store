package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAbstractImportingScope$processCallablesFromImportsByName$1 implements Function1 {
    final /* synthetic */ Function2 $buildImportedCopy;
    final /* synthetic */ Function1 $processor;
    final /* synthetic */ FirRegularClassSymbol $staticsScopeOwnerSymbol;

    public FirAbstractImportingScope$processCallablesFromImportsByName$1(FirRegularClassSymbol firRegularClassSymbol, Function1 function1, Function2 function2) {
        this.$staticsScopeOwnerSymbol = firRegularClassSymbol;
        this.$processor = function1;
        this.$buildImportedCopy = function2;
    }

    public final void invoke(FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (firCallableSymbol.getRawStatus().isStatic() || this.$staticsScopeOwnerSymbol.getClassKind() == ClassKind.OBJECT) {
            this.$processor.invoke(this.$buildImportedCopy.invoke(firCallableSymbol, this.$staticsScopeOwnerSymbol.getClassId()));
        } else {
            this.$processor.invoke(firCallableSymbol);
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FirCallableSymbol) obj);
        return Unit.INSTANCE;
    }
}
